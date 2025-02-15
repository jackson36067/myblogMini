package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.jackson.constant.JwtConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.Article;
import com.jackson.dao.User;
import com.jackson.dao.UserFollow;
import com.jackson.dao.UserNote;
import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.myblogminisystem.repository.ArticleRepository;
import com.jackson.myblogminisystem.repository.UserFollowRepository;
import com.jackson.myblogminisystem.repository.UserNoteRepository;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.UserService;
import com.jackson.properties.WeChatProperties;
import com.jackson.result.Result;
import com.jackson.utils.AliOssUtils;
import com.jackson.utils.HttpClientUtils;
import com.jackson.utils.JwtUtils;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserDataVO;
import com.jackson.vo.UserDetailVO;
import com.jackson.vo.UserResult;
import jakarta.annotation.Resource;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private UserRepository userRepository;
    @Resource
    private WeChatProperties weChatProperties;
    @Resource
    private AliOssUtils aliOssUtils;
    @Resource
    private UserFollowRepository userFollowRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private UserNoteRepository userNoteRepository;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    public Result<LoginResultVO> login(UserLoginDTO userLoginDTO) {
        // 通过httpclient配合code想微信端发起请求获取openid
        String openid = getOpenid(userLoginDTO.getCode());
        User user = userRepository.findByOpenid(openid);
        if (user == null) {
            user = BeanUtil.copyProperties(userLoginDTO, User.class);
            user.setSex(userLoginDTO.getGender());
            user.setCreateTime(LocalDateTime.now());
            userRepository.save(user);
        }
        // 用户不为空 -> 旧用户, 生成token返回
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.USER_ID, user.getId());
        String token = JwtUtils.createJWT(claims);
        stringRedisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY_PREFIX + token, user.getId().toString());
        // 返回这个用户对象
        LoginResultVO loginResultVO = BeanUtil.copyProperties(user, LoginResultVO.class);
        loginResultVO.setToken(token);
        return Result.success(loginResultVO);
    }

    /**
     * 获取用户详情信息
     *
     * @return
     */
    public Result<UserResult> getUserInfo() {
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        UserResult userResult = BeanUtil.copyProperties(user, UserResult.class);
        List<Article> allByUserId = articleRepository.findAllByUserId(currentId);
        // 获取用户文章总获赞数
        int totalLike = 0;
        if (allByUserId != null && !allByUserId.isEmpty()) {
            totalLike = allByUserId.stream().mapToInt(Article::getTotalLike).sum();
        }
        // 获取该用户总关注数量
        List<UserFollow> byUserId = userFollowRepository.findAllByUserId(currentId);
        int totalFollow = 0;
        int totalFriend = 0;
        if (byUserId != null && !byUserId.isEmpty()) {
            totalFollow = byUserId.size();
            // 获取用户所有朋友数量
            totalFriend = byUserId.stream().mapToInt(userfollow -> {
                int initFriendNum = 0;
                // 判断该用户关注的用户是否关注关注该用户
                Boolean isFollow = stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + userfollow.getUserFollowId(), currentId.toString());
                if (Boolean.TRUE.equals(isFollow)) {
                    initFriendNum++;
                }
                return initFriendNum;
            }).sum();
        }
        // 获取该用户的总粉丝数
        List<UserFollow> allByUserFollowId = userFollowRepository.findAllByUserFollowId(currentId);
        int totalFans = 0;
        if (allByUserFollowId != null && !allByUserFollowId.isEmpty()) {
            totalFans = allByUserFollowId.size();
        }
        userResult.setTotalLike(totalLike);
        userResult.setTotalFriend(totalFriend);
        userResult.setTotalFollow(totalFollow);
        userResult.setTotalFans(totalFans);
        return Result.success(userResult);
    }

    /**
     * 上传头像至alioss
     *
     * @param image
     * @return
     */
    @Override
    public Result<String> uploadImage(MultipartFile image) {
        String upload = aliOssUtils.upload(image);
        return Result.success(upload);
    }

    /**
     * 修改用户信息接口
     *
     * @param updateUserDTO
     */
    @Override
    public void updateUserInfo(UpdateUserDTO updateUserDTO) {
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        String avatar = updateUserDTO.getAvatar();
        if (StringUtils.hasText(avatar)) {
            user.setAvatar(avatar);
        }
        String nickName = updateUserDTO.getNickName();
        if (StringUtils.hasText(nickName)) {
            user.setNickName(nickName);
        }
        userRepository.saveAndFlush(user);
    }

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    public Result<UserDetailVO> getUserDetailData(Long id) {
        // 获取当前用户id
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(id).get();
        UserDetailVO userDetailVO = BeanUtil.copyProperties(user, UserDetailVO.class);
        // 获取当前用户是否关注该用户
        Boolean isFollow = stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + currentId, id.toString());
        userDetailVO.setIsFollow(isFollow);
        // 判断是否互相关注
        if (Boolean.FALSE.equals(isFollow)) {
            userDetailVO.setIsMutualAttention(false);
        } else {
            // 判断该获取是否关注当前用户
            Boolean backFollow = stringRedisTemplate.opsForSet().isMember(RedisConstant.USER_FOLLOW_KEY_PREFIX + id, currentId.toString());
            userDetailVO.setIsMutualAttention(backFollow);
        }
        // 获取该用户总共的点赞数以及粉丝以及关注

        // 总关注数
        Set<String> members = stringRedisTemplate.opsForSet().members(RedisConstant.USER_FOLLOW_KEY_PREFIX + id);
        userDetailVO.setTotalFollow(members != null ? members.size() : 0);

        // 总点赞数
        int totalLike = 0;
        List<Article> articleList = articleRepository.findAllByUserId(id);
        totalLike = articleList.stream().mapToInt(Article::getTotalLike).sum();
        userDetailVO.setTotalLike(totalLike);

        // 总粉丝数
        List<UserFollow> userFollowList = userFollowRepository.findAllByUserFollowId(id);
        userDetailVO.setTotalFans(userFollowList != null ? userFollowList.size() : 0);

        // 获取当前用户对该用户的备注,可以没有
        UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, id);
        if (userNote != null) {
            userDetailVO.setComment(userNote.getNote());
        }
        // 缓存用户访问历史记录
        // TODO 异步实现保存效果
        stringRedisTemplate.opsForZSet().add(RedisConstant.USER_BROWSE_HISTORY_PREFIX + currentId, id.toString(), System.currentTimeMillis());
        return Result.success(userDetailVO);
    }

    /**
     * 获取用户访问他人主页历史记录详情
     *
     * @return
     */
    @Override
    public Result<List<UserDataVO>> getUserHistoryBrowse() {
        Long currentId = BaseContext.getCurrentId();
        Set<String> userBrowseHistoryIdStrSet = stringRedisTemplate.opsForZSet().reverseRange(RedisConstant.USER_BROWSE_HISTORY_PREFIX + currentId, 0, -1);
        List<UserDataVO> userDataVOList = userBrowseHistoryIdStrSet.stream().map((userBrowseHistoryIdStr) -> {
            Long id = Long.valueOf(userBrowseHistoryIdStr);
            User user = userRepository.findById(id).get();
            UserDataVO userDataVO = BeanUtil.copyProperties(user, UserDataVO.class);
            // 获取当前用户对该用户的备注,可以没有
            UserNote userNote = userNoteRepository.findByUserIdAndUserNoteId(currentId, id);
            if(userNote != null){
                userDataVO.setComment(userNote.getNote());
            }
            return userDataVO;
        }).toList();
        return Result.success(userDataVOList);
    }

    /**
     * 调用微信接口服务,获取当前用户的openid
     *
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        // 通过HttpClient发送请求给auth.code2Session接口获取openid
        Map<String, String> params = new HashMap<>();
        params.put("appid", weChatProperties.getAppid());
        params.put("secret", weChatProperties.getSecret());
        params.put("grant_type", "authorization_code");
        params.put("js_code", code);
        String json = HttpClientUtils.doGet(WX_LOGIN, params);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }
}

