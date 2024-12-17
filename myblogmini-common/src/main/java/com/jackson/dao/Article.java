package com.jackson.dao;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "total_comment")
    private Integer totalComment;
    @Column(name = "total_like")
    private Integer totalLike;
    @Column(name = "total_visit")
    private Integer totalVisit;
    @Column(name = "classify")
    private  String classify;
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;
    @Column(name = "cover_image")
    private String coverImage;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public Article() {
    }

    public Article(Long id, String title, String content, Integer totalComment, Integer totalLike, Integer totalVisit, String classify, LocalDateTime createTime, LocalDateTime updateTime, String coverImage, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.totalComment = totalComment;
        this.totalLike = totalLike;
        this.totalVisit = totalVisit;
        this.classify = classify;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.coverImage = coverImage;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }

    public Integer getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(Integer totalVisit) {
        this.totalVisit = totalVisit;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(totalComment, article.totalComment) && Objects.equals(totalLike, article.totalLike) && Objects.equals(totalVisit, article.totalVisit) && Objects.equals(classify, article.classify) && Objects.equals(createTime, article.createTime) && Objects.equals(updateTime, article.updateTime) && Objects.equals(coverImage, article.coverImage) && Objects.equals(user, article.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, totalComment, totalLike, totalVisit, classify, createTime, updateTime, coverImage, user);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", totalComment=" + totalComment +
                ", totalLike=" + totalLike +
                ", totalVisit=" + totalVisit +
                ", classify='" + classify + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", coverImage='" + coverImage + '\'' +
                ", user=" + user +
                '}';
    }
}
