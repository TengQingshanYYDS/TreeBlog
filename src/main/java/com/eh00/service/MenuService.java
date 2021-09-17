package com.eh00.service;

import com.eh00.entity.Menu;

import java.util.List;


public interface MenuService {
    /**
     * 获得菜单列表
     * @return
     */
    List<Menu> listMenu();

    /**
     * 添加菜单
     */
    Menu insertMenu(Menu menu);

    /**
     * 删除菜单
     */
    void deleteMenu(Integer id);

    /**
     * 更新菜单
     */
    void updateMenu(Menu menu);

    /**
     * 根据id获取菜单
     */
    Menu getMenuById(Integer id);
}
