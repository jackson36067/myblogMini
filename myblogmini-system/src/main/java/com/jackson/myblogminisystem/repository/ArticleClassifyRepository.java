package com.jackson.myblogminisystem.repository;

import com.jackson.dao.ArticleClassify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleClassifyRepository extends JpaRepository<ArticleClassify, Long> {
    ArticleClassify findByType(String type);
}
