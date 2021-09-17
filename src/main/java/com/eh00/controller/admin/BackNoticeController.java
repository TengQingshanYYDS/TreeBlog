package com.eh00.controller.admin;

import com.eh00.entity.Notice;
import com.eh00.enums.NoticeStatus;
import com.eh00.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 后台公告列表显示
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";
    }

    /**
     * 添加公告页面显示
     * @param model
     * @return
     */
    @RequestMapping("/insert")
    public String insertNoticeView(Model model) {
        return "Admin/Notice/insert";
    }

    /**
     * 添加公告提交
     * @param notice
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) {
        Date date = new Date();
        notice.setNoticeCreateTime(date);
        notice.setNoticeUpdateTime(date);
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);

        return "redirect:/admin/notice/index";
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);

        return "redirect:/admin/notice/index";
    }

    /**
     * 编辑公告页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editNoticeView(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);

        return "Admin/Notice/edit";
    }

    /**
     * 编辑公告提交
     * @param notice
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);

        return "redirect:/admin/notice";
    }
}
