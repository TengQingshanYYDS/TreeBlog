package com.eh00.service.impl;

import com.eh00.entity.Article;
import com.eh00.entity.Comment;
import com.eh00.enums.ArticleStatus;
import com.eh00.mapper.ArticleMapper;
import com.eh00.mapper.CommentMapper;
import com.eh00.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public void insertComment(Comment comment) {
        try {
            commentMapper.insertComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建评论 失败：comment:{}, cause:{}", comment, e);
        }
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listCommentByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章id获取评论列表 失败：articleId:{}, cause:{}", articleId, e);
        }
        return commentList;
    }

    @Override
    public Comment getCommentById(Integer id) {
        Comment comment = null;
        try {
            commentMapper.listCommentByArticleId(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id获取评论 失败：id:{}, cause:{}", id, e);
        }
        return comment;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listComment(criteria);
            for(Comment comment : commentList){
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),
                        comment.getCommentId());
                comment.setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页获得评论 失败,pageIndex:{}, pageSize:{}, cause:{}", pageIndex, pageSize, e);
        }
        return new PageInfo<>(commentList);
    }

    @Override
    public PageInfo<Comment> listReceiveCommentByPage(Integer pageIndex, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = null;
        try {
            //获取用户发表文章
            List<Integer> articleIds = articleMapper.listArticleIdsByUserId(userId);
            if(articleIds != null && articleIds.size() != 0){
                commentList = commentMapper.getReceiveComment(articleIds);
                for (int i = 0; i < commentList.size(); i++) {
                    Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), commentList.get(i).getCommentArticleId());
                    commentList.get(i).setArticle(article);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("获得某个用户收到的评论 失败, cause:{}", e);
        }
        return new PageInfo<>(commentList);
    }

    @Override
    public void deleteComment(Integer id) {
        try {
            commentMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评论 失败：id:{}, cause:{}", id, e);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        try {
            commentMapper.update(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新评论 失败：cause:{}", e);
        }
    }

    @Override
    public Integer countComment() {
        Integer commentCount = null;
        try {
            commentMapper.countComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计评论数量 失败：cause:{}", e);
        }
        return commentCount;
    }

    @Override
    public List<Comment> listRecentComment(Integer userId, Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listRecentComment(userId, limit);
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最近评论 失败：cause:{}", e);
        }
        return commentList;
    }

    @Override
    public List<Comment> listChildComment(Integer id) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listChildComment(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论的子评论 失败：cause:{}", e);
        }
        return commentList;
    }
}
