package com.eh00.service.impl;

import com.eh00.entity.Options;
import com.eh00.mapper.OptionsMapper;
import com.eh00.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OptionsServiceImpl implements OptionsService {
    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    @Cacheable(value = "default", key = "'options'")
    public Options getOptions() {
        return optionsMapper.getOptions();
    }

    @Override
    @CacheEvict(value = "default", key = "'options'")
    public void insertOptions(Options options) {
        optionsMapper.insert(options);
    }

    @CacheEvict(value = "default", key = "'options'")
    @Override
    public void updateOptions(Options options) {
        optionsMapper.update(options);
    }
}
