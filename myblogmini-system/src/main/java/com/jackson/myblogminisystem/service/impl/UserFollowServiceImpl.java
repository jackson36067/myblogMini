package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.User;
import com.jackson.dao.UserFollow;
import com.jackson.dao.UserNote;
import com.jackson.exception.OnFollowException;
import com.jackson.myblogminisystem.handler.WebSocketHandler;
import com.jackson.myblogminisystem.repository.UserFollowRepository;
import com.jackson.myblogminisystem.repository.UserNoteRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.UserFollowService;
import com.jackson.result.Result;
import com.jackson.vo.UserFollowListVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Resource
    private UserFollowRepository userFollowRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private WebSocketHandler webSocketHandler;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserNoteRepository userNoteRepository;

    /**
     * 关注用户
     *
     * @param id
     */
    @Override
    public void followUser(Long id) {
        Long currentId = BaseContext.getCurrentId();
        // 判断是否已经关注
        if (Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, id.toString()))) {
            stringRedisTemplate.opsForSet().remove(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, id.toString());
            UserFollow userFollow = userFollowRepository.findByUserIdAndUserFollowId(currentId, id);
            userFollowRepository.delete(userFollow);
        } else {
            UserFollow userFollow = new UserFollow();
            userFollow.setUserId(currentId);
            userFollow.setUserFollowId(id);
            userFollow.setCreateTime(LocalDateTime.now());
            userFollowRepository.save(userFollow);
            // 将关注记录保存到Redis中 (使用hash进行存储,key为用户id,value为被关注用户id)
            stringRedisTemplate.opsForSet().add(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, id.toString());
            // 发送关注信息
            try {
                webSocketHandler.sendNotification(id, currentId.toString(), 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Result<List<UserFollowListVO>> getUserFollowList() {
        Long currentId = BaseContext.getCurrentId();
        List<UserFollow> userFollowList = userFollowRepository.findAllByUserFollowIdAndOrderByCreateTimeAsc(currentId);
        List<UserFollowListVO> userFollowListVOList = userFollowList.stream()
                .map(userFollow -> {
                    UserFollowListVO userFollowListVO = new UserFollowListVO();
                    Long userId = userFollow.getUserId();
                    userFollowListVO.setId(userId);
                    userFollowListVO.setCreateTime(userFollow.getCreateTime().toLocalDate());
                    User user = userRepository.findById(userId).get();
                    userFollowListVO.setNickName(user.getNickName());
                    userFollowListVO.setAvatar(user.getAvatar());
                    UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, userId);
                    userFollowListVO.setComment(userNote != null ? userNote.getNote() : null);
                    UserFollow mutualAttention = userFollowRepository.findByUserIdAndUserFollowId(currentId, userId);
                    userFollowListVO.setMutualAttention(mutualAttention != null);
                    return userFollowListVO;
                })
                .toList();
        // 将Redis中的信息删除
        Set<String> range = stringRedisTemplate.opsForZSet().range(RedisConstant.USER_FOLLOW_MESSAGE_PREFIX + currentId, 0, -1);
        if (range != null && !range.isEmpty()) {
            stringRedisTemplate.opsForZSet().removeRange(RedisConstant.USER_FOLLOW_MESSAGE_PREFIX + currentId, 0, -1);
        }
        return Result.success(userFollowListVOList);
    }
}
