package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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
    private String avatar;
    private String nickName;
    private Boolean isLike;
    private Boolean isFollow;
    private List<String> tags;

    public ArticleVO() {
    }

    public ArticleVO(Long id, String title, LocalDateTime createTime, String content, String coverImage, Integer totalComment, Integer totalLike, Integer totalVisit, String avatar, String nickName, Boolean isLike,Boolean isFollow,List<String> tags) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.content = content;
        this.coverImage = coverImage;
        this.totalComment = totalComment;
        this.totalLike = totalLike;
        this.totalVisit = totalVisit;
        this.avatar = avatar;
        this.nickName = nickName;
        this.isLike = isLike;
        this.isFollow = isFollow;
        this.tags = tags;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVO articleVO = (ArticleVO) o;
        return Objects.equals(id, articleVO.id) && Objects.equals(title, articleVO.title) && Objects.equals(createTime, articleVO.createTime) && Objects.equals(content, articleVO.content) && Objects.equals(coverImage, articleVO.coverImage) && Objects.equals(totalComment, articleVO.totalComment) && Objects.equals(totalLike, articleVO.totalLike) && Objects.equals(totalVisit, articleVO.totalVisit) && Objects.equals(avatar, articleVO.avatar) && Objects.equals(nickName, articleVO.nickName) && Objects.equals(isLike, articleVO.isLike) && Objects.equals(isFollow, articleVO.isFollow) && Objects.equals(tags, articleVO.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createTime, content, coverImage, totalComment, totalLike, totalVisit, avatar, nickName, isLike, isFollow, tags);
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
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isLike=" + isLike +
                ", isFollow=" + isFollow +
                ", tags=" + tags +
                '}';
    }
}
