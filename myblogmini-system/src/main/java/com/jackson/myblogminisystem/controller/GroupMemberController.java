package com.jackson.myblogminisystem.controller;

import com.jackson.dto.GroupMemberDTO;
import com.jackson.myblogminisystem.service.GroupMemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group/member")
public class GroupMemberController {

    @Resource
    private GroupMemberService groupMemberService;

    /**
     * 插入或者改变分促成员接口
     * @param groupMemberDTO
     */
    @PostMapping()
    public void insertOrChangeMemberToGroup(@RequestBody GroupMemberDTO groupMemberDTO) {
        groupMemberService.insertOrChangeMemberToGroup(groupMemberDTO);
    }
}
