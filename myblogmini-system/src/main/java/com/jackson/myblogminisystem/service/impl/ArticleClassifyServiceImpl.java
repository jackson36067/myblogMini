package com.jackson.myblogminisystem.service.impl;

import cn.hutool.json.JSONUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.dao.ArticleClassify;
import com.jackson.myblogminisystem.repository.ArticleClassifyRepository;
import com.jackson.myblogminisystem.service.ArticleClassifyService;
import com.jackson.result.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ArticleClassifyServiceImpl implements ArticleClassifyService {

    @Resource
    private ArticleClassifyRepository articleClassifyRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<List<ArticleClassify>> getClassifyList() {
        // 先从redis获取
        Set<String> members = stringRedisTemplate.opsForSet().members(RedisConstant.ARTICLE_CLASSIFY_KEY);
        List<ArticleClassify> list;
        // 存在直接返回redis中的数据
        if (members != null && !members.isEmpty()) {
            list = members
                    .stream()
                    .map(member -> JSONUtil.toBean(member, ArticleClassify.class))
                    .toList();
        } else {
            // 不存在,从数据库中获取,顺便保存到redis中
            list = articleClassifyRepository.findAll();
            List<String> list1 = list.stream().map(JSONUtil::toJsonStr).toList();
            stringRedisTemplate.opsForSet().add(RedisConstant.ARTICLE_CLASSIFY_KEY, list1.toArray(new String[0]));
        }
        return Result.success(list);
    }
}