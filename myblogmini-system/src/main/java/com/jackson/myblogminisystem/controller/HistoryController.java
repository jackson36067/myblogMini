package com.jackson.myblogminisystem.controller;

import com.jackson.myblogminisystem.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    /**
     * 删除用户历史访问记录
     * @param current 0. 删除访问用户历史记录 1. 删除浏览文章历史记录
     */
    @DeleteMapping("/delete/{current}")
    public void deleteBrowseHistory(@PathVariable Integer current) {
        historyService.deleteBrowseHistory(current);
    }
}
