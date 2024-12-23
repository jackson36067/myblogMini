package com.jackson.myblogminisystem.controller;

import com.jackson.dao.UserNote;
import com.jackson.dto.UserNoteDTO;
import com.jackson.myblogminisystem.service.UserNoteService;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
public class UserNoteController {
    @Resource
    private UserNoteService userNoteService;

    @PostMapping()
    public void createOrUpdateNoteToUser(@RequestBody UserNoteDTO userNoteDTO) {
        userNoteService.createOrUpdateNoteToUser(userNoteDTO.getId(), userNoteDTO.getNote());
    }

    @GetMapping("/{id}")
    public Result<UserNote> getUserNote(@PathVariable Long id) {
        return userNoteService.getUserNote(id);
    }
}
