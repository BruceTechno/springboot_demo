package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.PersonInfo;

import java.util.List;

public class AddPersonInfoRequest {

    private List<PersonInfo> personInfoList;

    private String message;

    private String id;

    private int age ;

    private int age2 ;

    private  String cityStr;

    private PersonInfo personInfo;

//======================================

    public AddPersonInfoRequest() {
    }


//======================================


    public String getCityStr() {
        return cityStr;
    }

    public void setCityStr(String cityStr) {
        this.cityStr = cityStr;
    }

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
