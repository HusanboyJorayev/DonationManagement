package com.example.donationmanagement.forfightingagainstglobalhunger;

import com.example.donationmanagement.cart.Cart;
import com.example.donationmanagement.cart.CartRepository;
import com.example.donationmanagement.dto.ResponseDto;
import com.example.donationmanagement.forcoveringstudentstudyloans.ForCSSL;
import com.example.donationmanagement.forcoveringstudentstudyloans.ForCSSLDto;
import com.example.donationmanagement.forcoveringstudentstudyloans.ForCSSLMapper;
import com.example.donationmanagement.forcoveringstudentstudyloans.ForCSSLRepository;
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
public class ForFAGHService {
    private final ForFAGHRepository forFAGHRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ForFAGHMapper forFAGHMapper;

    public String create(ForFAGHDto dto) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(dto.getUserId());
        Optional<Cart> optionalCart = this.cartRepository.findByIdAndDeletedAtIsNull(dto.getUserId());


        if (optional.isEmpty()) {
            return "User is not found";
        }
        if (optionalCart.isEmpty()) {
            return "Cart is not found";
        }
        if (optionalCart.get().getValidateYear() - Year.now().getValue() > 10) {
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

        var forFAGH = this.forFAGHMapper.toEntity(dto);
        forFAGH.setCreatedAt(LocalDateTime.now());
        this.forFAGHRepository.save(forFAGH);

        cart.setBalance(cart.getBalance() - dto.getSendMoney());
        this.cartRepository.save(cart);
        return "Success";
    }

    public ResponseDto<ForFAGHDto> get(Integer id) {
        Optional<ForFAGH> optional = this.forFAGHRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ForFAGHDto>builder()
                    .code(-1)
                    .message("ForCSSL is not found")
                    .build();
        }
        var a = optional.get();
        return ResponseDto.<ForFAGHDto>builder()
                .message("Ok")
                .success(true)
                .data(this.forFAGHMapper.toDto(a))
                .build();
    }

    public double getAllBalance() {
        List<ForFAGH> plantingTrees = this.forFAGHRepository.getAllForPlantingTrees();
        if (plantingTrees.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (ForFAGH plantingTree : plantingTrees) {
            sum += plantingTree.getSendMoney();
        }
        return sum;
    }

}
