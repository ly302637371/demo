package com.liyuan.entity;

import com.liyuan.base.BeanBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liy on 2017/8/15.
 */
@Entity
@Table(name = "sys_user")
public class User extends BeanBase implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    private String id;
    /*账户名*/
    @Column(name = "user_accent", length = 30)
    private String userAccent;
    /*账号密码*/
    @Column(name = "user_pw",length = 50)
    private String userPw;//密码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserAccent() {
        return userAccent;
    }

    public void setUserAccent(String userAccent) {
        this.userAccent = userAccent;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
}
