package xyz.gaoliqing.authserver.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-18 17:52
 * @description 获取请求的IP地址
 */

public class CatchClientIpAddress {

    public static String getIpAddress(HttpServletRequest request) {

        String clientIp = request.getHeader("x-forwarded-for");

        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
