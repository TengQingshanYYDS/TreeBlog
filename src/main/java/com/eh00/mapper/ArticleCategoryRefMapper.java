package com.eh00.mapper;

import com.eh00.entity.ArticleCategoryRef;
import com.eh00.entity.Category;

import java.util.List;

/**
 * 文章分类关联表Mapper
 */
public interface ArticleCategoryRefMapper {
    /**
     * 添加文章和分类关联记录
     * @param articleCategoryRef 关联对象
     * @return
     */
    int insert(ArticleCategoryRef articleCategoryRef);

    /**
     * 根据分类ID删除记录
     * @param categoryId
     * @return
     */
    int deleteByCategoryId(Integer categoryId);

    /**
     * 根据文章id删除记录
     * @param articleId
     * @return
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据分类ID统计文章数
     * @param categoryId
     * @return
     */
    int countArticleByCategoryId(Integer categoryId);

    /**
     * 根据文章id查询分类id
     * @param articleId
     * @return
     */
    List<Integer> selectCategoryIdByArticleId(Integer articleId);

    /**
     * 根据分类id查询文章id
     * @param categoryId
     * @return
     */
    List<Integer> selectArticleIdByCategoryId(Integer categoryId);

    /**
     * 根据文章ID获得分类列表
     *
     * @param articleId 文章ID
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);

}
