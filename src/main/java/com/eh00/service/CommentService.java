package com.eh00.service;

import com.eh00.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

public interface CommentService {
    /**
     * 添加评论
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 根据文章id获取评论列表
     * @param articleId
     * @return
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    Comment getCommentById(Integer id);

    /**
     * 获取所有评论列表
     * @param pageIndex 第几页开始
     * @param pageSize 一页显示数量
     * @param criteria 条件
     * @return
     */
    PageInfo<Comment> listCommentByPage(Integer pageIndex,
                                        Integer pageSize,
                                        HashMap<String, Object> criteria);

    /**
     * 获得某个用户收到的评论
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Comment> listReceiveCommentByPage(Integer pageIndex,
                                               Integer pageSize,
                                               Integer userId);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Integer id);

    /**
     * 修改评论
     * @param comment
     */
    void updateComment(Comment comment);

    /**
     * 统计评论数
     * @return
     */
    Integer countComment();

    /**
     * 获得最近评论
     * @param userId
     * @param limit
     * @return
     */
    List<Comment>  listRecentComment(Integer userId, Integer limit);

    /**
     * 获得评论的子评论
     * @param id
     * @return
     */
    List<Comment> listChildComment(Integer id);

}
