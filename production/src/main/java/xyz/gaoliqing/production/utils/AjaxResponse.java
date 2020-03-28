package xyz.gaoliqing.production.utils;

import lombok.Data;
import xyz.gaoliqing.production.exception.CustomException;
import xyz.gaoliqing.production.exception.CustomExceptionType;

import java.io.Serializable;

/**
 * @author Mr.GaoLiqing
 * @create 2020-01-19 18:39
 * @description
 */
@Data
public class AjaxResponse<T> implements Cloneable, Serializable {

    private static final long serialVersionUID = -5893516345281301561L;
    private boolean status;
    private int code;
    private String message;
    private T data;

    private static AjaxResponse ajaxResponse = new AjaxResponse();

    public static AjaxResponse error(CustomException e) {

        AjaxResponse clone = ARClone();
        clone.setStatus(false);
        clone.setCode(e.getCode());
        switch (e.getCode()) {
            case 400: clone.setMessage(e.getMessage());
            case 500: clone.setMessage(e.getMessage());
            case 999: clone.setMessage(e.getMessage());
            default: clone.setMessage(e.toString());
        }
        return clone;
    }

    public static AjaxResponse success() {

        AjaxResponse clone = ARClone();
        clone.setStatus(true);
        clone.setCode(200);
        clone.setMessage("success");
        return clone;
    }

    public static <T> AjaxResponse success(T date) {

        AjaxResponse clone = ARClone();
        clone.setStatus(true);
        clone.setCode(200);
        clone.setMessage("success");
        clone.setData(date);
        return clone;
    }

    private static AjaxResponse ARClone() {
        AjaxResponse cloned;
        try {
            cloned = (AjaxResponse) ajaxResponse.clone();
        } catch (CloneNotSupportedException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "克隆失败");
        }
        return cloned;
    }
}
