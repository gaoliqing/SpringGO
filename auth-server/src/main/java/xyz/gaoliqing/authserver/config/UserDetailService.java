package xyz.gaoliqing.authserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.gaoliqing.authserver.exception.CustomException;
import xyz.gaoliqing.authserver.exception.CustomExceptionType;
import xyz.gaoliqing.authserver.mapper.AccountMapper;
import xyz.gaoliqing.authserver.po.UserExist;

import javax.annotation.Resource;

/**
 * @author Mr.GaoLiqing
 * @create 2019-03-04 10:40
 * @description
 */
@Component
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        log.info("谁在登录：---> " + s);

        String userExist = accountMapper.checkUser(s);

        if (null == userExist) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或密码错误 !");
        } else {
            //从用户库中提取账户对象
            xyz.gaoliqing.authserver.po.User users = accountMapper.getUser(s);

            String password = users.getPassword();
            //权限
            String scope = users.getScope();
            //账户是否可用
            boolean enabled = users.getEnabled() == 1;
            //账户是否过期
            boolean account_non_expired = users.getAccount_non_expired() == 1;
            //密码是否过期
            boolean credentials_non_expired = users.getCredentials_non_expired() == 1;
            //账户是否锁定
            boolean account_non_locked = users.getAccount_non_locked() == 1;

            return new User(s, password,
                    enabled, account_non_expired, credentials_non_expired, account_non_locked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(scope));
        }
    }

}
