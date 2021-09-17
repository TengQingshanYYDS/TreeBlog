package com.eh00.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.eh00.entity.Article;
import com.eh00.entity.Comment;
import com.eh00.entity.User;
import com.eh00.enums.Role;
import com.eh00.enums.UserRole;
import com.eh00.service.ArticleService;
import com.eh00.service.CommentService;
import com.eh00.util.MyUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 评论页面
     * @param pageIndex
     * @param pageSize
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String commentList(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              HttpSession session,
                              Model model) {
        User user = (User) session.getAttribute("user");
        HashMap<String, Object> criteria = new HashMap<>();
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            // 用户查询自己的文章, 管理员查询所有的
            criteria.put("userId", user.getUserId());
        }
        PageInfo<Comment> commentPageInfo = commentService.listCommentByPage(pageIndex,
                pageSize, criteria);
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix", "/admin/comment?pageIndex");

        return "Admin/Comment/index";
    }

    /**
     * 已注册用户收到的评论
     * @param pageIndex
     * @param pageSize
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/receive")
    public String myReceiveComment(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                   HttpSession session,
                                   Model model) {
        User user = (User) session.getAttribute("user");
        PageInfo<Comment> commentPageInfo = commentService.listReceiveCommentByPage(pageIndex,
                pageSize, user.getUserId());
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix", "/admin/comment?pageIndex");

        return "Admin/Comment/index";
    }

    /**
     * 添加评论
     * @param request
     * @param comment
     * @param session
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertComment(HttpServletRequest request, Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Article article = articleService.getArticleByStatusAndId(null, comment.getCommentArticleId());
        if (article == null) {
            return ;
        }
        // 添加评论
        comment.setCommentUserId(user.getUserId());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(comment);
        // 更新文章的评论数
        articleService.updateCommentCount(article.getArticleId());
    }

    /**
     * 删除评论
     * @param id
     * @param session
     */
    @RequestMapping("/delete/{id}")
    public void deleteComment(@PathVariable Integer id, HttpSession session) {
        Comment comment = commentService.getCommentById(id);
        User user = (User) session.getAttribute("user");

        // 如果不是管理员，没有权限删除其他用户文章下面的评论
        Article article = articleService.getArticleByStatusAndId(null, comment.getCommentArticleId());
        if (!Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())
                && !Objects.equals(article.getArticleUserId(), user.getUserId())) {
            return;
        }

        // 删除评论
        commentService.deleteComment(id);
        // 删除子评论
        List<Comment> childCommentList = commentService.listChildComment(id);
        for (Comment childComment : childCommentList) {
            commentService.deleteComment(childComment.getCommentId());
        }
        // 更新文章评论数
        articleService.updateArticle(article);
    }

    /**
     * 编辑评论页面显示
     * @param id
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editCommentView(@PathVariable Integer id, HttpSession session, Model model) {
        // 没有权限操作,只有管理员可以操作
        User user = (User) session.getAttribute("user");
        if (!Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return "redirect:/403";
        }

        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "Admin/Comment/edit";
    }

    /**
     * 编辑评论提交
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        // 没有权限操作,只有管理员可以操作
        if (!Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return "redirect:/403";
        }
        commentService.updateComment(comment);
        return "redirect:/admin/comment";
    }

    /**
     * 回复评论页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/reply/{id}")
    public String replyCommentView(@PathVariable Integer id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "Admin/Comment/reply";
    }

    /**
     * 回复评论提交
     * @param request
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replyCommentSubmit (HttpServletRequest request, Comment comment, HttpSession session) {
        // 更新文章评论数
        Article article = articleService.getArticleByStatusAndId(null, comment.getCommentArticleId());
        if (article == null) {
            return "redirect:/404";
        }
        articleService.updateCommentCount(article.getArticleId());

        User user = (User) session.getAttribute("user");
        // 掉线
        if (user == null) {
            return "redirect:/admin/login";
        }
        //添加评论
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(user.getUserNickname());
        comment.setCommentAuthorEmail(user.getUserEmail());
        comment.setCommentAuthorUrl(user.getUserUrl());
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        if (Objects.equals(user.getUserId(), article.getArticleUserId())) {
            comment.setCommentRole(Role.OWNER.getValue());
        } else {
            comment.setCommentRole(Role.VISITOR.getValue());
        }
        commentService.insertComment(comment);
        return "redirect:/admin/comment";
    }
}
