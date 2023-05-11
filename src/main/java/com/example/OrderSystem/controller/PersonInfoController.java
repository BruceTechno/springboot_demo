package com.example.OrderSystem.controller;

import com.example.OrderSystem.service.ifs.PersonInfoService;
import com.example.OrderSystem.vo.GetPersonInfoResponse;
import com.example.OrderSystem.vo.AddPersonInfoRequest;
import com.example.OrderSystem.vo.PersonInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoController {
    @Autowired
    PersonInfoService personInfoService;

    @PostMapping(value = "add_person_information")
    public PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest addPersonInfoRequest) {
        return personInfoService.addPersonInfo(addPersonInfoRequest.getPersonInfoList());
    }

    @GetMapping(value = "get_person_information")
    public PersonInfoResponse getPersonInfo(@RequestBody AddPersonInfoRequest addPersonInfoRequest) {
        return personInfoService.getPersonInfo();
    }

    //    @PostMapping(value ="get_person_information_by_id" )
//    public GetPersonInfoResponse getPersonInfoById(@RequestBody AddPersonInfoRequest addPersonInfoRequest){
//        return personInfoService.getPersonInfoById(.addPersonInfoRequest.getId());
//    }
    @PostMapping(value = "get_person_information_by_id2")
    public GetPersonInfoResponse getPersonInfoById(@RequestBody AddPersonInfoRequest personInfoRequest) {
        return personInfoService.getPersonInfoById(personInfoRequest);
    }
@PostMapping(value = "get_information_by_age_greater_than")
    public PersonInfoResponse getPersonInfoByAgeGreaterThan(@RequestBody AddPersonInfoRequest addPersonInfoRequest){
        return personInfoService.getPersonInfoByAgeGreaterThan(addPersonInfoRequest);
    }
}
