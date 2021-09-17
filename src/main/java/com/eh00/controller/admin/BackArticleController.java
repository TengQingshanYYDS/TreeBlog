package com.eh00.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.eh00.dto.ArticleParam;
import com.eh00.entity.Article;
import com.eh00.entity.Category;
import com.eh00.entity.Tag;
import com.eh00.entity.User;
import com.eh00.enums.UserRole;
import com.eh00.service.ArticleService;
import com.eh00.service.CategoryService;
import com.eh00.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 后台文章列表显示
     * @param pageIndex
     * @param pageSize
     * @param status
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status,
                        Model model,
                        HttpSession session) {
        HashMap<String, Object> criteria = new HashMap<>();
        // 判断网址是否需要 status
        if (status == null) {
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
            criteria.put("status", status);
        }

        User user = (User) session.getAttribute("user");
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            // 用户查询自己的文章，管理员查询所有的
            criteria.put("userId", user.getUserId());
        }

        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        return "Admin/Article/index";
    }

    /**
     * 后台添加文章页面显示
     * @param model
     * @return
     */
    @RequestMapping("/insert")
    public String insertArticleView(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("tagList", tagList);

        return "Admin/Article/insert";
    }

    @RequestMapping(value = {"/insertSubmit", "/insertDraftSubmit"}, method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session,
                                      ArticleParam articleParam,
                                      HttpServletRequest request) {
        Article article = new Article();

        // 用户id
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        // 文章标题
        article.setArticleTitle(articleParam.getArticleTitle());
        // 文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
        } else {
            article.setArticleSummary(summaryText);
        }
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());

        // 填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        // 填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (Integer tagId : articleParam.getArticleTagIds()) {
                Tag tag = new Tag(tagId);
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        if (request.getContextPath() == "insertSubmit") {
            return "redirect:/admin/article";
        } else {
            return "redirect:/admin";
        }
    }

    /**
     * 删除文章
     * @param id
     * @param session
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void deleteArticle(@PathVariable("id") Integer id, HttpSession session) {
        Article dbArticle = articleService.getArticleByStatusAndId(null, id);
        if (dbArticle == null) {
            return;
        }
        User user = (User) session.getAttribute("user");
        // 如果不是管理员，访问其他用户的数据，则返回
        if (!Objects.equals(dbArticle.getArticleUserId(), user.getUserId())
                && !Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return;
        }
        articleService.deleteArticle(id);
    }

    /**
     * 编辑页面显示
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editArticleView(@PathVariable("id") Integer id,
                                  Model model,
                                  HttpSession session) {
        Article article = articleService.getArticleByStatusAndId(null, id);
        if (article == null) {
            return "redirect:/404";
        }

        User user = (User) session.getAttribute("user");
        // 如果不是管理员，访问其他用户的数据，则返回403
        if (!Objects.equals(article.getArticleUserId(), user.getUserId())
                && !Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return "redirect:/403";
        }

        model.addAttribute("article", article);

        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        return "Admin/Article/edit";
    }

    @RequestMapping(value = "editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam, HttpSession session) {
        Article dbArticle = articleService.getArticleByStatusAndId(null, articleParam.getArticleId());
        if (dbArticle == null) {
            return "redirect:/404";
        }

        User user = (User) session.getAttribute("user");
        // 如果不是管理员，访问其他用户的数据，则返回403
        if (!Objects.equals(dbArticle.getArticleUserId(), user.getUserId())
                && !Objects.equals(user.getUserRole(), UserRole.ADMIN.getValue())) {
            return "redirect:/403";
        }

        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());

        // 文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
        } else {
            article.setArticleSummary(summaryText);
        }

        // 填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        // 填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (Integer tagId : articleParam.getArticleTagIds()) {
                Tag tag = new Tag(tagId);
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }

}
