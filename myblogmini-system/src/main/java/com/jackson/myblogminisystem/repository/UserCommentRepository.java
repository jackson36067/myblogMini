package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCommentRepository extends JpaRepository<UserComment, Long>, JpaSpecificationExecutor<UserComment> {

    @Query("select u from UserComment u where u.article.id = :id ")
    Page<UserComment> findAllByArticleId(Long id, Pageable pageable);

    @Query("select u from UserComment u where u.article.id = :id order by u.totalLike desc")
    List<UserComment> findAllByArticleId(Long id);


}
