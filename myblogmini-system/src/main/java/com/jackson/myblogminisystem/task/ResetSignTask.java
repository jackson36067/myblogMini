package com.jackson.myblogminisystem.task;

import com.jackson.myblogminisystem.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResetSignTask {

    @Resource
    private UserRepository userRepository;

    // 每天凌晨重置所有用户签到
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetSignIn() {
        log.info("重置签到活动");
        userRepository.resetAllUsersSignIn();
    }
}
