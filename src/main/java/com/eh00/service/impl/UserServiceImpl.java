package com.eh00.service.impl;

import com.eh00.entity.User;
import com.eh00.mapper.ArticleMapper;
import com.eh00.mapper.CommentMapper;
import com.eh00.mapper.UserMapper;
import com.eh00.service.ArticleService;
import com.eh00.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public List<User> listUser() {
        List<User> userList = null;
        try {
            userList = userMapper.listUser();
            for(User user : userList) {
                Integer articleCount = articleMapper.countArticleByUser(user.getUserId());
                user.setArticleCount(articleCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得用户列表 失败, cause:{}", e);
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        try {
            user = userMapper.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查询用户信息 失败, cause:{}", e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try {
            userMapper.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改用户信息 失败, cause:{}", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        // 删除用户
        userMapper.delete(id);
        // 删除评论
        commentMapper.deleteByUserId(id);
        // 删除文章
        List<Integer> articleIds = articleMapper.listArticleIdsByUserId(id);
        if (articleIds != null && articleIds.size() > 0) {
            for (Integer articleId : articleIds) {
                articleService.deleteArticle(articleId);
            }
        }
    }

    @Override
    public User insertUser(User user) {
        try {
            userMapper.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加用户 失败, cause:{}", e);
        }
        return user;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        User user = null;
        try {
            user = userMapper.getUserByNameOrEmail(str);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据用户名和邮箱查询用户 失败, cause:{}", e);
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        try {
            user = userMapper.getUserByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据用户名查询用户 失败, cause:{}", e);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            user = userMapper.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据邮箱查询用户 失败, cause:{}", e);
        }
        return user;
    }
}
