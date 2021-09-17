package com.eh00.service;

import com.eh00.entity.Article;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

public interface ArticleService {

    /**
     * 获取文章总数
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

    /**
     * 获得评论总数
     * @return 数量
     */
    Integer countArticleComment();

    /**
     * 浏览量总数
     * @return
     */
    Integer countArticleView();

    /**
     * 统计有这个分类的文章数
     * @param categoryId
     * @return
     */
    Integer countArticleByCategoryId(Integer categoryId);

    /**
     * 统计有这个标签的文章数
     * @param tagId
     * @return
     */
    Integer countArticleByTagId(Integer tagId);

    /**
     * 获得最新文章
     * @param userId
     * @param limit
     * @return
     */
    List<Article> listRecentArticle(Integer userId, Integer limit);

    /**
     * 修改文章详细信息
     * @param article
     */
    void updateArticleDetail(Article article);

    /**
     * 修改文章简单信息
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     * @param id 文章id
     */
    void deleteArticle(Integer id);

    /**
     * 分页显示
     * @param pageIndex 开始页码
     * @param pageSize  每页文章数量
     * @param criteria  查询条件
     * @return  文章列表
     */
    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);

    /**
     * 文章详情页面显示
     * @param status
     * @param id
     * @return
     */
    Article getArticleByStatusAndId(Integer status, Integer id);

    /**
     * 获取访问量较多的文章
     * @param limit
     * @return
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获得下一文章
     * @param id
     * @return
     */
    Article getAfterArticle(Integer id);

    /**
     * 获得上一文章
     * @param id
     * @return
     */
    Article getPreArticle(Integer id);

    /**
     * 获得随机文章
     * @param limit 数量
     * @return 列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得评论数较多的文章
     * @param limit 数量
     * @return 列表
     */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * 添加文章
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 更新文章的评论数
     * @param articleId
     */
    void updateCommentCount(Integer articleId);

    /**
     * 获得最后更新记录
     * @return
     */
    Article getLastUpdateArticle();

    /**
     * 获得相关文章
     * @param cateIds 分类ID
     * @param limit 数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);

    /**
     * 根据文章ID获得分类ID列表
     * @param articleId 文章Id
     * @return 列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 获得所有的文章
     * @return 列表
     */
    List<Article> listAllNotWithContent();
}
