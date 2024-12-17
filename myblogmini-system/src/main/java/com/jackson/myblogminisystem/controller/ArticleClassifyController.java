package com.jackson.myblogminisystem.controller;

import com.jackson.dao.ArticleClassify;
import com.jackson.myblogminisystem.service.ArticleClassifyService;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classify")
public class ArticleClassifyController {

    @Resource
    private ArticleClassifyService articleClassifyService;

    @GetMapping("/list")
    public Result<List<ArticleClassify>> getClassifyList() {
        return articleClassifyService.getClassifyList();
    }
}
