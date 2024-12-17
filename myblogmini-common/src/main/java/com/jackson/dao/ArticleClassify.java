package com.jackson.dao;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "article_classify")
@EntityListeners(AuditingEntityListener.class)
public class ArticleClassify implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "icon")
    private String icon;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

    public ArticleClassify() {
    }

    public ArticleClassify(Long id, String type, String icon, LocalDateTime createTime) {
        this.id = id;
        this.type = type;
        this.icon = icon;
        this.createTime = createTime;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleClassify that = (ArticleClassify) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(icon, that.icon) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, icon, createTime);
    }

    @Override
    public String toString() {
        return "ArticleClassify{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
