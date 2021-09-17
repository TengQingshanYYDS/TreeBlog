package com.eh00.service.impl;

import com.eh00.entity.Link;
import com.eh00.mapper.LinkMapper;
import com.eh00.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class linkServiceImpl implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public Integer countLink(Integer status) {
        Integer count = 0;
        try {
            count = linkMapper.countLink(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得链接总数 失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public List<Link> listLink(Integer status) {
        List<Link> linkList = null;
        try {
            linkList = linkMapper.listLink(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得链接列表 失败, cause:{}", e);
        }
        return linkList;
    }

    @Override
    public void insertLink(Link link) {
        try {
            linkMapper.insertLink(link);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加链接 失败, cause:{}", e);
        }
    }

    @Override
    public void deleteLink(Integer id) {
        try {
            linkMapper.deleteLink(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除链接 失败, cause:{}", e);
        }
    }

    @Override
    public void updateLink(Link link) {
        try {
            linkMapper.updateLink(link);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新链接 失败, cause:{}", e);
        }
    }

    @Override
    public Link getLinkById(Integer id) {
        Link link = null;
        try {
            link = linkMapper.getLinkById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查询链接 失败, cause:{}", e);
        }
        return link;
    }
}
