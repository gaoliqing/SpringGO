package xyz.gaoliqing.authserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.gaoliqing.authserver.po.User;
import xyz.gaoliqing.authserver.po.UserArge;


/**
 * @author Mr.GaoLiqing
 * @create 2019-03-08 16:05
 * @description
 */

@Mapper
public interface AccountMapper {

    /**
     *
     * @param username 用户名
     * @return 检查输入的用户名是否存在
     */
    String checkUser(@Param(value = "username") String username);


    /**
     *
     * @param username 用户名
     * @return 获取用户账号对象
     */
    User getUser(@Param(value = "username") String username);


    /**
     *
     * @param userArge 用户对象
     * @return 用户注册后插入账号对象
     *
     */
    int insertAccount(UserArge userArge);

    /**
     *
     * @param username 用户名
     * @param count 次数
     * @return 更新登录失败次数
     */
    int updateAccontLimit(@Param(value = "username") String username, @Param(value = "count") int count);

    /**
     *
     * @param username 用户名
     * @return 锁定账户
     */
    int updateAccountLocked(@Param(value = "username") String username);
}
