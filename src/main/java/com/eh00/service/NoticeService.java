package com.eh00.service;

import com.eh00.entity.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 获得公告列表
     * @param status
     * @return
     */
    List<Notice> listNotice(Integer status);

    /**
     * 添加公告
     * @param notice
     */
    void insertNotice(Notice notice);

    /**
     * 删除公告
     * @param id
     */
    void deleteNotice(Integer id);

    /**
     * 更新公告
     * @param notice
     */
    void updateNotice(Notice notice);

    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    Notice getNoticeById(Integer id);
}
