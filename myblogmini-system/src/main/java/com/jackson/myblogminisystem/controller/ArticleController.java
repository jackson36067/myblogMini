package com.jackson.myblogminisystem.controller;

import com.jackson.dto.ArticleLikeDTO;
import com.jackson.myblogminisystem.service.ArticleService;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 分页获取文章
     *
     * @param sign     0.最新 1.热门 2.标签
     * @param pageSize
     * @param page
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> getArticleWithPaging(@RequestParam(required = false) String title,
                                                   Integer sign,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "1") Integer page) {
        return articleService.getArticleWithPaging(title, sign, page, pageSize);
    }

    /**
     * 点赞功能
     *
     * @param articleLikeDTO
     */
    @PostMapping("/like")
    public void doArticleLike(@RequestBody ArticleLikeDTO articleLikeDTO) {
        articleService.doArticleLike(articleLikeDTO.getId());
    }
}
