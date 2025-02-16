package com.jackson.myblogminisystem.service;

import com.jackson.vo.ChatMessageVO;
import com.jackson.result.Result;
import com.jackson.vo.TotalUnReadMessageVO;

public interface ChatMessageService {
    Result<ChatMessageVO> getChatMessageList(Long id) throws Exception;

    Result<TotalUnReadMessageVO> getUnReadMessageList();
}
