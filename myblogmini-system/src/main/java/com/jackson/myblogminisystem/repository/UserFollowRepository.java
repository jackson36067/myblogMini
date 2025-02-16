package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
    List<UserFollow> findAllByUserId(Long userId);

    List<UserFollow> findAllByUserFollowId(Long userFollowId);

    @Query("select u from UserFollow u where u.userId = :userId order by u.createTime desc")
    List<UserFollow> findAllByUserIdOOrderByCreateTimeDesc(Long userId);

    @Query("select u from UserFollow u where u.userId = :userId order by u.createTime asc")
    List<UserFollow> findAllByUserIdOOrderByCreateTimeAsc(Long userId);

    UserFollow findByUserIdAndUserFollowId(Long userId, Long userFollowId);

    @Query("select u from UserFollow u where u.userFollowId = :userFollowId order by u.createTime desc")
    List<UserFollow> findAllByUserFollowIdAndOrderByCreateTimeAsc(Long userFollowId);
}
