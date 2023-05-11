package com.example.OrderSystem.controller;

import com.example.OrderSystem.constants.RtnCode;
import com.example.OrderSystem.service.ifs.RegisterService;
import com.example.OrderSystem.vo.RegisterRequest;
import com.example.OrderSystem.vo.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping(value = "register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        return registerService.register(request.getAccount(), request.getPwd());
    }
    @PostMapping(value = "active")
     public RegisterResponse active(@RequestBody RegisterRequest request){
        return registerService.active(request.getAccount(), request.getPwd());
    }
    @PostMapping(value = "login" )
    public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession httpSession){
        RegisterResponse result = registerService.login(request.getAccount(), request.getPwd());
        if (result.getMessage().equalsIgnoreCase(RtnCode.SUCCESSFUL.getMessage())) {
            double random = Math.random() * 10000;
            int verifyCode = (int) Math.round(random);//round四捨五入
            httpSession.setAttribute("verifyCode", verifyCode);
            httpSession.setAttribute("account", request.getAccount());
            httpSession.setAttribute("pwd",request.getPwd());
            httpSession.setMaxInactiveInterval(60);//設定session存活時間 單位:秒
            result.setSessionId(httpSession.getId());
            result.setVerifyCode(verifyCode);
//            return result;
        }
        return result;
    }
    @PostMapping(value = "get reg time")
    public RegisterResponse getRegTime(@RequestBody RegisterRequest request){
        return registerService.getRegTime(request.getAccount(), request.getPwd());
    }
    @PostMapping(value = "get reg time2")
    public RegisterResponse getRegTime2(@RequestBody RegisterRequest request ,HttpSession session){
        String account = (String) session.getAttribute("account");
        String pwd = (String) session.getAttribute("pwd");
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)){
            return new RegisterResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        Integer verifyCode = (Integer) session.getAttribute("verifyCode") ;
        if (verifyCode == null || verifyCode != request.getVerifyCode()){//還沒登入就 getRegTime >>沒 verifyCode 或 過期了
            return new RegisterResponse("Verify code error");
        }
        return registerService.getRegTime(account,pwd);
    }
    @PostMapping(value = "get reg time3")
    public RegisterResponse getRegTime3(@RequestBody RegisterRequest request ,HttpSession session){
        String account = (String) session.getAttribute("account");
        String pwd = (String) session.getAttribute("pwd");
        Integer verifyCode = (Integer) session.getAttribute("verifyCode") ;

        return registerService.getRegTime3(request,account,pwd,verifyCode);
    }

}
