package com.eh00.mapper;

import com.eh00.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    /**
     * 获得公告列表
     * @param status
     * @return
     */
    List<Notice> listNotice(@Param("status") Integer status);

    /**
     * 添加
     * @param notice
     * @return
     */
    int insert(Notice notice);

    /**
     * 根据id查询
     * @param noticeId
     * @return
     */
    Notice getNoticeById(Integer noticeId);

    /**
     * 更新
     * @param notice
     * @return
     */
    int update(Notice notice);

    /**
     * 根据ID删除
     * @param noticeId
     * @return
     */
    int deleteById(Integer noticeId);
}
