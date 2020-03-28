package xyz.gaoliqing.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.gaoliqing.authserver.handler.LoginFailureHandler;
import xyz.gaoliqing.authserver.handler.LoginSuccessHandler;

import javax.annotation.Resource;

/**
 * @author Mr.GaoLiqing
 * @create 2019-03-04 10:32
 * @description web安全配置类
 */

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 定制请求的授权规则
     *
     * @param http .
     * @throws Exception .
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
//                .loginPage("/login.html")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**","/insert").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}



