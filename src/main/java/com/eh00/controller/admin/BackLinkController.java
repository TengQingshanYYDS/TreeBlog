package com.eh00.controller.admin;

import com.eh00.entity.Link;
import com.eh00.enums.LinkStatus;
import com.eh00.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/link")
public class BackLinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 后台链接列表显示
     * @param model
     * @return
     */
    @RequestMapping("")
    public String linkList(Model model) {
        List<Link> linkList = linkService.listLink(null);
        model.addAttribute("linkList", linkList);
        return "Admin/Link/index";
    }

    /**
     * 后台添加链接页面显示
     * @param model
     * @return
     */
    @RequestMapping("insert")
    public String insertLinkView(Model model) {
        List<Link> linkList = linkService.listLink(null);
        model.addAttribute("linkList", linkList);
        return "Admin/Link/insert";
    }

    /**
     * 后台添加链接页面提交
     * @param link
     * @return
     */
    @RequestMapping(value = "insertSubmit", method = RequestMethod.POST)
    public String insertLinkSubmit(Link link) {
        Date date = new Date();
        link.setLinkCreateTime(date);
        link.setLinkUpdateTime(date);
        link.setLinkStatus(LinkStatus.NORMAL.getValue());
        linkService.insertLink(link);
        return "redirect:/admin/link/insert";
    }

    /**
     * 删除链接
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id) {
        linkService.deleteLink(id);
        return "redirect:/admin/link/index";
    }

    /**
     * 编辑链接页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editLinkView(@PathVariable("id") Integer id, Model model) {
        Link linkCustom = linkService.getLinkById(id);
        model.addAttribute("linkCustom", linkCustom);

        List<Link> linkList = linkService.listLink(null);
        model.addAttribute("linkList", linkList);

        return "Admin/Link/edit";
    }

    /**
     * 编辑链接提交
     * @param link
     * @return
     */
    @RequestMapping(value = "editSubmit", method = RequestMethod.POST)
    public String editLinkSubmit(Link link) {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link/index";
    }

}
