package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNoteRepository extends JpaRepository<UserNote, Long> {
    UserNote findByUserIdAndUserNoteId(Long userId, Long userNoteId);
}
