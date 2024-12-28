package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("select u from UserGroup u where u.user.id = :id")
    List<UserGroup> findAllByUserId(Long id);

    UserGroup findByGroupName(String groupName);
}
