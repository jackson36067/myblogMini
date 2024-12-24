package com.jackson.myblogminisystem.aop;

import com.jackson.constant.JwtConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.context.BaseContext;
import com.jackson.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class TokenAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(com.jackson.myblogminisystem.annotation.GetLoginUserId)")
    public void allControllerMethods() {
    }

    /**
     * 为没有被拦截器拦截的函数添加赋值BaseContext中的ThreadLocal
     *
     * @param joinPoint
     */
    @Before("allControllerMethods()")
    public void setUserContext(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(JwtConstant.TOKEN_HEADER);
        if (StringUtils.hasText(token)) {
            String id = stringRedisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
            if (StringUtils.hasText(id)) {
                BaseContext.setCurrentId(Long.parseLong(id));
            }
        }
    }
}
