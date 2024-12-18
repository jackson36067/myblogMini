package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.Article;
import com.jackson.dao.UserLikeArticle;
import com.jackson.myblogminisystem.repository.ArticleRepository;
import com.jackson.myblogminisystem.repository.UserLikeArticleRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.ArticleService;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.ArticlePageVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserLikeArticleRepository userLikeArticleRepository;

    /**
     * 分页获取文章
     *
     * @param title
     * @param sign
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Result<PageResult> getArticleWithPaging(String title, Integer sign, Integer page, Integer pageSize) {
        // 获取用户id
        Long currentId = BaseContext.getCurrentId();
        Specification<Article> articleSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();
            if (StringUtils.hasText(title)) {
                Predicate predicate = cb.like(root.get("title"), "%" + title + "%");
                predicateArrayList.add(predicate);
            }
            return cb.and(predicateArrayList.toArray(new Predicate[0]));
        };
        // 初始获取最近文章
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        if (sign == 1) {
            pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "totalLike"));
        }
        Page<Article> articleRepositoryAll = articleRepository.findAll(articleSpecification, pageRequest);
        List<ArticlePageVO> articlePageVOS = getResult(articleRepositoryAll.getContent());
        PageResult pageResult = new PageResult(articleRepositoryAll.getTotalElements(), articleRepositoryAll.getTotalPages(), articlePageVOS);
        return Result.success(pageResult);
    }

    /**
     * 点赞功能
     *
     * @param id
     */
    @Override
    public void doArticleLike(Long id) {
        // 获取用户id
        Long currentId = BaseContext.getCurrentId();
        Article article = articleRepository.findById(id).get();
        // 判断是取消点赞还是点赞
        String key = RedisConstant.ARTICLE_LIKE_PREFIX + id;
        Boolean isLike = stringRedisTemplate.opsForSet().isMember(key, currentId.toString());
        if (Boolean.TRUE.equals(isLike)) {
            // 存在 -> 取消点赞,从当前key中删除该数据
            stringRedisTemplate.opsForSet().remove(key, currentId.toString());
            // 减少一个点赞
            article.setTotalLike(article.getTotalLike() - 1);
            // 从用户点赞文章表中获取数据
            UserLikeArticle byUserIdAndArticleId = userLikeArticleRepository.findByUserIdAndArticleId(currentId, id);
            userLikeArticleRepository.delete(byUserIdAndArticleId);
        } else {
            // 不存在 -> 点赞,将当前数据添加到key中
            stringRedisTemplate.opsForSet().add(key, currentId.toString());
            // 添加一个点赞数量
            article.setTotalLike(article.getTotalLike() + 1);
            userLikeArticleRepository.save(new UserLikeArticle(null, currentId, id));
        }
        articleRepository.saveAndFlush(article);
    }

    /**
     * 获取用户点赞文章接口
     *
     * @return
     */
    @Override
    public Result<List<ArticlePageVO>> getLikeArticle(String title) {
        Long currentId = BaseContext.getCurrentId();
        List<UserLikeArticle> byUserId = userLikeArticleRepository.findAllByUserId(currentId);
        List<Article> articleList;
        articleList = byUserId
                .stream()
                .map(userLikeArticle -> {
                    return articleRepository.findById(userLikeArticle.getArticleId()).get();
                })
                .toList();
        if (StringUtils.hasText(title)) {
            articleList = articleList.stream().filter(article -> article.getTitle().contains(title)).toList();
        }
        List<ArticlePageVO> articlePageVOS = getResult(articleList);
        return Result.success(articlePageVOS);
    }

    /**
     * 获取当前用户的文章
     *
     * @return
     */
    @Override
    public Result<List<ArticlePageVO>> getMyArticle(Integer type, String title) {
        Long currentId = BaseContext.getCurrentId();
        List<Article> allByUserId;
        if (StringUtils.hasText(title)) {
            allByUserId = articleRepository.findAllByUserIdAndTitle(currentId, title);
        } else {
            allByUserId = articleRepository.findAllByUserId(currentId);
        }
        allByUserId = allByUserId
                .stream()
                .filter(article -> {
                    if (type == 0) {
                        return article.getIsShow();
                    } else {
                        return !article.getIsShow();
                    }
                }).toList();
        List<ArticlePageVO> articlePageVOS = getResult(allByUserId);
        return Result.success(articlePageVOS);
    }

    /**
     * 判断用户是否对文章点赞,
     *
     * @param articleId
     * @param userId
     * @return
     */
    private Boolean isMember(Long articleId, Long userId) {
        return stringRedisTemplate.opsForSet().isMember(RedisConstant.ARTICLE_LIKE_PREFIX + articleId, userId.toString());
    }

    /**
     * 获取封装后的数据
     * @param articleList
     * @return
     */
    private List<ArticlePageVO> getResult(List<Article> articleList) {
        Long currentId = BaseContext.getCurrentId();
        return articleList.stream()
                .filter(Article::getIsShow) // 过滤出所有展示的文章
                .map(article -> {
                    ArticlePageVO articlePageVO = BeanUtil.copyProperties(article, ArticlePageVO.class);
                    String[] split = article.getTags().split(",");
                    articlePageVO.setTags(Arrays.stream(split).toList());
                    String nickName = userRepository.findNickNameById(article.getUser().getId());
                    articlePageVO.setAuthor(nickName);
                    Boolean isMember = isMember(article.getId(), currentId);
                    articlePageVO.setIsLike(isMember);
                    return articlePageVO;
                })
                .toList();
    }
}
