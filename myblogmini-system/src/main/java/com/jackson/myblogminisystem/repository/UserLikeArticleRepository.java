package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserLikeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeArticleRepository extends JpaRepository<UserLikeArticle, Long> {

    UserLikeArticle findByUserIdAndArticleId(Long userId, Long articleId);

    List<UserLikeArticle> findAllByUserId(Long userId);
}
