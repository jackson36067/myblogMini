package com.jackson.myblogminisystem.service;

import com.jackson.dao.ArticleClassify;
import com.jackson.result.Result;
import com.jackson.vo.ClassifyArticleVO;

import java.util.List;

public interface ArticleClassifyService {
    Result<List<ArticleClassify>> getClassifyList();

    Result<ClassifyArticleVO> getClassifyDetail(Long id);
}
