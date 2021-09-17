package com.eh00.mapper;

import com.eh00.entity.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkMapper {
    Integer countLink(@Param("status") Integer status);

    List<Link> listLink(@Param("status") Integer status);

    int insertLink(Link link);

    int deleteLink(Integer id);

    int updateLink(Link link);

    Link getLinkById(Integer id);
}
