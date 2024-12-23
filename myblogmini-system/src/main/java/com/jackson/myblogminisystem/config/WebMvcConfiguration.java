package com.jackson.myblogminisystem.config;

import com.jackson.json.JacksonObjectMapper;
import com.jackson.myblogminisystem.interceptor.JwtTokenUserInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Resource
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/api/**","/api/article/doLike") // /api/article/**只拦截 /api/article/doLike
                .excludePathPatterns("/api/user/login",
                        "/api/article/**"
                );
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("拓展消息转换器");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置一个对象转换器给消息转换器,用于将java对象序列化为json格式的数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己定义的消息转换器添加到容器中
        //参数0是为了设置优先情况,把我们自定义的消息转换器设置为最优先的
        converters.add(0, converter);
    }
}
