package com.example.gateway.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用响应结果状态码
 *
 * @author Yiren (<a href="mailto:yiren.dev@gmail.com">Send Email.<a/>)
 * @date 2021/3/21
 */
@Getter
@AllArgsConstructor
public enum ResponseResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS("200", "Success"),
    /**
     * 未登录
     */
    UNAUTHORIZED("401", "Unauthorized"),
    /**
     * 没有权限
     */
    FORBIDDEN("403", "Forbidden"),
    /**
     * 请求类型不被支持
     */
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    /**
     * 服务器无法处理请求附带的媒体格式
     */
    UNSUPPORTED_MEDIA_TYPE("415", "Unsupported Media Type"),
    /**
     * 服务错误
     */
    SERVER_ERROR("500", "Internal server error"),
    /**
     * 服务器不支持请求的功能，无法完成请求
     */
    NOT_IMPLEMENTED	("501", "Not Implemented"),
    /**
     *
     */
    BAD_GATEWAY("502", "Bad Gateway"),
    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE("503", "Service Unavailable"),
    /**
     * 网关超时
     */
    GATEWAY_TIMEOUT("504", "Gateway Timeout"),


    ;

    private final String code;
    private final String msg;

}
