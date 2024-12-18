package com.jackson.myblogminisystem.repository;

import com.jackson.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Query("select a from Article a where a.articleClassify.id = :id")
    List<Article> findAllByClassifyId(Long id);

    @Query("select a from Article a where a.user.id = :userId")
    List<Article> findAllByUserId(Long userId);

    @Query("select a from Article a where a.isShow = :isShow")
    List<Article> findALlByIsShow(Boolean isShow);

    @Query("select a from Article a where a.user.id = :userId and a.title like concat('%', :title, '%')")
    List<Article> findAllByUserIdAndTitle(Long userId,String title);
}
