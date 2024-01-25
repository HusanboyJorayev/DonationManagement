package com.example.donationmanagement.auth;

import com.example.donationmanagement.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthValidation {

    public List<ErrorDto>validate(AuthenticateRequest request){
        List<ErrorDto>list=new ArrayList<>();

        if (StringUtils.isBlank(request.getEmail())){
            list.add(new ErrorDto("email","email cannot be null or empty"));
        }
        if (StringUtils.isBlank(request.getPassword())){
            list.add(new ErrorDto("Password","Password cannot be null or empty"));
        }

        return list;
    }
}
