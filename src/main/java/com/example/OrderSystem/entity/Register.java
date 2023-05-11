package com.example.OrderSystem.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Table(name = "register")
public class Register {
    @Id

    @Column(name = "account")
    private String account ;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "reg_time")
    private LocalDateTime regTime = LocalDateTime.now();
    @Column(name = "active")
    private boolean active ;
//===================================================================================================

    public Register() {
    }

    public Register(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }
//===================================================================================================

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
