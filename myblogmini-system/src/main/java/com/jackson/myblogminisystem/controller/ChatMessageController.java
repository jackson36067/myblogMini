package com.jackson.myblogminisystem.controller;

import com.jackson.vo.ChatMessageVO;
import com.jackson.myblogminisystem.service.ChatMessageService;
import com.jackson.result.Result;
import com.jackson.vo.UnReadMessageVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Resource
    private ChatMessageService chatMessageService;

    /**
     * 获取聊天记录
     *
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public Result<ChatMessageVO> getChatMessageList(@PathVariable Long id) throws Exception {
        return chatMessageService.getChatMessageList(id);
    }


    /**
     * 获取未读消息列表
     * @return
     */
    @GetMapping("/unread")
    public Result<List<UnReadMessageVO>> getUnReadMessageList() {
        return chatMessageService.getUnReadMessageList();
    }
}
