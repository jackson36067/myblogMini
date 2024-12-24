package com.jackson.myblogminisystem.interceptor;

import com.jackson.constant.JwtConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(Thread.currentThread().getId());
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(JwtConstant.TOKEN_HEADER);
        //2.校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtils.parseJWT(token);
            Long userId = Long.valueOf(claims.get(JwtConstant.USER_ID).toString());
            log.info("登录用户的id:{}", userId);
            //使用ThreadLocal对新增员工操作者的id的储存
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception e) {
            log.info("解析令牌失败");
            stringRedisTemplate.opsForValue().getAndDelete(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
            response.setStatus(401);
            return false;
        }
    }
}
