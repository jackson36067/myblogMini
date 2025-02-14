package com.jackson.myblogminisystem.service;

import com.jackson.vo.ChatMessageVO;
import com.jackson.result.Result;
import com.jackson.vo.UnReadMessageVO;

import java.util.List;

public interface ChatMessageService {
    Result<ChatMessageVO> getChatMessageList(Long id) throws Exception;

    Result<List<UnReadMessageVO>> getUnReadMessageList();
}
