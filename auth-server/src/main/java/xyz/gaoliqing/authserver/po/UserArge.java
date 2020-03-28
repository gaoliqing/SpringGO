package xyz.gaoliqing.authserver.po;

import lombok.Data;

/**
 * @author Mr.GaoLiqing
 * @create 2020-03-26 14:01
 * @description
 */
@Data
public class UserArge {

    private String username;
    private String password;
    private String scope;
}
