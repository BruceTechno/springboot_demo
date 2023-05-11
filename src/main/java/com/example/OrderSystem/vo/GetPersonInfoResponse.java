package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.PersonInfo;

public class GetPersonInfoResponse {

    private PersonInfo personInfo ;

    private String message ;
    //============

    public GetPersonInfoResponse(PersonInfo personInfo, String message) {
        this.personInfo = personInfo;
        this.message = message;
    }

    public GetPersonInfoResponse(String message) {
        this.message = message;
    }

    public GetPersonInfoResponse() {
    }
    //============


    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
