package com.eh00.service;

import com.eh00.entity.Options;


public interface OptionsService {
    /**
     * 获得基本信息
     * @return
     */
    Options getOptions();

    /**
     * 新建基本信息
     * @param options
     */
    void insertOptions(Options options);

    /**
     * 更新基本信息
     */
    void updateOptions(Options options);
}
