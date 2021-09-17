package com.eh00.controller.admin;

import com.eh00.entity.Menu;
import com.eh00.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表显示
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String menuList(Model model) {
        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList", menuList);
        return "Admin/Menu/index";
    }

    /**
     * 添加菜单内容提交
     * @param menu
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu) {
        menuService.insertMenu(menu);

        return "redirect:/admin/menu/index";
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id) {
        menuService.deleteMenu(id);
        return "redirect:/admin/menu/index";
    }

    /**
     * 编辑菜单页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editMenuView(@PathVariable("id") Integer id, Model model) {
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);

        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList", menuList);

        return "Admin/Menu/edit";
    }

    /**
     * 编辑菜单提交
     * @param menu
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu) {
        menuService.updateMenu(menu);
        return "redirect:/admin/menu/index";
    }
}
