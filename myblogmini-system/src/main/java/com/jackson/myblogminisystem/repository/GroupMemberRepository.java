package com.jackson.myblogminisystem.repository;

import com.jackson.dao.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    @Query("select g from GroupMember g where g.userGroup.id = :id")
    List<GroupMember> findAllByGroupId(Long id);
}
