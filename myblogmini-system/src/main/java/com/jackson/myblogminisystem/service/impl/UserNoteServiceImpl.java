package com.jackson.myblogminisystem.service.impl;

import com.jackson.context.BaseContext;
import com.jackson.dao.UserNote;
import com.jackson.myblogminisystem.repository.UserNoteRepository;
import com.jackson.myblogminisystem.service.UserNoteService;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserNoteServiceImpl implements UserNoteService {
    @Resource
    private UserNoteRepository userNoteRepository;

    @Override
    public void createOrUpdateNoteToUser(Long id, String note) {
        Long userId = BaseContext.getCurrentId();
        UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(userId, id);
        // 判断备注是否存在是否
        if (userNote != null) {
            // 存在 -> 判断note是否有文字
            if (StringUtils.hasText(note)) {
                userNote.setNote(note);
                userNoteRepository.saveAndFlush(userNote);
            } else {
                userNoteRepository.delete(userNote);
            }
        } else {
            if (StringUtils.hasText(note)) {
                userNote = new UserNote(null, note, userId, id);
                userNoteRepository.save(userNote);
            }
        }
    }

    @Override
    public Result<UserNote> getUserNote(Long id) {
        Long currentId = BaseContext.getCurrentId();
        UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, id);
        return Result.success(userNote);
    }
}
