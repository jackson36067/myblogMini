package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.Article;
import com.jackson.dao.ArticleClassify;
import com.jackson.myblogminisystem.repository.ArticleClassifyRepository;
import com.jackson.myblogminisystem.repository.ArticleRepository;
import com.jackson.myblogminisystem.service.ArticleClassifyService;
import com.jackson.result.Result;
import com.jackson.vo.ArticlePageVO;
import com.jackson.vo.ClassifyArticleVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleClassifyServiceImpl implements ArticleClassifyService {

    @Resource
    private ArticleClassifyRepository articleClassifyRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ArticleRepository articleRepository;

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

    /**
     * 获取分类文章详情接口
     *
     * @param id
     * @return
     */
    @Override
    public Result<ClassifyArticleVO> getClassifyDetail(Long id) {
        Long currentId = BaseContext.getCurrentId();
        ArticleClassify articleClassify = articleClassifyRepository.findById(id).get();
        ClassifyArticleVO classifyArticleVO = BeanUtil.copyProperties(articleClassify, ClassifyArticleVO.class);
        List<Article> articles = articleRepository.findAllByClassifyId(id);
        List<ArticlePageVO> articlePageVOS = articles.stream().map(article -> {
            ArticlePageVO articlePageVO = BeanUtil.copyProperties(article, ArticlePageVO.class);
            Boolean isMember = stringRedisTemplate.opsForSet().isMember(RedisConstant.ARTICLE_LIKE_PREFIX + article.getId(), currentId.toString());
            articlePageVO.setIsLike(isMember);
            return articlePageVO;
        }).toList();
        classifyArticleVO.setArticlePageVOList(articlePageVOS);
        return Result.success(classifyArticleVO);
    }
}
