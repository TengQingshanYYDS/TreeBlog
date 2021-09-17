package com.eh00.service;

import com.eh00.entity.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PageService {
    /**
     * 获得页面列表
     * @param status 状态
     * @return
     */
    List<Page> listPage(Integer status);

    /**
     * 根据页面key获取页面
     * @param status
     * @param key
     * @return
     */
    Page getPageByKey(Integer status, String key);

    /**
     * 根据id获取页面
     * @param id
     * @return
     */
    Page getPageById(Integer id);

    /**
     * 添加页面
     * @param page
     */
    void insertPage(Page page);

    /**
     * 删除页面
     * @param id
     */
    void deletePage(Integer id);

    /**
     * 编辑页面
     * @param page
     */
    void updatePage(Page page);
}
