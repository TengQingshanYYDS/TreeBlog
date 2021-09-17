package com.eh00.mapper;

import com.eh00.entity.Tag;

import java.util.List;

public interface TagMapper {
    Integer countTag();

    List<Tag> listTag();

    Tag getTagById(Integer id);

    Tag insertTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(Integer id);
}
