package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ArticleVO implements Serializable {
    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;
    private String content;
    private String coverImage;
    private Integer totalComment;
    private Integer totalLike;
    private Integer totalVisit;
    private Boolean isLike;

    public ArticleVO() {
    }

    public ArticleVO(Long id, String title, LocalDateTime createTime, String content, String coverImage, Integer totalComment, Integer totalLike, Integer totalVisit, Boolean isLike) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.content = content;
        this.coverImage = coverImage;
        this.totalComment = totalComment;
        this.totalLike = totalLike;
        this.totalVisit = totalVisit;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
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

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean isLike) {
        this.isLike = isLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVO articleVO = (ArticleVO) o;
        return Objects.equals(id, articleVO.id) && Objects.equals(title, articleVO.title) && Objects.equals(createTime, articleVO.createTime) && Objects.equals(content, articleVO.content) && Objects.equals(coverImage, articleVO.coverImage) && Objects.equals(totalComment, articleVO.totalComment) && Objects.equals(totalLike, articleVO.totalLike) && Objects.equals(totalVisit, articleVO.totalVisit) && Objects.equals(isLike, articleVO.isLike);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createTime, content, coverImage, totalComment, totalLike, totalVisit, isLike);
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", totalComment=" + totalComment +
                ", totalLike=" + totalLike +
                ", totalVisit=" + totalVisit +
                ", isLike=" + isLike +
                '}';
    }
}
