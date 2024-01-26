package com.example.donationmanagement.forcoveringstudentstudyloans;

import com.example.donationmanagement.cart.Cart;
import com.example.donationmanagement.cart.CartRepository;
import com.example.donationmanagement.dto.ResponseDto;
import com.example.donationmanagement.user.User;
import com.example.donationmanagement.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForCSSLService {
    private final ForCSSLRepository forCSSLRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ForCSSLMapper forCSSLMapper;

    public String create(ForCSSLDto dto) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(dto.getUserId());
        Optional<Cart> optionalCart = this.cartRepository.findByIdAndDeletedAtIsNull(dto.getUserId());


        if (optional.isEmpty()) {
            return "User is not found";
        }
        if (optionalCart.isEmpty()) {
            return "Cart is not found";
        }
        if (optionalCart.get().getValidateYear() - Year.now().getValue() > 10
                || optionalCart.get().getValidateYear() - Year.now().getValue() < 0) {
            return "Card is expired";
        }

        var user = optional.get();
        var cart = optionalCart.get();

        if (!dto.getCardNumber().equals(cart.getCardNumber())) {
            return "CardNumber is wrong";
        }

        if (cart.getBalance() < dto.getSendMoney()) {
            return "You dont have enough money ";
        }
        dto.setUsername(user.getFirstName());

        var forPlanting = this.forCSSLMapper.toEntity(dto);
        forPlanting.setCreatedAt(LocalDateTime.now());
        this.forCSSLRepository.save(forPlanting);

        cart.setBalance(cart.getBalance() - dto.getSendMoney());
        this.cartRepository.save(cart);
        return "Success";
    }

    public ResponseDto<ForCSSLDto> get(Integer id) {
        Optional<ForCSSL> optional = this.forCSSLRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ForCSSLDto>builder()
                    .code(-1)
                    .message("ForCSSL is not found")
                    .build();
        }
        var a = optional.get();
        return ResponseDto.<ForCSSLDto>builder()
                .message("Ok")
                .success(true)
                .data(this.forCSSLMapper.toDto(a))
                .build();
    }

    public double getAllBalance() {
        List<ForCSSL> plantingTrees = this.forCSSLRepository.getAllForPlantingTrees();
        if (plantingTrees.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (ForCSSL plantingTree : plantingTrees) {
            sum += plantingTree.getSendMoney();
        }
        return sum;
    }

}
