package com.jackson.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AddArticleDTO implements Serializable {
    private String coverImage;
    private String title;
    private String content;
    private Boolean isShow;
    private List<String> tags;
    private String type;
    private String description;

    public AddArticleDTO() {
    }

    public AddArticleDTO(String coverImage, String title, String content, Boolean isShow, List<String> tags,String type,String description) {
        this.coverImage = coverImage;
        this.title = title;
        this.content = content;
        this.isShow = isShow;
        this.tags = tags;
        this.type = type;
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImg) {
        this.coverImage = coverImg;
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

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddArticleDTO that = (AddArticleDTO) o;
        return Objects.equals(coverImage, that.coverImage) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(isShow, that.isShow) && Objects.equals(tags, that.tags) && Objects.equals(type, that.type) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coverImage, title, content, isShow, tags, type, description);
    }

    @Override
    public String toString() {
        return "AddArticleDTO{" +
                "coverImage='" + coverImage + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isShow=" + isShow +
                ", tags=" + tags +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
