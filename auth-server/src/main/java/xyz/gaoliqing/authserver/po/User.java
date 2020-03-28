package xyz.gaoliqing.authserver.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.GaoLiqing
 * @create 2019-03-08 16:08
 * @description
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -7495991670462550346L;

//    private String id;
    private String user_name;
    private String password;
    private String scope;
    private int enabled;
    private int account_non_expired;
    private int credentials_non_expired;
    private int account_non_locked;
    private int failure_limit;

    @Override
    public String toString() {
        return this.user_name;
    }

    @Override
    public int hashCode() {
        return user_name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());

    }
}
