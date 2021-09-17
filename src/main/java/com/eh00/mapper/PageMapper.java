package com.eh00.mapper;

import com.eh00.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageMapper {
    List<Page> listPage(Integer status);

    Page getPageByKey(@Param("status") Integer status,
                      @Param("key") String key);

    Page getPageById(Integer id);

    void insert(Page page);

    void deleteById(Integer id);

    void update(Page page);
}
