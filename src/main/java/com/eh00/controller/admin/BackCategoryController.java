package com.eh00.controller.admin;

import com.eh00.entity.Category;
import com.eh00.service.ArticleService;
import com.eh00.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 后台分类列表表示
     * @param model
     * @return
     */
    @RequestMapping
    public String categoryList(Model model) {
        List<Category> categoryList = categoryService.listCategoryWithCount();
        model.addAttribute("categoryList", categoryList);
        return "Admin/Category/index";
    }

    /**
     * 后台添加分类提交
     * @param category
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertCategorySubmit(Category category) {
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        // 禁止删除有文章的分类
        int count = articleService.countArticleByCategoryId(id);

        if (count == 0) {
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";
    }

    /**
     * 编辑分类页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editCategoryView(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);

        List<Category> categoryList = categoryService.listCategoryWithCount();
        model.addAttribute("categoryList", categoryList);

        return "Admin/Category/edit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCategorySubmit(Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
}
