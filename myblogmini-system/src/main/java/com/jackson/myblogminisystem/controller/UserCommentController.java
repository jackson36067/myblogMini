package com.jackson.myblogminisystem.controller;

import com.jackson.dto.SendCommentDTO;
import com.jackson.myblogminisystem.service.UserCommentService;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class UserCommentController {

    @Resource
    private UserCommentService userCommentService;

    /**
     * 根据文章id获取文章中的所有评论
     *
     * @param id 文章id
     * @return
     */
    @GetMapping("/list")
    public Result<PageResult> getUserComment(Long id) {
        return userCommentService.getUserComment(id);
    }

    /**
     * 用户在文章中发布评论接口
     * @param sendCommentDTO
     */
    @PostMapping("/send")
    public void sendComment(@RequestBody SendCommentDTO sendCommentDTO) {
        userCommentService.sendComment(sendCommentDTO);
    }
}
