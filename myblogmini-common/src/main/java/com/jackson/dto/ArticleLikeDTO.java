package com.jackson.dto;

import java.io.Serializable;

public class ArticleLikeDTO implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
