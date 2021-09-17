package com.eh00.controller.admin;

import com.eh00.entity.Page;
import com.eh00.enums.PageStatus;
import com.eh00.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/page")
public class BackPageController {
    @Autowired
    private PageService pageService;

    /**
     * 后台页面列表显示
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        List<Page> pageList = pageService.listPage(null);
        model.addAttribute("pageList", pageList);

        return "Admin/Page/index";
    }

    /**
     * 后台添加页面 页面显示
     * @param model
     * @return
     */
    @RequestMapping("/insert")
    public String insertPageView(Model model) {
        return "Admin/Page/insert";
    }

    /**
     * 后台添加页面提交
     * @param page
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertPage(Page page) {
        // 判断别名是否存在
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());
        if (checkPage == null) {
            Date date = new Date();
            page.setPageCreateTime(date);
            page.setPageUpdateTime(date);
            page.setPageStatus(PageStatus.NORMAL.getValue());
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }

    /**
     * 删除页面
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deletePage(@PathVariable("id") Integer id) {
        pageService.deletePage(id);

        return "redirect:/admin/page";
    }

    /**
     * 编辑页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editPageView(@PathVariable("id") Integer id, Model model) {
        Page page = pageService.getPageById(id);
        model.addAttribute("page", page);

        return "Admin/Page/edit";
    }

    /**
     * 编辑页面提交
     * @param page
     * @return
     */
    @RequestMapping("/editSubmit")
    public String editPageSubmit(Page page) {
        // 判断别名是否重复
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());
        if (checkPage != null && Objects.equals(checkPage.getPageId(), page.getPageId())) {
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }

        return "redirect:/admin/page";
    }
}
