package com.d4c.entity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类: 用于统一封装响应结果（包括响应代码，响应描述和响应数据）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /** 响应代码 */
    private Integer code;
    /** 响应描述 */
    private String message;
    /** 响应数据 */
    private Object data;

    /**
     * 创建Result类，手动传递响应代码，响应描述和响应数据
     *
     * @param code    响应代码
     * @param message 响应描述
     * @param data    响应数据
     */
    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 创建Result类，默认返回成功状态码和响应描述，手动传递响应数据
     *
     * @param data 响应数据
     */
    public Result(Object data) {
        this.code = ResultCode.SUCCESS.getCODE();
        this.message = ResultCode.SUCCESS.getMESSAGE();
        this.data = data;
    }

    /**
     * 创建Result类，手动传递响应状态枚举实例，默认响应数据为null
     *
     * @param resultCode 响应状态枚举实例
     */
    public Result(ResultCode resultCode) {
        this.code = resultCode.getCODE();
        this.message = resultCode.getMESSAGE();
        this.data = null;
    }

    /**
     * 创建Result类，手动传递响应状态枚举实例和响应数据
     *
     * @param resultCode 响应状态枚举实例
     * @param data       响应数据
     */
    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCODE();
        this.message = resultCode.getMESSAGE();
        this.data = data;
    }
}