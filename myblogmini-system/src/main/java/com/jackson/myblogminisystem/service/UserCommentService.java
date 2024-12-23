package com.jackson.myblogminisystem.service;

import com.jackson.dto.SendCommentDTO;
import com.jackson.result.PageResult;
import com.jackson.result.Result;

public interface UserCommentService {
    Result<PageResult> getUserComment(Long id,Boolean isAll);

    void sendComment(SendCommentDTO sendCommentDTO);

    void doLikeComment(Long id);
}
