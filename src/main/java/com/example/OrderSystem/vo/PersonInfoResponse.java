package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.PersonInfo;
import com.example.OrderSystem.repository.PersonInfoDao;

import java.util.List;

public class PersonInfoResponse {
    private List<PersonInfo> personInfoList;
    private String message;
    private PersonInfo personInfo;

    //========================================================================



    public PersonInfoResponse(PersonInfo personInfo, String message) {
        this.personInfo = personInfo;
        this.message = message;

    }

    public PersonInfoResponse(List<PersonInfo> personInfoList, String message) {
        this.personInfoList = personInfoList;
        this.message = message;
    }

    public PersonInfoResponse(String message) {
        this.message = message;
    }

    public PersonInfoResponse(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }

    public PersonInfoResponse() {
    }
    //==================================================================

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public List<PersonInfo> getPersonInfoList() {
        return personInfoList;
    }

    public void setPersonInfoList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
