package com.jackson.dao;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_comment")
@EntityListeners(AuditingEntityListener.class)
public class UserComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;
    @Column(name = "total_like")
    private Integer totalLike;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id",referencedColumnName = "id")
    private Article article;

    public UserComment() {
    }

    public UserComment(Long id, String content, LocalDateTime createTime, User user ,Article article) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.user = user;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComment that = (UserComment) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(createTime, that.createTime) && Objects.equals(totalLike, that.totalLike) && Objects.equals(user, that.user) && Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createTime, totalLike, user, article);
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", localDateTime=" + createTime +
                ", totalLike=" + totalLike +
                ", user=" + user +
                ", article=" + article +
                '}';
    }
}
