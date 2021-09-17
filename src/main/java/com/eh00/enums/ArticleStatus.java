package com.eh00.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleStatus {
    PUBLISH(1, "已发布"),
    DRAFT(0, "草稿");

    private Integer value;
    private String message;
}
