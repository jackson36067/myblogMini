package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.jackson.constant.JwtConstant;
import com.jackson.context.BaseContext;
import com.jackson.dao.User;
import com.jackson.dto.UserLoginDTO;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.service.UserService;
import com.jackson.properties.WeChatProperties;
import com.jackson.result.Result;
import com.jackson.utils.HttpClientUtils;
import com.jackson.utils.JwtUtils;
import com.jackson.vo.LoginResultVO;
import com.jackson.vo.UserResult;
import jakarta.annotation.Resource;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private UserRepository userRepository;
    @Resource
    private WeChatProperties weChatProperties;

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
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            userRepository.save(user);
        }
        // 用户不为空 -> 旧用户, 生成token返回
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.USER_ID, user.getId());
        String token = JwtUtils.createJWT(claims);
        // 返回这个用户对象
        LoginResultVO loginResultVO = BeanUtil.copyProperties(user, LoginResultVO.class);
        loginResultVO.setToken(token);
        return Result.success(loginResultVO);
    }

    /**
     * 获取用户详情信息
     * @return
     */
    public Result<UserResult> getUserInfo() {
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        UserResult userResult = BeanUtil.copyProperties(user, UserResult.class);
        return Result.success(userResult);
    }

    /**
     * 调用微信接口服务,获取当前用户的openid
     *
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        // 通过HttpClient发送请求给auth.code2Session接口获取openid
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", weChatProperties.getAppid());
        params.put("secret", weChatProperties.getSecret());
        params.put("grant_type", "authorization_code");
        params.put("js_code", code);
        String json = HttpClientUtils.doGet(WX_LOGIN, params);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }
}

