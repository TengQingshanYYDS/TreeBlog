package com.eh00.service;

import com.eh00.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 获得标签总数
     * @return
     */
    Integer countTag();

    /**
     * 获得标签列表
     * @return
     */
    List<Tag> listTag();

    /**
     * 获得标签列表
     * @return
     */
    List<Tag> listTagWithCount();

    /**
     * 根据id获得标签信息
     * @param id
     * @return
     */
    Tag getTagById(Integer id);

    /**
     * 添加标签
     * @param tag
     * @return
     */
    Tag insertTag(Tag tag);

    /**
     * 修改标签
     * @param tag
     */
    void updateTag(Tag tag);

    /**
     * 删除标签
     * @param id
     */
    void deleteTag(Integer id);
}
