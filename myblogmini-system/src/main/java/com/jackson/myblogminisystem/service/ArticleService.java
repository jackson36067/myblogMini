package com.jackson.myblogminisystem.service;

import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.ArticlePageVO;
import com.jackson.vo.ArticleVO;

import java.util.List;

public interface ArticleService {
    Result<PageResult> getArticleWithPaging(String title, Integer sign, Integer page, Integer pageSize);

    void doArticleLike(Long id);

    Result<List<ArticlePageVO>> getLikeArticle(String title);

    Result<List<ArticlePageVO>> getMyArticle(Integer type, String title);

    Result<ArticleVO> getArticleDetail(Long id);
}
