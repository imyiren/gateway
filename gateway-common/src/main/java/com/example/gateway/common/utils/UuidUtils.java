package com.example.gateway.common.utils;

import java.util.UUID;

/**
 * UUID工具
 *
 * @author Yiren (<a href="mailto:lf253130@alibaba-inc.com">Send Email.<a/>)
 * @date 2021/3/27
 */
public class UuidUtils {

    public static final String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
