package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
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

    /**
     * 根据文章id获取评论
     *
     * @param id
     * @return
     */
    @Override
    public Result<PageResult> getUserComment(Long id) {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "totalLike"));
        Page<UserComment> allByArticleId = userCommentRepository.findAllByArticleId(id, pageRequest);
        List<UserCommentVO> userCommentVOList = allByArticleId.getContent()
                .stream()
                .map(userComment -> {
                    UserCommentVO userCommentVO = BeanUtil.copyProperties(userComment, UserCommentVO.class);
                    User user = userRepository.findById(userComment.getUser().getId()).get();
                    userCommentVO.setAvatar(user.getAvatar());
                    userCommentVO.setNickName(user.getNickName());
                    return userCommentVO;
                })
                .toList();
        // TODO:判断该用户是否点赞该评论,是否关注发布评论的作者
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
        // TODO: 将信息添加到消息队列,然后通知文章作者
    }
}
