package com.eh00.controller.home;

import cn.hutool.http.HtmlUtil;
import com.eh00.dto.JsonResult;
import com.eh00.entity.Article;
import com.eh00.entity.Comment;
import com.eh00.entity.User;
import com.eh00.enums.ArticleStatus;
import com.eh00.enums.Role;
import com.eh00.service.ArticleService;
import com.eh00.service.CommentService;
import com.eh00.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    public JsonResult insertComment(HttpRequest request, Comment comment,
                                    HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new JsonResult().fail("前先登录");
        }
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),
                comment.getCommentArticleId());
        if (article == null) {
            return new JsonResult().fail("文章不存在");
        }

        comment.setCommentUserId(user.getUserId());
        comment.setCommentCreateTime(new Date());
        if (Objects.equals(user.getUserId(), article.getArticleUserId())) {
            comment.setCommentRole(Role.OWNER.getValue());
        } else {
            comment.setCommentRole(Role.VISITOR.getValue());
        }
        //头像
        comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));

        //过滤字符 防止xss攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));

        comment.setCommentAuthorName(user.getUserNickname());
        comment.setCommentAuthorEmail(user.getUserEmail());
        comment.setCommentAuthorUrl(user.getUserUrl());

        try {
            commentService.insertComment(comment);
            articleService.updateCommentCount(article.getArticleId());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }
}
