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
    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;
    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;
    @Column(name = "cover_image")
    private String coverImage;
    @Column(name = "is_show")
    private Boolean isShow;
    @Column(name = "total_collect")
    private Integer totalCollect;

    @ManyToOne
    @JoinColumn(name = "classify_id", referencedColumnName = "id")
    private ArticleClassify articleClassify;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Article() {
    }

    public Article(Long id, String title, String content, Integer totalComment, Integer totalLike, Integer totalVisit, LocalDateTime createTime, LocalDateTime updateTime, String coverImage, User user, ArticleClassify articleClassify, Boolean isShow, Integer totalCollect) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.totalComment = totalComment;
        this.totalLike = totalLike;
        this.totalVisit = totalVisit;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.coverImage = coverImage;
        this.articleClassify = articleClassify;
        this.user = user;
        this.isShow = isShow;
        this.totalCollect = totalCollect;
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

    public ArticleClassify getArticleClassify() {
        return articleClassify;
    }

    public void setArticleClassify(ArticleClassify articleClassify) {
        this.articleClassify = articleClassify;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }

    public Integer getTotalCollect() {
        return totalCollect;
    }

    public void setTotalCollect(Integer totalCollect) {
        this.totalCollect = totalCollect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(totalComment, article.totalComment) && Objects.equals(totalLike, article.totalLike) && Objects.equals(totalVisit, article.totalVisit) && Objects.equals(createTime, article.createTime) && Objects.equals(updateTime, article.updateTime) && Objects.equals(coverImage, article.coverImage) && Objects.equals(articleClassify, article.articleClassify) && Objects.equals(user, article.user) & Objects.equals(isShow, article.isShow) & Objects.equals(totalCollect, article.totalCollect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, totalComment, totalLike, totalVisit, createTime, updateTime, coverImage, articleClassify, user, isShow, totalCollect);
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
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", coverImage='" + coverImage + '\'' +
                ", isShow=" + isShow +
                ", totalCollect=" + totalCollect +
                ", articleClassify=" + articleClassify +
                ", user=" + user +
                '}';
    }
}
