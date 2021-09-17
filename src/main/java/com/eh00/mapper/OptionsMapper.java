package com.eh00.mapper;

import com.eh00.entity.Options;

public interface OptionsMapper {
    /**
     * 根据id删除
     * @param optionId 系统设置id
     * @return 影响行数
     */
    int deleteById(Integer optionId);

    /**
     * 添加
     * @param options 系统设置
     * @return 影响行数
     */
    int insert(Options options);

    /**
     * 根据id查询
     * @param optionId 系统设置id
     * @return 影响行数
     */
    Options getOptionsById(Integer optionId);

    /**
     * 更新
     * @param options 系统信息
     * @return 影响行数
     */
    int update(Options options);

    /**
     * 获得记录
     * @return 系统信息
     */
    Options getOptions();
}
