package com.d4c.www.entity.result;

import lombok.Getter;

/**
 * 响应枚举类: 用于和前端约定响应状态（包括响应代码和响应描述）
 *
 * @author JoeZhou
 */
@Getter
public enum ResultCode {

    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    TOKEN_FAILED(1003, "Token过期或失效"),
    TOKEN_EXPIRING_SOON(1004, "Token即将过期");

    private final int CODE;
    private final String MESSAGE;

    ResultCode(int code, String msg) {
        this.CODE = code;
        this.MESSAGE = msg;
    }
}