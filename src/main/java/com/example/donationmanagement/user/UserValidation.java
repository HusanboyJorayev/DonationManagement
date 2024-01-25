package com.example.donationmanagement.user;


import com.example.donationmanagement.auth.RegisterRequest;
import com.example.donationmanagement.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {

    public List<ErrorDto> validate(RegisterRequest request) {

        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(request.getPassword())) {
            errors.add(new ErrorDto("password", "password cannot be null or empty"));
        }
        if (StringUtils.isBlank(request.getFirstname())) {
            errors.add(new ErrorDto("firstname", "firstname cannot be null or empty"));
        }
        if (StringUtils.isBlank(request.getLastname())) {
            errors.add(new ErrorDto("lastname", "lastname cannot be null or empty"));
        }
        if (StringUtils.isBlank(request.getEmail())) {
            errors.add(new ErrorDto("email", "email cannot be null or empty"));
        }
        return errors;
    }
}
