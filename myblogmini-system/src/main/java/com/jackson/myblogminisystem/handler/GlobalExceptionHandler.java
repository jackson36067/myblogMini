package com.jackson.myblogminisystem.handler;

import com.jackson.exception.AuthorizedException;
import com.jackson.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     *
     * @param be
     * @return
     */
    @ExceptionHandler
    public Result<String> ExceptionHandler(Exception be, HttpServletResponse response) {
        log.info("异常信息:{}", be.getMessage());
        if (be instanceof AuthorizedException){
            response.setStatus(401);
        }else{
            response.setStatus(500);
        }
        return Result.error(be.getMessage());
    }
}
