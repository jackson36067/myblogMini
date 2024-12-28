package com.jackson.myblogminisystem.controller;

import com.jackson.dto.GroupMemberDTO;
import com.jackson.myblogminisystem.service.GroupMemberService;
import com.jackson.result.Result;
import com.jackson.vo.GroupMemberVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group/member")
public class GroupMemberController {

    @Resource
    private GroupMemberService groupMemberService;

    /**
     * 插入或者改变成员分组接口
     *
     * @param groupMemberDTO
     */
    @PostMapping()
    public void insertOrChangeMemberToGroup(@RequestBody GroupMemberDTO groupMemberDTO) {
        groupMemberService.insertOrChangeMemberToGroup(groupMemberDTO);
    }

    /**
     * 将成员添加进分组或者从分组中删除
     *
     * @param groupMemberDTO
     */
    @PostMapping("/change")
    public void addOrDeleteMemberToGroup(@RequestBody GroupMemberDTO groupMemberDTO) {
        groupMemberService.addOrDeleteMemberToGroup(groupMemberDTO);
    }

    /**
     * 根据分组id获取分组成员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<List<GroupMemberVO>> getGroupMemberById(@PathVariable Long id) {
        return groupMemberService.getGroupMemberById(id);
    }

    /**
     * 从分组中移除成员
     * @param groupMemberDTO
     */
    @PostMapping("/remove")
    public void removeMemberFromGroup(@RequestBody GroupMemberDTO groupMemberDTO) {
        groupMemberService.removeMemberFromGroup(groupMemberDTO); 
    }
}
