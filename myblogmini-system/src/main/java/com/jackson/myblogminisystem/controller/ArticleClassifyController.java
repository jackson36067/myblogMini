package com.jackson.myblogminisystem.controller;

import com.jackson.dao.ArticleClassify;
import com.jackson.myblogminisystem.service.ArticleClassifyService;
import com.jackson.result.Result;
import com.jackson.vo.ClassifyArticleVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 获取分类文章详情接口
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ClassifyArticleVO> getClassifyDetail(@PathVariable Long id) {
        return articleClassifyService.getClassifyDetail(id);
    }
}
