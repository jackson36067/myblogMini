package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.Article;
import com.jackson.dao.ArticleClassify;
import com.jackson.dao.User;
import com.jackson.dao.UserLikeArticle;
import com.jackson.dto.AddArticleDTO;
import com.jackson.myblogminisystem.annotation.GetLoginUserId;
import com.jackson.myblogminisystem.repository.ArticleClassifyRepository;
import com.jackson.myblogminisystem.repository.ArticleRepository;
import com.jackson.myblogminisystem.repository.UserLikeArticleRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.ArticleService;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.ArticlePageVO;
import com.jackson.vo.ArticleVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
    @Resource
    private ArticleClassifyRepository articleClassifyRepository;

    /**
     * 分页获取文章
     *
     * @param title
     * @param sign
     * @param page
     * @param pageSize
     * @return
     */
    @GetLoginUserId
    @Override
    public Result<PageResult> getArticleWithPaging(String title, Integer sign, Integer page, Integer pageSize) {
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
            pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "totalVisit"));
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
     * 获取当前用户所有的收藏文章
     *
     * @param title
     * @return
     */
    public Result<List<ArticlePageVO>> getMyCollectArticle(String title) {
        Long currentId = BaseContext.getCurrentId();
        // 获取当前用户所有收藏文章的id
        Set<String> members = stringRedisTemplate.opsForSet().members(RedisConstant.ARTICLE_FAVORITE_KEY_PREFIX + currentId);
        List<Article> articleList = List.of();
        if (members != null && !members.isEmpty()) {
            articleList = members
                    .stream()
                    .map(member -> {
                        return articleRepository.findById(Long.valueOf(member)).get();
                    })
                    .toList();
            if (StringUtils.hasText(title)) {
                articleList = articleList.stream().filter(article -> article.getTitle().contains(title)).toList();
            }
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
     * 获取文章详情接口
     *
     * @param id
     * @return
     */
    @GetLoginUserId
    @Override
    public Result<ArticleVO> getArticleDetail(Long id) {
        Article article = articleRepository.findById(id).get();
        ArticleVO articleVO = BeanUtil.copyProperties(article, ArticleVO.class);
        Long currentId = BaseContext.getCurrentId();
        if (currentId != null) {
            Boolean isMember = isLike(id, currentId);
            articleVO.setIsLike(isMember);
            articleVO.setIsFavorite(isFavorite(currentId, id));
        }
        User user = userRepository.findById(article.getUser().getId()).get();
        articleVO.setAvatar(user.getAvatar());
        articleVO.setNickName(user.getNickName());
        articleVO.setIsFollow(isFollow(currentId, user.getId()));
        articleVO.setArticleId(id);
        String[] tags = article.getTags().split(",");
        articleVO.setTags(Arrays.stream(tags).toList());
        // 文章访问数 + 1
        article.setTotalVisit(article.getTotalVisit() + 1);
        articleRepository.saveAndFlush(article);
        // 将文章阅读记录保存 -> 使用zSet,方便按时间获取
        stringRedisTemplate.opsForZSet().add(RedisConstant.ARTICLE_BROWSE_HISTORY_PREFIX + currentId, id.toString(), System.currentTimeMillis());
        return Result.success(articleVO);
    }

    /**
     * 收藏文章
     *
     * @param id
     */
    @Override
    public void doFavoriteArticle(Long id) {
        Long currentId = BaseContext.getCurrentId();
        Article article = articleRepository.findById(id).get();
        Boolean isFavorite = stringRedisTemplate.opsForSet().isMember(RedisConstant.ARTICLE_FAVORITE_KEY_PREFIX + currentId, id.toString());
        // 判断是否收藏
        if (Boolean.TRUE.equals(isFavorite)) {
            // 取消收藏
            stringRedisTemplate.opsForSet().remove(RedisConstant.ARTICLE_FAVORITE_KEY_PREFIX + currentId, id.toString());
            // 收藏数➖1
            article.setTotalCollect(article.getTotalCollect() - 1);
        } else {
            // 收藏
            stringRedisTemplate.opsForSet().add(RedisConstant.ARTICLE_FAVORITE_KEY_PREFIX + currentId, id.toString());
            // 收藏数➕1
            article.setTotalCollect(article.getTotalCollect() + 1);
        }
        articleRepository.saveAndFlush(article);
    }

    /**
     * 新增文章接口
     *
     * @param addArticleDTO
     */
    @Override
    public void addArticle(AddArticleDTO addArticleDTO) {
        Article article = BeanUtil.copyProperties(addArticleDTO, Article.class);
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        String tags = StringUtil.join(",", addArticleDTO.getTags()).toString();
        ArticleClassify articleClassify = articleClassifyRepository.findByType(addArticleDTO.getType());
        article.setUser(user);
        article.setTags(tags);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setTotalComment(0);
        article.setTotalCollect(0);
        article.setTotalLike(0);
        article.setTotalVisit(0);
        article.setArticleClassify(articleClassify);
        articleRepository.save(article);
    }

    /**
     * 获取用户文章详情
     *
     * @param id      用户id
     * @param current 0 用户自己的文章 1. 用户点赞的文章
     * @return
     */
    @Override
    public Result<List<ArticlePageVO>> getUserDetailArticle(Long id, Integer current) {
        List<Article> articleList = List.of();
        if (current == 0) {
            articleList = articleRepository.findAllByUserId(id);
        } else if (current == 1) {
            articleList = userLikeArticleRepository.findAllByUserId(id)
                    .stream()
                    .map(userLikeArticle ->
                            articleRepository.findById(userLikeArticle.getArticleId()).get()
                    )
                    .toList();
        }
        List<ArticlePageVO> articlePageVOList = getResult(articleList);
        return Result.success(articlePageVOList);
    }

    /**
     * 获取用户访问文章历史记录详情
     *
     * @return
     */
    @Override
    public Result<List<ArticlePageVO>> getArticleBrowseHistory() {
        // 获取用户浏览记录历史
        Long currentId = BaseContext.getCurrentId();
        // 按照时间先后顺序获取浏览记录
        Set<String> historyBrowseArticleIdStrSet = stringRedisTemplate.opsForZSet().reverseRange(RedisConstant.ARTICLE_BROWSE_HISTORY_PREFIX + currentId, 0, -1);
        List<Long> historyBrowseArticleIdList = historyBrowseArticleIdStrSet.stream().map(Long::valueOf).toList();
        List<Article> historyBrowseArticleList = articleRepository.findAllById(historyBrowseArticleIdList);
        List<ArticlePageVO> articlePageVOList = getResult(historyBrowseArticleList);
        return Result.success(articlePageVOList);
    }

    /**
     * 判断用户是否对文章点赞
     *
     * @param articleId
     * @param userId
     * @return
     */
    private Boolean isLike(Long articleId, Long userId) {
        return stringRedisTemplate.opsForSet().isMember(RedisConstant.ARTICLE_LIKE_PREFIX + articleId, userId.toString());
    }

    /**
     * 判断是否关注
     *
     * @param userId
     * @param userFollowId
     * @return
     */
    private Boolean isFollow(Long userId, Long userFollowId) {
        return stringRedisTemplate.opsForSet().isMember(RedisConstant.ARTICLE_LIKE_PREFIX + userId, userFollowId.toString());
    }

    /**
     * 判断是否收藏文章
     *
     * @param userId
     * @param articleId
     * @return
     */
    private Boolean isFavorite(Long userId, Long articleId) {
        return stringRedisTemplate.opsForSet().isMember(RedisConstant.ARTICLE_FAVORITE_KEY_PREFIX + userId, articleId.toString());
    }

    /**
     * 获取封装后的数据
     *
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
                    if (currentId != null) {
                        Boolean isMember = isLike(article.getId(), currentId);
                        articlePageVO.setIsLike(isMember);
                    }
                    return articlePageVO;
                })
                .toList();
    }
}
