package com.miaxis.distinguished.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by 一非 on 2018/6/27.
 */

@Entity
public class Config implements Serializable {
    private static final long serialVersionUID = 7352283040685174414L;
    @Id
    private Long id;
    private String username;
    private String password;
    private boolean remember;
    private boolean logingSelf;
    private boolean lastLogin;
    @Generated(hash = 477651016)
    public Config(Long id, String username, String password, boolean remember,
            boolean logingSelf, boolean lastLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.remember = remember;
        this.logingSelf = logingSelf;
        this.lastLogin = lastLogin;
    }
    @Generated(hash = 589037648)
    public Config() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getRemember() {
        return this.remember;
    }
    public void setRemember(boolean remember) {
        this.remember = remember;
    }
    public boolean getLogingSelf() {
        return this.logingSelf;
    }
    public void setLogingSelf(boolean logingSelf) {
        this.logingSelf = logingSelf;
    }
    public boolean getLastLogin() {
        return this.lastLogin;
    }
    public void setLastLogin(boolean lastLogin) {
        this.lastLogin = lastLogin;
    }
    
}
