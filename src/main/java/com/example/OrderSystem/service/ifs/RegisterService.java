package com.example.OrderSystem.service.ifs;

import com.example.OrderSystem.entity.Register;
import com.example.OrderSystem.vo.RegisterRequest;
import com.example.OrderSystem.vo.RegisterResponse;

public interface RegisterService {
    public RegisterResponse register (String account , String pwd);
    public RegisterResponse active(String account , String pwd);
    public RegisterResponse login(String account,String pwd);
    public RegisterResponse getRegTime(String account,String pwd);
    public RegisterResponse getRegTime3(RegisterRequest request,String account,String pwd,Integer verifyCode);
                                                //要輸入的verifyCode                                 session的verifyCode
}
