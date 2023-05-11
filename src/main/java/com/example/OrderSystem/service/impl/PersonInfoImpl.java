package com.example.OrderSystem.service.impl;

import ch.qos.logback.classic.spi.STEUtil;

import com.example.OrderSystem.entity.PersonInfo;
import com.example.OrderSystem.repository.PersonInfoDao;
import com.example.OrderSystem.service.ifs.PersonInfoService;
import com.example.OrderSystem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonInfoImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;


    @Override
    public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
        List<String> idsList = new ArrayList<>();
        if (CollectionUtils.isEmpty(personInfoList)) {
            return new PersonInfoResponse("personInfo ist Empty");
        }
        // id name age city
        for (PersonInfo item : personInfoList) {
            if (!StringUtils.hasText(item.getId())) {
                //list.add item.getId
                return new PersonInfoResponse("id is empty");
            }
            //多一個if 判斷 item.getId contains list //這樣寫只能 for 此次輸入 無法去跟資料庫裏面的比對
            if (!StringUtils.hasText(item.getName())) {
                return new PersonInfoResponse("name is empty");
            }
            if (item.getAge() < 0) {
                return new PersonInfoResponse("age won't be less than zero");
            }
            if (!StringUtils.hasText(item.getCity())) {

                return new PersonInfoResponse("city information is wrong ");
            }
            idsList.add(item.getId());
        }
        List<PersonInfo> res = personInfoDao.findAllById(idsList);//拿 45行本次要加入的ID去資料庫裏面搜尋 如果搜尋到了
        if (res.size() == personInfoList.size() ){
            return new PersonInfoResponse("此筆資料全部重複");
        }
        if (res.size() > 0) {                                        //代表要加進去的  資料已經存在了 即為重複。

            List<String> resId = new ArrayList<>();
            for (PersonInfo item : res) {
                resId.add(item.getId());
            }
            List<PersonInfo> saveInfo = new ArrayList<>();
            for (PersonInfo item : personInfoList) {
                if (!resId.contains(item.getId())) {
                    saveInfo.add(item);
                }
            }
            personInfoDao.saveAll(saveInfo);
            return new PersonInfoResponse(saveInfo,"資料除了有重複地以外都成功的新增了");
        }
        List<PersonInfo> response = personInfoDao.saveAll(personInfoList);
        return new PersonInfoResponse(response, "add successfully");
    }


    @Override
    public PersonInfoResponse getPersonInfo() {
        List<PersonInfo> result = personInfoDao.findAll();
        return new PersonInfoResponse(result);
    }

    @Override
    public GetPersonInfoResponse getPersonInfoById(AddPersonInfoRequest request) {
        if (!StringUtils.hasText(request.getId())){
            return new GetPersonInfoResponse("Id format is error");
        }
        Optional<PersonInfo> op = personInfoDao.findById(request.getId());
        if (!op.isPresent()){
            return new GetPersonInfoResponse("There is no information in DB");
        }
        return new GetPersonInfoResponse(op.get(),"successful");
    }

    @Override
    public PersonInfoResponse getPersonInfoByAgeGreaterThan(AddPersonInfoRequest personInfoRequest) {
        String cityStr = personInfoRequest.getCityStr();
        int age = personInfoRequest.getAge();
        int age2 = personInfoRequest.getAge2();
        if (age < 1 && age2 <1 ){
            return new PersonInfoResponse("age can't be negative ");
        }

    ///GreaterThan
//        List<PersonInfo> finalInfoList = personInfoDao.findByAgeGreaterThan(age);//=105~119行
    //LessThan
//        List<PersonInfo> finalInfoList = personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
    //Between
//        List<PersonInfo> finalInfoList = personInfoDao.findByAgeBetween(age , age2);

     //Between (3)
//        List<PersonInfo> finalInfoList = personInfoDao.findTop3ByAgeBetweenOrderByAgeDesc(age , age2);

//        List<PersonInfo> finalInfoList= personInfoDao.findByCityContaining(cityStr);

//        List<PersonInfo> finalInfoList = personInfoDao.findByAgeGreaterThanAndCityContainingOrderByAge(age,cityStr);

        List<PersonInfo> finalInfoList = personInfoDao.findByAgeLessThanOrAgeGreaterThan(age, age2);

        /*  第四題暫放  int age = personInfoRequest.getAge();

        if (age < 1 ){
            return new PersonInfoResponse("age can't be negative ");
        }

        List<String> targetIdList = new ArrayList<>();
        List<PersonInfo> dbAllInfo = personInfoDao.findAll();

        for (PersonInfo item : dbAllInfo){
            if (age < item.getAge()){
                targetIdList.add(item.getId());
            }
        }
        List<PersonInfo> finalInfoList = personInfoDao.findAllById(targetIdList);*/

        return new  PersonInfoResponse( finalInfoList , "successful");
    }

//    @Override
//    public PersonInfoResponse getPersonInfoByNameOrCity(GetPersonInfoByNameOrCityRequest request) {
//        String name = StringUtils.hasText(request.getName()) ? request.getName() : null;
//        String city = StringUtils.hasText(request.getCity()) ? request.getCity() : null;
//        List<PersonInfo> result = personInfoDao.findByNameContainingOrCityContaining(name, city);
//        return new PersonInfoResponse(result);
//    }
}
    //    @Override
//    public PersonInfoResponse getPersonInfoById(String Id) {
//        if (!StringUtils.hasText(Id)){
//            return new PersonInfoResponse("Id format is error");
//        }
//        Optional<PersonInfo> op = personInfoDao.findById(Id);
//        if (!op.isPresent()){
//            return new PersonInfoResponse("There is no information in DB");
//        }
//        return new PersonInfoResponse(op.get(),"successful");
//    }

//    @Override
//    public GetPersonInfoResponse getPersonInfoById(String Id) {
//        if (!StringUtils.hasText(Id)) {
//            return new GetPersonInfoResponse("Id format is error");
//        }
//        Optional<PersonInfo> op = personInfoDao.findById(Id);
//        if (!op.isPresent()) {
//            return new GetPersonInfoResponse("There is no information in DB");
//        }
//        return new GetPersonInfoResponse(op.get(), "successful");
//    }


/*  第四題暫放  int age = personInfoRequest.getAge();

        if (age < 1 ){
            return new PersonInfoResponse("age can't be negative ");
        }

        List<String> targetIdList = new ArrayList<>();
        List<PersonInfo> dbAllInfo = personInfoDao.findAll();

        for (PersonInfo item : dbAllInfo){
            if (age < item.getAge()){
                targetIdList.add(item.getId());
            }
        }
        List<PersonInfo> finalInfoList = personInfoDao.findAllById(targetIdList);*/