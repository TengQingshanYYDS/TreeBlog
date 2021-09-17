package com.eh00.service.impl;

import com.eh00.entity.Tag;
import com.eh00.mapper.ArticleTagRefMapper;
import com.eh00.mapper.TagMapper;
import com.eh00.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public Integer countTag() {
        Integer count = 0;
        try {
            count = tagMapper.countTag();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据ID获得标签 失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有标签 失败, cause:{}", e);
        }
        return tagList;
    }

    @Override
    public List<Tag> listTagWithCount() {
        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
            for(Tag tag : tagList) {
                Integer count = articleTagRefMapper.countArticleByTagId(tag.getTagId());
                tag.setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有标签(listTagWithCount) 失败, cause:{}", e);
        }
        return tagList;
    }

    @Override
    public Tag getTagById(Integer id) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据ID获得标签失败 失败, id:{}, cause:{}", id, e);
        }
        return tag;
    }

    @Override
    public Tag insertTag(Tag tag) {
        try {
            tagMapper.insertTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加标签失败, tag:{}, cause:{}", tag, e);
        }
        return tag;
    }

    @Override
    public void updateTag(Tag tag) {
        try {
            tagMapper.updateTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新标签失败, tag:{}, cause:{}", tag, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Integer id) {
        try {
            tagMapper.deleteTag(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除标签失败, id:{}, cause:{}", id, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
