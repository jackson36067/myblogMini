package com.jackson.myblogminisystem.service;

import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.ArticlePageVO;

import java.util.List;

public interface ArticleService {
    Result<PageResult> getArticleWithPaging(String title, Integer sign, Integer page, Integer pageSize);

    void doArticleLike(Long id);

    Result<List<ArticlePageVO>> getLikeArticle();

    Result<List<ArticlePageVO>> getMyArticle();
}
