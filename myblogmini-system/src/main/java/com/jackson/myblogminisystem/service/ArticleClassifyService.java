package com.jackson.myblogminisystem.service;

import com.jackson.dao.ArticleClassify;
import com.jackson.result.Result;

import java.util.List;

public interface ArticleClassifyService {
    Result<List<ArticleClassify>> getClassifyList();
}
