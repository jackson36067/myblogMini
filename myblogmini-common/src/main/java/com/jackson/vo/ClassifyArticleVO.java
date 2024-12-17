package com.jackson.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ClassifyArticleVO implements Serializable {
    private Long id;
    private String type;
    private List<ArticlePageVO> articlePageVOList;

    public ClassifyArticleVO() {
    }

    public ClassifyArticleVO(Long id, String title, List<ArticlePageVO> articlePageVOList) {
        this.id = id;
        this.type = title;
        this.articlePageVOList = articlePageVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ArticlePageVO> getArticlePageVOList() {
        return articlePageVOList;
    }

    public void setArticlePageVOList(List<ArticlePageVO> articlePageVOList) {
        this.articlePageVOList = articlePageVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassifyArticleVO that = (ClassifyArticleVO) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(articlePageVOList, that.articlePageVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, articlePageVOList);
    }

    @Override
    public String toString() {
        return "ClassifyArticleVO{" +
                "id=" + id +
                ", title='" + type + '\'' +
                ", articlePageVOList=" + articlePageVOList +
                '}';
    }
}
