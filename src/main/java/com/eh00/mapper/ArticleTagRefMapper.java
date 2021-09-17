package com.eh00.mapper;

import com.eh00.entity.ArticleTagRef;
import com.eh00.entity.Tag;

import java.util.List;

/**
 * 文章标签关联表Mapper
 */
public interface ArticleTagRefMapper {
    /**
     * 添加文章和标签关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    int insert(ArticleTagRef record);

    /**
     * 根据标签ID删除记录
     * @param tagId 标签id
     * @return 影响行数
     */
    int deleteByTagId(Integer tagId);

    /**
     * 根据文章ID删除记录
     * @param articleId
     * @return
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据标签ID统计文章数
     * @param tagId 标签id
     * @return 影响行数
     */
    int countArticleByTagId(Integer tagId);

    /**
     * 根据文章获得标签列表
     * @param articleId 文章id
     * @return 标签列表
     */
    List<Tag> listTagByArticleId(Integer articleId);

}
