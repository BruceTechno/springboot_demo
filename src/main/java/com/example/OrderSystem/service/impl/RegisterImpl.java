package com.example.OrderSystem.service.impl;

import com.example.OrderSystem.constants.RtnCode;
import com.example.OrderSystem.entity.Register;
import com.example.OrderSystem.repository.RegisterDao;
import com.example.OrderSystem.service.ifs.RegisterService;
import com.example.OrderSystem.vo.RegisterRequest;
import com.example.OrderSystem.vo.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Service
public class RegisterImpl implements RegisterService {
    @Autowired
    private RegisterDao registerDao;
    @Override
    public RegisterResponse register(String account, String pwd) {
        String patternAccount = "^[^\\s]{3,8}$";
        String patternPwd = "^(?=.+[\\p{Punct}])(?!.*[\\s\\t\\r\\n\\f])[\\p{Print}]{8,12}$";
       if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)){
           return new RegisterResponse(RtnCode.CANNOT_EMPTY.getMessage());
       }
       if (!account.matches(patternAccount) || !pwd.matches(patternPwd) ){
           return new RegisterResponse(RtnCode.DATA_ERROR.getMessage());//格式錯誤
       }

        if (registerDao.existsById(account)){
        return new RegisterResponse(RtnCode.DATA_ERROR.getMessage()) ;//此帳號已經存在
        }
        Register register = new Register(account,pwd);
        registerDao.save(register);
        return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage(),register);
    }

    @Override
    public RegisterResponse active(String account, String pwd) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)){
            return new RegisterResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        Register result = registerDao.findByAccountAndPwd(account, pwd);
        if (result == null){
            return new RegisterResponse(RtnCode.NOT_FOUND.getMessage());
        }
        result.setActive(true);
        registerDao.save(result);
        return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage(),result);
    }

    @Override
    public RegisterResponse login(String account, String pwd) {

        Register result = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
        if (result == null){
            return new RegisterResponse("GG");//登入失敗或未激活
        }

        return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage());
    }

    @Override
    public RegisterResponse getRegTime(String account, String pwd) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)){
            return new RegisterResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        Register result = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
        if (result == null){
            return new RegisterResponse(RtnCode.DATA_ERROR.getMessage());//登入失敗或未激活
        }

        return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage(),result.getRegTime());
    }

    @Override
    public RegisterResponse getRegTime3(RegisterRequest request, String account, String pwd, Integer verifyCode) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)){
            return new RegisterResponse("Please login!!");
        }
        if (verifyCode == null || verifyCode != request.getVerifyCode()){//還沒登入就 getRegTime >>沒 verifyCode 或 過期了
            return new RegisterResponse("Verify code error");
        }

        Register result = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
        if (result == null){
            return new RegisterResponse(RtnCode.DATA_ERROR.getMessage());//登入失敗或未激活
        }
        return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage(),result.getRegTime());
    }
}
