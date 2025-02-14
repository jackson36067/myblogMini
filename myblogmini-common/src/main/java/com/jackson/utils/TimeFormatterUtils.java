package com.jackson.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeFormatterUtils {

    /**
     * 最近上线时间日期转换
     * @param lastOnline
     * @return
     */
    public static String formatLastOnline(LocalDateTime lastOnline) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastOnline, now);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        if (minutes < 1) {
            return "刚刚";
        } else if (minutes < 60) {
            return minutes + " 分钟前";
        } else if (hours < 24 && lastOnline.toLocalDate().isEqual(now.toLocalDate())) {
            return hours + " 小时前";
        } else if (days == 1) {
            return "昨天";
        } else if (days == 2) {
            return "两天前";
        } else {
            return lastOnline.toLocalDate().toString(); // 返回具体日期
        }
    }
}
