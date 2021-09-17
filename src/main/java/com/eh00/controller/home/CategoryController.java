package com.eh00.controller.home;

import com.eh00.entity.Article;
import com.eh00.entity.Category;
import com.eh00.entity.Notice;
import com.eh00.entity.Tag;
import com.eh00.enums.ArticleStatus;
import com.eh00.enums.NoticeStatus;
import com.eh00.service.ArticleService;
import com.eh00.service.CategoryService;
import com.eh00.service.NoticeService;
import com.eh00.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("category/{cateId}")
    public String getArticleListByCategory(@PathVariable("cateId") Integer cateId,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                           Model model){
        //获取分类
        Category category = categoryService.getCategoryById(cateId);
        if (category == null) {
            return "redirect:404";
        }
        model.addAttribute("category", category);

        //文章列表
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("categoryId", cateId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏

        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);

        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        //公告
        List<Notice> noticeList = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        model.addAttribute("noticeList", noticeList);
        return "Home/Page/articleListByCategory";
    }
}
