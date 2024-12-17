package com.jackson.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_like_article")
public class UserLikeArticle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "article_id")
    private Long articleId;

    public UserLikeArticle() {
    }

    public UserLikeArticle(Long id, Long userId, Long articleId) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLikeArticle that = (UserLikeArticle) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, articleId);
    }

    @Override
    public String toString() {
        return "UserLikeArticle{" +
                "id=" + id +
                ", userId=" + userId +
                ", articleId=" + articleId +
                '}';
    }
}
