package com.jackson.myblogminisystem.service;

import com.jackson.result.PageResult;
import com.jackson.result.Result;

public interface ArticleService {
    Result<PageResult> getArticleWithPaging(String title, Integer sign, Integer page, Integer pageSize);

    void doArticleLike(Long id);
}
