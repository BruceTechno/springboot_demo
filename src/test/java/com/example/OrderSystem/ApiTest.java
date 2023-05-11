package com.example.OrderSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiTest {
    @SuppressWarnings("uncheked")
    @Test
    public void GetApiTest() throws JsonProcessingException {
        String targetUrl = "https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take=10&skip=20";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resString = restTemplate.getForEntity(targetUrl, String.class);

        System.out.println(resString.getStatusCode());
        System.out.println(resString.getStatusCodeValue());
        String responseString = resString.getBody();
        System.out.println(responseString);
        System.out.println("======================================================");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> resList = mapper.readValue(responseString,List.class);
        for (Map<String,Object> item : resList){
            for (Map.Entry<String,Object> map : item.entrySet()){
                System.out.println("Key: " + map.getKey());
                System.out.println("Value:"+ (String)map.getValue());
            }
        }
    }
    @Test
    public void postApiTest(){
        String targetUrl ="http://172.20.10.8:8080/api/register";
        Map<String,String> bodyMap = new HashMap<>();
        bodyMap.put("account","Rock_Monkey555");
        bodyMap.put("pwd","55688");

        ObjectMapper mapper = new ObjectMapper();
        try{
            String reqBodyStr = mapper.writeValueAsString(bodyMap);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestBody = new HttpEntity<>(reqBodyStr,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,requestBody, String.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
        }catch (Exception e){

        }
    }
}
