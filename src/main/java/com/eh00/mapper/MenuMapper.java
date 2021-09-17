package com.eh00.mapper;

import com.eh00.entity.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 删除
     * @param menuId 菜单id
     * @return 影响行数
     */
    int deleteById(Integer menuId);

    /**
     * 添加
     * @param menu 菜单
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 根据id查询
     * @param menuId id
     * @return 菜单
     */
    Menu getMenuById(Integer menuId);

    /**
     * 更新
     * @param menu 菜单
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 菜单列表
     * @return 列表
     */
    List<Menu> listMenu();
}
