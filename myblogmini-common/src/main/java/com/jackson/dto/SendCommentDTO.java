package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class SendCommentDTO implements Serializable {
    private Long articleId;
    private String content;

    public SendCommentDTO() {
    }

    public SendCommentDTO(Long articleId, String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendCommentDTO that = (SendCommentDTO) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, content);
    }

    @Override
    public String toString() {
        return "SendCommentDTO{" +
                "articleId=" + articleId +
                ", content='" + content + '\'' +
                '}';
    }
}
