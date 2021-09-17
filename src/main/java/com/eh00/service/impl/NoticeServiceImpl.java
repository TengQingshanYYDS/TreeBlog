package com.eh00.service.impl;

import com.eh00.entity.Notice;
import com.eh00.mapper.NoticeMapper;
import com.eh00.service.NoticeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice(Integer status) {
        return noticeMapper.listNotice(status);
    }

    @Override
    public void insertNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public void deleteNotice(Integer id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.update(notice);
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.getNoticeById(id);
    }
}
