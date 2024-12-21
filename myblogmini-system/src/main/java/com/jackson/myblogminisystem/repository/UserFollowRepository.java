package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
    List<UserFollow> findAllByUserId(Long userId);
    List<UserFollow> findAllByUserFollowId(Long userFollowId);
}
