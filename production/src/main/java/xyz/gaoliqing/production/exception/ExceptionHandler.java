package xyz.gaoliqing.production.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.gaoliqing.production.utils.AjaxResponse;

/**
 * @author Mr.GaoLiqing
 * @create 2020-01-20 16:05
 * @description
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public AjaxResponse customerException(CustomException e) {
        if (500 == e.getCode()) log.error("自定义已知报错信息------>"+e.getMessage());
        return AjaxResponse.error(e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public AjaxResponse exception(Exception e) {
        log.error("系统未知的报错信息======> "+ e.toString());
        return AjaxResponse.error(new CustomException(CustomExceptionType.OTHER_ERROR, "系统出了点问题,请联系管理员,tel:1585455XXXX"));
    }
}
