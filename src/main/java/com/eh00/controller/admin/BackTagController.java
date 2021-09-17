package com.eh00.controller.admin;

import com.eh00.entity.Tag;
import com.eh00.service.ArticleService;
import com.eh00.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("admin/tag")
public class BackTagController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
     * 后台标签列表页面显示
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        List<Tag> tagList = tagService.listTagWithCount();
        model.addAttribute("tagList", tagList);

        return "Admin/Tag/index";
    }

     /**
     * 后台添加分类页面提交
     * @param tag
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag) {
        tagService.insertTag(tag);

        return "redirect:/admin/tag";
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id) {
        // 只有该标签下不含有文章，才可以删除
        Integer count = articleService.countArticleByTagId(id);
        if (count == 0) {
            tagService.deleteTag(id);
        }

        return "redirect:/admin/tag";
    }

    /**
     * 编辑标签页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editTagView(@PathVariable("id") Integer id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);

        List<Tag> tagList = tagService.listTagWithCount();
        model.addAttribute("tagList", tagList);

        return "Admin/Tag/edit";
    }

    /**
     * 编辑标签提交
     * @param tag
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editTagSubmit(Tag tag) {
        tagService.updateTag(tag);

        return "redirect:/admin/tag";
    }

}
