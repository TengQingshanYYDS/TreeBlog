package com.eh00.mapper;

import com.eh00.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CommentMapper {
    void insertComment(Comment comment);

    List<Comment> listCommentByArticleId(Integer articleId);

    Comment getCommentById(Integer id);

    List<Comment> listComment(HashMap<String, Object> criteria);

    /**
     * 根据文章获取评论
     *
     * @param articleIds
     * @return
     */
    List<Comment> getReceiveComment(List<Integer> articleIds);

    void deleteById(Integer id);

    void deleteByUserId(Integer id);

    void update(Comment comment);

    Integer countComment();

    List<Comment> listRecentComment(@Param("userId") Integer userId,
                                    @Param("limit") Integer limit);

    List<Comment> listChildComment(Integer id);

    /**
     * 根据文章ID删除
     * @param articleId 文章id
     * @return 影响行数
     */
    Integer deleteByArticleId(Integer articleId);
}
