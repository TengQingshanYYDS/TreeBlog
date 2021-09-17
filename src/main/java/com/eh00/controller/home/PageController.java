package com.eh00.controller.home;

import com.eh00.entity.Article;
import com.eh00.entity.Category;
import com.eh00.entity.Page;
import com.eh00.entity.Tag;
import com.eh00.enums.PageStatus;
import com.eh00.service.ArticleService;
import com.eh00.service.CategoryService;
import com.eh00.service.PageService;
import com.eh00.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * 页面详情页面
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/{key}")
    public String pageDetail(@PathVariable("key") String key, Model model) {
        Page page = pageService.getPageByKey(PageStatus.NORMAL.getValue(), key);
        if (page == null) {
            return "redirect:/404";
        }
        model.addAttribute("page", page);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/page";
    }

    /**
     * 文章归档页面显示
     * @param model
     * @return
     */
    @RequestMapping("/articleFile")
    public String articleFile(Model model) {
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/articleFile";
    }

    /**
     * 站点地图显示
     * @param model
     * @return
     */
    @RequestMapping("/map")
    public String siteMap(Model model) {
        //文章显示
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);

        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        //标签显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/Page/siteMap";
    }

    /**
     * 留言板
     * @param model
     * @return
     */
    @RequestMapping("/message")
    public String message(Model model) {
        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/Page/message";
    }
}
