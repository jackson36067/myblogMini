package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ArticlePageVO implements Serializable {
    private Long id;
    private String title;
    private String coverImage;
    private String author;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createTime;
    private Integer totalVisit;
    private Integer totalLike;
    private Integer totalComment;
    private Boolean isLike; // 该文章是否被单前用户点赞

    public ArticlePageVO() {
    }

    public ArticlePageVO(Long id, String title, String coverImage, String author, LocalDate createTime, Integer totalVisit, Integer totalLike, Integer totalComment,Boolean isLike) {
        this.id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.author = author;
        this.createTime = createTime;
        this.totalVisit = totalVisit;
        this.totalLike = totalLike;
        this.totalComment = totalComment;
        this.isLike = isLike;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public Integer getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(Integer totalVisit) {
        this.totalVisit = totalVisit;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }

    public Integer getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean like) {
        isLike = like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlePageVO that = (ArticlePageVO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(coverImage, that.coverImage) && Objects.equals(author, that.author) && Objects.equals(createTime, that.createTime) && Objects.equals(totalVisit, that.totalVisit) && Objects.equals(totalLike, that.totalLike) && Objects.equals(totalComment, that.totalComment) && Objects.equals(isLike, that.isLike);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, coverImage, author, createTime, totalVisit, totalLike, totalComment, isLike);
    }

    @Override
    public String toString() {
        return "ArticlePageVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", totalVisit=" + totalVisit +
                ", totalLike=" + totalLike +
                ", totalComment=" + totalComment +
                ", isLike=" + isLike +
                '}';
    }
}
