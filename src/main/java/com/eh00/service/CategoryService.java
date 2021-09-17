package com.eh00.service;

import com.eh00.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 获得分类总数
     * @return
     */
    Integer countCategory();

    /**
     * 获得分类列表
     * @return
     */
    List<Category> listCategory();

    /**
     * 获得分类列表（带数量）
     * @return
     */
    List<Category> listCategoryWithCount();

    /**
     * 删除分类
     * @param id
     */
    void deleteCategory(Integer id);

    /**
     * 根据id查询分类信息
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     * 添加分类
     * @param category
     * @return
     */
    Category insertCategory(Category category);

    /**
     * 更新分类
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 根据分类名获取分类
     * @param name
     * @return
     */
    Category getCategoryByName(String name);


}
