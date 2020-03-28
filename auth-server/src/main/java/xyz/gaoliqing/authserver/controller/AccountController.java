package xyz.gaoliqing.authserver.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.gaoliqing.authserver.mapper.AccountMapper;
import xyz.gaoliqing.authserver.po.User;
import xyz.gaoliqing.authserver.po.UserArge;
import xyz.gaoliqing.authserver.utils.AjaxResponse;
import xyz.gaoliqing.authserver.utils.JwtHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-19 16:22
 * @description
 */
@RestController
public class AccountController {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private JwtHandler jwtHandler;

    @PostMapping("/insert")
    public String insert(@RequestBody UserArge userArge) {

        String password = userArge.getPassword();
        // 对密码进行加密
        String encode = passwordEncoder.encode(password);
        userArge.setPassword(encode);

        int insertAccount = accountMapper.insertAccount(userArge);
        return insertAccount >= 1 ? "insert 成功" : "insert 失败";

    }

    @PostMapping("/token")
    public AjaxResponse loginSuccess(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {

        Map<Object, Object> map = new HashMap<>();
        String username = userDetails.getUsername();
        String scope = null;

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (Object authority : authorities) {
            scope = authority.toString();
        }

        //将JWTtoken和ip地址放入Map中
//        String ipAddress = (String) request.getAttribute("ipAddress");
        String jwtToken = jwtHandler.createToken(username,scope);
//        map.put("ipAddress", ipAddress);
        map.put("jwtToken", jwtToken);

        //将token和Map放入redis并设置过期时间
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.boundHashOps(uuid).putAll(map);
        redisTemplate.expire(uuid, 300, TimeUnit.SECONDS);

        return AjaxResponse.success(map);
    }
}
