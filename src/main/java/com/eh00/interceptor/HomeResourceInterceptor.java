package com.eh00.interceptor;

import com.eh00.entity.*;
import com.eh00.enums.ArticleStatus;
import com.eh00.enums.LinkStatus;
import com.eh00.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HomeResourceInterceptor implements HandlerInterceptor {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private CommentService commentService;

    /**
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //菜单、目录 显示
        List<Menu> menuList = menuService.listMenu();
        request.setAttribute("menuList", menuList);

        List<Category> categoryList = categoryService.listCategory();
        request.setAttribute("allCategoryList", categoryList);

        //获得网站概况
        List<String> siteBasicStatistics = new ArrayList<>();
        siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue()) + "");
        siteBasicStatistics.add(articleService.countArticleComment()+"");
        siteBasicStatistics.add(categoryService.countCategory()+"");
        siteBasicStatistics.add(tagService.countTag()+"");
        siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue()) + "");
        siteBasicStatistics.add(articleService.countArticleView()+"");
        request.setAttribute("siteBasicStatistics", siteBasicStatistics);
        //最后更新的文章
        Article lastUpdateArticle = articleService.getLastUpdateArticle();
        request.setAttribute("lastUpdateArticle", lastUpdateArticle);
        //标签
        List<Tag> allTagList = tagService.listTag();
        request.setAttribute("allTagList", allTagList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
        request.setAttribute("recentCommentList", recentCommentList);

        //博客基本信息显示(Options)
        Options options = optionsService.getOptions();
        request.setAttribute("options", options);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
