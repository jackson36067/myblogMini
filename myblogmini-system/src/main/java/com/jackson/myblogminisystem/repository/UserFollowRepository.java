package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
    UserFollow findByUserId(Long userId);
}
