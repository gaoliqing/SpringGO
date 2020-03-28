package xyz.gaoliqing.authserver.exception;


/**
 * @author Mr.GaoLiqing
 * @create 2020-01-19 18:57
 * @description
 */
public enum CustomExceptionType {

    USER_INPUT_ERROR(400,"客户输入错误"),
    SYSTEM_ERROR(500,"系统服务错误"),
    OTHER_ERROR(999,"其他未知错误");

    CustomExceptionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;    //异常的中文描述

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
