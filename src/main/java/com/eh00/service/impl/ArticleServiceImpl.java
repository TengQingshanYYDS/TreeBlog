package com.eh00.service.impl;

import com.eh00.entity.*;
import com.eh00.enums.ArticleCommentStatus;
import com.eh00.mapper.*;
import com.eh00.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        try {
            count = articleMapper.countArticle(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据状态统计文章数, status:{}, cause:{}", status, e);
        }
        return count;
    }

    @Override
    public Integer countArticleComment() {
        Integer count = 0;
        try {
            count = articleMapper.countArticleComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章评论数失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleView() {
        Integer count = 0;
        try {
            count = articleMapper.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章访问量失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count = 0;
        try {
            count = articleCategoryRefMapper.countArticleByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类统计文章数量失败, categoryId:{}, cause:{}", categoryId, e);
        }
        return count;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        Integer count = 0;
        try {
            count = articleTagRefMapper.countArticleByTagId(tagId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据标签统计文章数量失败, tagId:{}, cause:{}", tagId, e);
        }
        return count;
    }

    @Override
    public List<Article> listRecentArticle(Integer userId, Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listArticleByLimit(userId, limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最新文章失败, userId:{}, cause:{}", userId, e);
        }
        return articleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleMapper.update(article);

        List<Tag> tagList = article.getTagList();
        if(tagList != null && tagList.size() > 0) {
            //删除标签和文章关联
            articleTagRefMapper.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for (Tag tag : article.getTagList()) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(),
                        tag.getTagId());
                articleTagRefMapper.insert(articleTagRef);
            }
        }

        List<Category> categoryList = article.getCategoryList();
        if(categoryList != null && categoryList.size() > 0) {
            //删除分类和文章关联
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
            //添加分类和文章关联
            for (Category category : article.getCategoryList()) {
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),
                        category.getCategoryId());
                articleCategoryRefMapper.insert(articleCategoryRef);
            }
        }
    }

    @Override
    public void updateArticle(Article article) {
        try {
            articleMapper.update(article);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改文章简单信息失败, cause:{}", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer id) {
        //删除文章
        articleMapper.deleteById(id);
        //删除分类
        articleCategoryRefMapper.deleteByArticleId(id);
        //删除标签
        articleTagRefMapper.deleteByArticleId(id);
        //删除评论
        commentMapper.deleteByArticleId(id);
    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex,
                                         Integer pageSize,
                                         HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.findAll(criteria);
        for(int i = 0; i < articleList.size(); i++){
            //封装categoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0){
                categoryList = new ArrayList<>();
                categoryList.add(Category.getDefaultCategory());
            }
            articleList.get(i).setCategoryList(categoryList);

            articleList.get(i).setUser(userMapper.getUserById(articleList.get(i).getArticleUserId()));
        }

        return new PageInfo<>(articleList);
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        Article article = null;
        try {
            article = articleMapper.getArticleByStatusAndId(status, id);
            if (article != null) {
                List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
                List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
                article.setCategoryList(categoryList);
                article.setTagList(tagList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文章详情页面显示 失败, status:{}, id:{}, cause:{}", status, id, e);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listArticleByViewCount(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文章详情页面显示 失败, cause:{}", e);
        }
        return articleList;
    }

    @Override
    public Article getAfterArticle(Integer id) {
        Article article = null;
        try {
            article = articleMapper.getAfterArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得下一文章 失败, id:{}, cause:{}", id, e);
        }
        return article;
    }

    @Override
    public Article getPreArticle(Integer id) {
        Article article = null;
        try {
            article = articleMapper.getPreArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得上一文章 失败, id:{}, cause:{}", id, e);
        }
        return article;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listRandomArticle(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得随机文章 失败, cause:{}", e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listArticleByCommentCount(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论数较多的文章 失败, cause:{}", e);
        }
        return articleList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        Date createTime = new Date();
        article.setArticleCreateTime(createTime);
        article.setArticleUpdateTime(createTime);
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        articleMapper.insert(article);

        //添加分类和文章关联
        for(Category category : article.getCategoryList()) {
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),
                    category.getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }
        //添加标签和文章关联
        for(Tag tag : article.getTagList()) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(),
                    tag.getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        try {
            articleMapper.updateCommentCount(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改文章简单信息失败, articleId:{}, cause:{}",articleId, e);
        }
    }

    @Override
    public Article getLastUpdateArticle() {
        Article article = null;
        try {
            article = articleMapper.getLastUpdateArticle();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最后更新记录 失败, cause:{}", e);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if(cateIds == null || cateIds.size() == 0) {
            return null;
        }
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findArticleByCategoryIds(cateIds, limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论数较多的文章 失败, cause:{}", e);
        }
        return articleList;
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        List<Integer> categoryIdList = null;
        try {
            categoryIdList = articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章ID获得分类ID列表 失败, cause:{}", e);
        }
        return categoryIdList;
    }

    @Override
    public List<Article> listAllNotWithContent() {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listAllNotWithContent();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有的文章 失败, cause:{}", e);
        }
        return articleList;
    }
}
