package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.Register;

import java.time.LocalDateTime;

public class RegisterResponse {
    private String sessionId;
    private  int verifyCode;
    private String message;
    private Register register;
    private LocalDateTime regTime;
//==========================================================================

    public RegisterResponse(String sessionId, int verifyCode, String message) {
        this.sessionId = sessionId;
        this.verifyCode = verifyCode;
        this.message = message;
    }

    public RegisterResponse() {
    }

    public RegisterResponse(String message, Register register) {
        this.message = message;
        this.register = register;
    }

    public RegisterResponse(String message, LocalDateTime regTime) {
        this.message = message;
        this.regTime = regTime;
    }

    public RegisterResponse(String message) {
        this.message = message;
    }
    //==========================================================================

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(int verifyCode) {
        this.verifyCode = verifyCode;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
