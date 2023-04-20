package com.example.OrderSystem.vo;

import com.example.OrderSystem.entity.PersonInfo;

import java.util.List;

public class PersonInfoRequest {

    private List<PersonInfo> personInfoList;

    private String message;

    private String id;

//======================================

    public PersonInfoRequest() {
    }


//======================================


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
