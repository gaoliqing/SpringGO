package xyz.gaoliqing.authserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.gaoliqing.authserver.exception.CustomException;
import xyz.gaoliqing.authserver.exception.CustomExceptionType;
import xyz.gaoliqing.authserver.utils.CatchClientIpAddress;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-18 17:52
 * @description 登录成功时触发此类
 */

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();
        try {

            if (redisTemplate.hasKey(username)) {

                throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户已经登录");

            } else {
                log.info("登录成功了,将账户名称和登录时间存入redis并设置过期时间");
//                redisTemplate.boundValueOps(username).set(username);
//                redisTemplate.expire(username, 300, TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(username, System.currentTimeMillis(), Duration.ofMinutes(1));

                // 获取请求的IP地址
//                String ipAddress = CatchClientIpAddress.getIpAddress(request);
//                request.setAttribute("ipAddress", ipAddress);

                request.getRequestDispatcher("/token").forward(request, response);
            }
        } catch (AuthenticationException e) {

            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }
    }
}
