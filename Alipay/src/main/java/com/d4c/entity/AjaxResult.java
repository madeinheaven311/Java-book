package com.d4c.entity;

public class AjaxResult {
    private int code; // 状态码
    private String msg; // 消息
    private Object data; // 数据

    // 成功时调用的方法
    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    // 失败时调用的方法
    public static AjaxResult error(String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    // Getter 和 Setter 方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
