package com.kvn.poi.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 日志相关的工具类
 */
public class LogTools {

    /**
     * 生成唯一的id
     * 
     * @return
     */
    public static String generateUID() {
        String date = format(new Date(), "yyyyMMddHHmmss");
        String uniqueId = date + "_" + StringUtils.remove(UUID.randomUUID().toString(), "-");
        return uniqueId;
    }

    /**
     * 根据指定时间和样式格式化时间
     * <p>
     * 如：2014-04-21 21:20:10, yyyy-MM-dd -> 2014-04-21
     * 
     * @param date
     *            指定时间
     * @param pattern
     *            指定样式
     * @return 格式化时间
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
