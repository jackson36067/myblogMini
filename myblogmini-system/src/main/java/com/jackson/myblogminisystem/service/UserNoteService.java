package com.jackson.myblogminisystem.service;

import com.jackson.dao.UserNote;
import com.jackson.result.Result;

public interface UserNoteService {
    void createOrUpdateNoteToUser(Long id,String note);

    Result<UserNote> getUserNote(Long id);
}
