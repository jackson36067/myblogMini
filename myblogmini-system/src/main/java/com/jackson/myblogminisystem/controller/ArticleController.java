package com.jackson.myblogminisystem.controller;

import com.jackson.dto.AddArticleDTO;
import com.jackson.dto.ArticleLikeDTO;
import com.jackson.myblogminisystem.service.ArticleService;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.ArticlePageVO;
import com.jackson.vo.ArticleVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/doLike")
    public void doArticleLike(@RequestBody ArticleLikeDTO articleLikeDTO) {
        articleService.doArticleLike(articleLikeDTO.getId());
    }

    /**
     * 获取用户点赞文章接口
     *
     * @return
     */
    @GetMapping("/like")
    public Result<List<ArticlePageVO>> getLikeArticle(@RequestParam(required = false) String title) {
        return articleService.getLikeArticle(title);
    }

    /**
     * 获取当前用户所有的收藏文章
     *
     * @param title
     * @return
     */
    @GetMapping("/favorite")
    public Result<List<ArticlePageVO>> getTotalCollectArticle(String title) {
        return articleService.getMyCollectArticle(title);
    }

    /**
     * 获取用户文章
     *
     * @param type  0:已展示 1:为展示
     * @param title
     * @return
     */
    @GetMapping("/my")
    public Result<List<ArticlePageVO>> getMyArticle(Integer type, @RequestParam(required = false) String title) {
        return articleService.getMyArticle(type, title);
    }

    /**
     * 获取文章详情接口
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ArticleVO> getArticleDetail(@PathVariable Long id) {
        return articleService.getArticleDetail(id);
    }

    /**
     * 收藏文章接口
     *
     * @param id
     */
    @PostMapping("/favorite/{id}")
    public void doFavoriteArticle(@PathVariable Long id) {
        articleService.doFavoriteArticle(id);
    }

    /**
     * 新增文章
     *
     * @param addArticleDTO
     */
    @PostMapping("/add")
    public void addArticle(@RequestBody AddArticleDTO addArticleDTO) {
        articleService.addArticle(addArticleDTO);
    }

    /**
     * 获取用户文章详情 current:0 用户自己的文章 1. 用户点赞的文章
     *
     * @param id
     * @param current
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result<List<ArticlePageVO>> getUserDetailArticle(@PathVariable Long id, Integer current) {
        return articleService.getUserDetailArticle(id, current);
    }

    /**
     * 获取用户访问文章历史记录详情
     * @return
     */
    @GetMapping("/history")
    public Result<List<ArticlePageVO>> getArticleBrowseHistory() {
return articleService.getArticleBrowseHistory();
    }
}
