package com.eh00.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTagRef implements Serializable {
    public static final long serialVersionUID = 766120672388711229L;

    private Integer articleId;

    private Integer tagId;

    public ArticleTagRef() {
    }

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
