package com.example.donationmanagement.cart;

import com.example.donationmanagement.dto.ErrorDto;
import com.example.donationmanagement.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartValidation {

    private final UserRepository userRepository;

    public List<ErrorDto> validate(CartDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if (userRepository.findByIdAndDeletedAtIsNull(dto.getUserId()).isEmpty()) {
            errors.add(new ErrorDto("users", "you cannot create card this id because  user is not found"));
        }
        if (StringUtils.isBlank(dto.getCardCode())) {
            errors.add(new ErrorDto("cardCode", "cardCode cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getCardNumber())) {
            errors.add(new ErrorDto("cardNumber", "cardNumber cannot be null or empty"));
        }
        if (dto.getUserId() == null) {
            errors.add(new ErrorDto("userId", "userId cannot be null"));
        }
        if (dto.getValidateYear() == null) {
            errors.add(new ErrorDto("validateYear", "validateYear cannot be null"));
        }
        return errors;
    }
}
