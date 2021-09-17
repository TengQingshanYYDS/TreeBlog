package com.eh00.mapper;

import com.eh00.entity.Category;

import java.util.List;

public interface CategoryMapper {
    /**
     * 添加
     * @param category
     * @return
     */
    int insert(Category category);

    /**
     * 更新
     * @param category
     * @return
     */
    int update(Category category);

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int deleteCategory(Integer id);

    /**
     * 分类数量
     * @return
     */
    Integer countCategory();

    /**
     * 获得分类列表
     * @return
     */
    List<Category> listCategory();

    /**
     * 根据父分类找到子分类
     * @param id
     * @return
     */
    List<Category> findChildCategory(Integer id);

    /**
     * 根据分类名获取分类
     * @param name
     * @return
     */
    Category getCategoryByName(String name);
}
