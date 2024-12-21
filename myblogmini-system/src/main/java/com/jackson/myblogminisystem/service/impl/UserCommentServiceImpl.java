package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.Article;
import com.jackson.dao.User;
import com.jackson.dao.UserComment;
import com.jackson.dto.SendCommentDTO;
import com.jackson.myblogminisystem.repository.ArticleRepository;
import com.jackson.myblogminisystem.repository.UserCommentRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.UserCommentService;
import com.jackson.result.PageResult;
import com.jackson.result.Result;
import com.jackson.vo.UserCommentVO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Resource
    private UserCommentRepository userCommentRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据文章id获取评论
     *
     * @param id 文章id
     * @return
     */
    @Override
    public Result<PageResult> getUserComment(Long id, Boolean isAll) {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "totalLike"));
        Page<UserComment> allByArticleId = userCommentRepository.findAllByArticleId(id, pageRequest);
        List<UserComment> content = allByArticleId.getContent();
        // 如果是查看全部评论,就获取所有评论
        if (isAll != null && isAll) {
            content = userCommentRepository.findAllByArticleId(id);
        }
        List<UserCommentVO> userCommentVOList = content
                .stream()
                .map(userComment -> {
                    UserCommentVO userCommentVO = BeanUtil.copyProperties(userComment, UserCommentVO.class);
                    Long userId = userComment.getUser().getId();
                    User user = userRepository.findById(userId).get();
                    userCommentVO.setAvatar(user.getAvatar());
                    userCommentVO.setNickName(user.getNickName());
                    userCommentVO.setUserId(userId);
                    // 判断该用户是否点赞该评论,是否关注发布评论的作者
                    // 获取用户关注信息
                    Long currentId = BaseContext.getCurrentId();
                    Boolean isFollow = stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, userId.toString());
                    userCommentVO.setIsFollow(isFollow);
                    Boolean isLike = stringRedisTemplate.opsForSet().isMember(RedisConstant.COMMENT_LIKE_KEY_PREFIX + userComment.getId(), currentId.toString());
                    userCommentVO.setIsLike(isLike);
                    return userCommentVO;
                })
                .toList();

        PageResult pageResult = new PageResult(allByArticleId.getTotalElements(), allByArticleId.getTotalPages(), userCommentVOList);
        return Result.success(pageResult);
    }

    /**
     * 用户在文章中发布评论接口
     *
     * @param sendCommentDTO
     */
    @Override
    public void sendComment(SendCommentDTO sendCommentDTO) {
        // 新增评论
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        Article article = articleRepository.findById(sendCommentDTO.getArticleId()).get();
        UserComment userComment = BeanUtil.copyProperties(sendCommentDTO, UserComment.class);
        userComment.setUser(user);
        userComment.setArticle(article);
        userComment.setCreateTime(LocalDateTime.now());
        userComment.setTotalLike(0);
        userCommentRepository.save(userComment);
        // 将文章评论 + 1
        article.setTotalComment(article.getTotalComment() + 1);
        articleRepository.saveAndFlush(article);
        // TODO: 将信息添加到消息队列,然后通知文章作者
    }

    /**
     * 点赞评论
     *
     * @param id 评论id
     */
    @Override
    public void doLikeComment(Long id) {
        Long currentId = BaseContext.getCurrentId();
        // 判断是否点赞过
        String key = RedisConstant.COMMENT_LIKE_KEY_PREFIX + id;
        UserComment userComment = userCommentRepository.findById(id).get();
        if (Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(key, currentId.toString()))) {
            // 点赞过 -> 取消点赞
            stringRedisTemplate.opsForSet().remove(key, currentId.toString());
            userComment.setTotalLike(userComment.getTotalLike() - 1);
        } else {
            stringRedisTemplate.opsForSet().add(key, currentId.toString());
            userComment.setTotalLike(userComment.getTotalLike() + 1);
        }
        // 修改评论总点赞数
        userCommentRepository.saveAndFlush(userComment);
    }
}
