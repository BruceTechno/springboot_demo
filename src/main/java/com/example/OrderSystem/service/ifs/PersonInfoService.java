package com.example.OrderSystem.service.ifs;

import com.example.OrderSystem.entity.PersonInfo;
import com.example.OrderSystem.vo.AddPersonInfoRequest;
import com.example.OrderSystem.vo.GetPersonInfoByNameOrCityRequest;
import com.example.OrderSystem.vo.GetPersonInfoResponse;
import com.example.OrderSystem.vo.PersonInfoResponse;

import java.util.List;

public interface PersonInfoService {


//=================================
    public PersonInfoResponse addPersonInfo (List<PersonInfo> personInfo);
    public PersonInfoResponse getPersonInfo();

//    public GetPersonInfoResponse getPersonInfoById(String Id);

    public GetPersonInfoResponse getPersonInfoById(AddPersonInfoRequest request);

//    public PersonInfoResponse getPersonInfoById (String Id );

//    public PersonInfoResponse getPersonInfoById(PersonInfoRequest personInfoRequest);
    public PersonInfoResponse getPersonInfoByAgeGreaterThan(AddPersonInfoRequest personInfoRequest);

//    public PersonInfoResponse getPersonInfoByNameOrCity (GetPersonInfoByNameOrCityRequest request);

}
