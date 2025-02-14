package com.jackson.myblogminisystem.repository;

import com.jackson.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<Long> {
    User findByOpenid(String openid);

    @Query("select u.nickName from User u where u.id = :id")
    String findNickNameById(Long id);

    // 重置所有用户的 canSignIn 字段为 false
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isSignIn = false")
    void resetAllUsersSignIn();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isOnline = :isOnline WHERE u.id = :id")
    void updateUserIsOnlineById(@Param("id") Long id, @Param("isOnline") Boolean isOnline);

    @Query("SELECT u.isOnline FROM User u WHERE u.id = :id")
    boolean findUserOnline(Long id);
}
