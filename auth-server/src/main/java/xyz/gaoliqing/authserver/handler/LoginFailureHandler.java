package xyz.gaoliqing.authserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import xyz.gaoliqing.authserver.exception.CustomException;
import xyz.gaoliqing.authserver.exception.CustomExceptionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-18 17:52
 * @description 登录失败时触发此类
 */

@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {

        log.info("登录失败,原因: --->"+ e.getMessage());
        throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, e.getMessage());
    }
}
