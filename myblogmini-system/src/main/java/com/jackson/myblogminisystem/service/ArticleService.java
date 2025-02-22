package com.jackson.myblogminisystem.service;

import com.jackson.dto.AddArticleDTO;
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

    Result<List<ArticlePageVO>> getMyCollectArticle(String title);

    Result<ArticleVO> getArticleDetail(Long id);

    void doFavoriteArticle(Long id);

    void addArticle(AddArticleDTO addArticleDTO);

    Result<List<ArticlePageVO>> getUserDetailArticle(Long id, Integer current);

    Result<List<ArticlePageVO>> getArticleBrowseHistory();

}
