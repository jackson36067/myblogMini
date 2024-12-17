package com.jackson.myblogminisystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.context.BaseContext;
import com.jackson.dao.User;
import com.jackson.dao.UserSign;
import com.jackson.myblogminisystem.repository.UserRepository;
import com.jackson.myblogminisystem.repository.UserSignRepository;
import com.jackson.myblogminisystem.service.UserSignService;
import com.jackson.result.Result;
import com.jackson.vo.UserSignInfoVO;
import com.jackson.vo.UserSignResult;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserSignServiceImpl implements UserSignService {

    private static final Logger log = LoggerFactory.getLogger(UserSignServiceImpl.class);
    @Resource
    private UserSignRepository userSignRepository;
    @Resource
    private UserRepository userRepository;

    /**
     * 用户签到
     * @return
     */
    @Transactional
    @Override
    public Result<UserSignResult> doSign() {
        // 获取用户id
        Long currentId = BaseContext.getCurrentId();
        User user = userRepository.findById(currentId).get();
        // 1.新增签到信息到数据库中
        userSignRepository.save(new UserSign(null, user, LocalDate.now()));
        // 获取今日日期
        LocalDate currentDate = LocalDate.now();
        // 获取用户签到所有日期
        List<LocalDate> recentSignDates = userSignRepository.findRecentSignDates(currentId, currentDate);
        int continuousDays = 0; // 连续签到天数
        // 获取已经连续签到天数
        for (LocalDate date : recentSignDates) {
            if (date.equals(currentDate)) {
                continuousDays++; // 今天签到，计入连续天数
            } else if (date.equals(currentDate.minusDays(continuousDays))) {
                log.info("+1");
                continuousDays++; // 按顺序连续天数
            } else {
                break; // 不连续，结束检查
            }
        }
        user.setisSignIn(true);
        user.setPoints(user.getPoints() + 2);
        userRepository.saveAndFlush(user);
        return Result.success(new UserSignResult(continuousDays));
    }

    /**
     * 获取当前用户签到详情
     * @return
     */
    @Override
    public Result<List<UserSignInfoVO>> getSignInfo() {
        Long currentId = BaseContext.getCurrentId();
        List<UserSign> allByUserId = userSignRepository.findAllByUser_Id(currentId);
        List<UserSignInfoVO> list = allByUserId
                .stream()
                .map(userSign -> BeanUtil.copyProperties(userSign, UserSignInfoVO.class))
                .toList();
        return Result.success(list);
    }
}
