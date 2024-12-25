package com.jackson.myblogminisystem.repository;

import com.jackson.dao.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    @Query("select g from GroupMember g where g.userGroup.id = :id")
    List<GroupMember> findAllByGroupId(Long id);

    // 根据 memberId 和 userId 查询分组成员
    @Query("SELECT gm FROM GroupMember gm " +
            "JOIN UserGroup g ON gm.userGroup.id = g.id " +
            "WHERE gm.memberId = :memberId AND g.user.id = :userId")
    GroupMember findByMemberId(Long memberId,Long userId);
}
