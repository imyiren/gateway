package com.example.gateway.common.model;

import com.example.gateway.common.enums.ResponseResultCodeEnum;

/**
 * 返回结果包装类
 *
 * @author Yiren (<a href="mailto:yiren.dev@gmail.com">Send Email.<a/>)
 * @date 2021/3/21
 */
public class ResponseResult<T> {
    private String code;
    private String message;
    private T data;
    private String requestId;

    public ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult<T> success(T data) {
        return success(ResponseResultCodeEnum.SUCCESS, data);
    }

    public ResponseResult<T> success(ResponseResultCodeEnum codeEnum, T data) {
        return new ResponseResult<>(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    public ResponseResult<T> success(T data, String msg) {
        return new ResponseResult<>(ResponseResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public ResponseResult<T> error(String code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    public ResponseResult<T> error(ResponseResultCodeEnum codeEnum, T data) {
        return new ResponseResult<>(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    public ResponseResult<Void> error(ResponseResultCodeEnum codeEnum) {
        return new ResponseResult<Void>(codeEnum.getCode(), codeEnum.getMsg(), null);
    }

    public ResponseResult<Void> error(String code, String msg) {
        return new ResponseResult<Void>(code, msg, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
