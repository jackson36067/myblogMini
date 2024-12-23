package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.User;
import com.jackson.dao.UserFollow;
import com.jackson.dao.UserNote;
import com.jackson.myblogminisystem.repository.UserFollowRepository;
import com.jackson.myblogminisystem.repository.UserNoteRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.UserDataService;
import com.jackson.result.Result;
import com.jackson.vo.UserDataVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserFollowRepository userFollowRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserNoteRepository userNoteRepository;

    /**
     * @param current 0.朋友 1.关注 2.粉丝
     * @param sort    0.获取最近 1.获取最早
     * @return
     */
    @Override
    public Result<List<UserDataVO>> getUserDataList(Integer current, Integer sort, String nickNameOrComment) {
        List<UserFollow> userFollowList = List.of();
        Long currentId = BaseContext.getCurrentId();
        if (current == 0) {
            List<UserFollow> allByUserId = userFollowRepository.findAllByUserId(currentId);
            userFollowList = allByUserId.stream()
                    .filter(
                            userFollow -> stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + userFollow.getUserFollowId(), currentId.toString())
                    )
                    .toList();
        }
        if (current == 1) {
            if (sort == null || sort == 0) {
                userFollowList = userFollowRepository.findAllByUserIdOOrderByCreateTimeDesc(currentId);
            } else {
                userFollowList = userFollowRepository.findAllByUserIdOOrderByCreateTimeAsc(currentId);
            }

        }
        if (current == 2) {
            userFollowList = userFollowRepository.findAllByUserFollowId(currentId);
        }
        List<UserDataVO> userDataVOList = userFollowList.stream()
                .map(userFollow -> {
                    User user = userRepository.findById(userFollow.getUserFollowId()).get();
                    // 获取粉丝特殊情况
                    if (current == 2) {
                        user = userRepository.findById(userFollow.getUserId()).get();
                    }
                    UserDataVO userDataVO = BeanUtil.copyProperties(user, UserDataVO.class);
                    userDataVO.setIsFollow(true);
                    UserNote note = userNoteRepository.findByUserIdAndUserNoteId(currentId, userFollow.getUserFollowId());
                    if (current == 2) {
                        // 如果是判断粉丝是否关注,就判断是否互相关注,用户后续展示,如果不是互相关注那么就是该用户没有关注该粉丝
                        userDataVO.setIsFollow(
                                        Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, user.getId().toString()))
                                        &&
                                        Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + user.getId(), currentId.toString()))
                        );
                        note = userNoteRepository.findByUserIdAndUserNoteId(currentId, userFollow.getUserId());
                    }
                    if (note != null) {
                        userDataVO.setComment(note.getNote());
                    }
                    return userDataVO;
                })
                .toList();
        if (StringUtils.hasText(nickNameOrComment)) {
            userDataVOList = userDataVOList.stream()
                    .filter(
                            userDataVO -> {
                                if (userDataVO.getComment() == null) {
                                    return userDataVO.getNickName().contains(nickNameOrComment);
                                } else {
                                    return userDataVO.getComment().contains(nickNameOrComment) || userDataVO.getNickName().contains(nickNameOrComment);
                                }
                            }
                    ).toList();
        }
        return Result.success(userDataVOList);
    }
}
