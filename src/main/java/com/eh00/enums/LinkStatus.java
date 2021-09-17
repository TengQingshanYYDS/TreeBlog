package com.eh00.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinkStatus {
    NORMAL(1, "显示"),
    HIDDEN(0, "隐藏");

    private Integer value;
    private String message;
}
