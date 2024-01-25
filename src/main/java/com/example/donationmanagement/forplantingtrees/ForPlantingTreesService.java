package com.example.donationmanagement.forplantingtrees;

import com.example.donationmanagement.cart.Cart;
import com.example.donationmanagement.cart.CartRepository;
import com.example.donationmanagement.dto.ResponseDto;
import com.example.donationmanagement.user.User;
import com.example.donationmanagement.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForPlantingTreesService {
    private final ForPlantingTreesRepository forPlantingTreesRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ForPlantingTressMapper forPlantingTressMapper;

    public String create(ForPlantingTreesDto dto) {
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

        var forPlanting = this.forPlantingTressMapper.toEntity(dto);
        forPlanting.setCreatedAt(LocalDateTime.now());
        this.forPlantingTreesRepository.save(forPlanting);

        cart.setBalance(cart.getBalance() - dto.getSendMoney());
        this.cartRepository.save(cart);
        return "Success";
    }

    public ResponseDto<ForPlantingTreesDto> get(Integer id) {
        Optional<ForPlantingTrees> optional = this.forPlantingTreesRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.<ForPlantingTreesDto>builder()
                    .code(-1)
                    .message("forPlantingTrees is not found")
                    .build();
        }
        var a = optional.get();
        return ResponseDto.<ForPlantingTreesDto>builder()
                .message("Ok")
                .success(true)
                .data(this.forPlantingTressMapper.toDto(a))
                .build();
    }

    public double getAllBalance() {
        List<ForPlantingTrees> plantingTrees = this.forPlantingTreesRepository.getAllForPlantingTrees();
        if (plantingTrees.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (ForPlantingTrees plantingTree : plantingTrees) {
            sum += plantingTree.getSendMoney();
        }
        return sum;
    }

}
