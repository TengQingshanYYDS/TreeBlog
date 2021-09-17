package com.eh00.mapper;

import com.eh00.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ArticleMapper {

    Integer countArticle(Integer status);

    Integer countArticleComment();

    Integer countArticleView();

    List<Article> listArticle();

    Integer countArticleByUser(Integer id);

    List<Article> listArticleByLimit(@Param("userId") Integer userId, @Param("limit") Integer limit);

    void update(Article article);

    void deleteById(Integer id);

    Article getArticleByStatusAndId(@Param("status") Integer status, @Param("id") Integer id);

    List<Article> listArticleByViewCount(Integer limit);

    Article getAfterArticle(Integer id);

    Article getPreArticle(Integer id);

    List<Article> listRandomArticle(Integer limit);

    List<Article> listArticleByCommentCount(Integer limit);

    void insert(Article article);

    void updateCommentCount(Integer articleId);

    Article getLastUpdateArticle();

    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> cateIds,
                                           @Param("limit") Integer limit);

    List<Article> listAllNotWithContent();

    List<Article> findAll(HashMap<String, Object> criteria);

    List<Integer> listArticleIdsByUserId(Integer userId);

}
