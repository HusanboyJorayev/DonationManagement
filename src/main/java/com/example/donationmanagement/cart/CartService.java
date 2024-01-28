package com.example.donationmanagement.cart;

import com.example.donationmanagement.dto.ErrorDto;
import com.example.donationmanagement.dto.ResponseDto;
import com.example.donationmanagement.dto.SimpleCrud;
import com.example.donationmanagement.user.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService implements SimpleCrud<Integer, CartDto> {
    private final CartMapper cartMapper;
    private final CartValidation cartValidation;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<CartDto> create(CartDto dto) {
        List<ErrorDto> errors = this.cartValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CartDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        try {
            Cart cart = this.cartMapper.toEntity(dto);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setDate(LocalDate.now().toString());
            this.cartRepository.save(cart);

            return ResponseDto.<CartDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.cartMapper.toDto(cart))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CartDto>builder()
                    .code(-1)
                    .message("Type while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CartDto> get(Integer id) {
        try {
            return this.cartRepository.findByIdAndDeletedAtIsNull(id)
                    .map(cart -> ResponseDto.<CartDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.cartMapper.toDto(cart))
                            .build())
                    .orElse(ResponseDto.<CartDto>builder()
                            .code(-1)
                            .message("group is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<CartDto>builder()
                    .code(-1)
                    .message("group while getting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CartDto> update(CartDto dto, Integer id) {
        List<ErrorDto> errors = this.cartValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CartDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }

        try {
            return this.cartRepository.findByIdAndDeletedAtIsNull(id)
                    .map(cart -> {
                        cart.setUpdatedAt(LocalDateTime.now());
                        this.cartMapper.update(cart, dto);
                        this.cartRepository.save(cart);

                        return ResponseDto.<CartDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.cartMapper.toDto(cart))
                                .build();
                    })
                    .orElse(ResponseDto.<CartDto>builder()
                            .code(-1)
                            .message("group is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<CartDto>builder()
                    .code(-1)
                    .message("group while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CartDto> delete(Integer id) {
        try {
            return this.cartRepository.findByIdAndDeletedAtIsNull(id)
                    .map(cart -> {
                        cart.setDeletedAt(LocalDateTime.now());
                        this.cartRepository.delete(cart);

                        return ResponseDto.<CartDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.cartMapper.toDto(cart))
                                .build();
                    })
                    .orElse(ResponseDto.<CartDto>builder()
                            .code(-1)
                            .message("group is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<CartDto>builder()
                    .code(-1)
                    .message("group while deleting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<List<CartDto>> getAll() {
        try {
            List<Cart> allCarts = this.cartRepository.getAllCarts();
            if (allCarts.isEmpty()) {
                return ResponseDto.<List<CartDto>>builder()
                        .code(-1)
                        .message("groups are not found")
                        .build();
            }
            return ResponseDto.<List<CartDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(allCarts.stream().map(this.cartMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<CartDto>>builder()
                    .code(-1)
                    .message("groups while getting  all")
                    .build();
        }
    }

    public ResponseDto<List<CartDto>> codeStartsWith(String code) {
        List<Cart> cartPage = this.cartRepository.codeStartsWith(code);

        if (cartPage.isEmpty()) {
            return ResponseDto.<List<CartDto>>builder()
                    .code(-1)
                    .message("Carts are not found")
                    .build();
        }
        return ResponseDto.<List<CartDto>>builder()
                .success(true)
                .message("Ok")
                .data(cartPage.stream().map(this.cartMapper::toDto).toList())
                .build();
    }
    public ResponseDto<Page<CartDto>> pageResponseSorting(Integer page, Integer size) {
        Page<Cart> cartPage = this.cartRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size,
                Sort.by("balance").ascending().and(Sort.by("cardCode").descending())));

        if (cartPage.isEmpty()) {
            return ResponseDto.<Page<CartDto>>builder()
                    .code(-1)
                    .message("Carts are not found")
                    .build();
        }
        return ResponseDto.<Page<CartDto>>builder()
                .success(true)
                .message("Ok")
                .data(cartPage.map(this.cartMapper::toDto))
                .build();
    }

    public ResponseDto<Page<CartDto>> getCodeStartsWith(String code, Integer page, Integer size) {
        Page<Cart> carts = this.cartRepository.findByCardCodeStartsWith(code, PageRequest.of(page, size));
        if (carts.isEmpty()) {
            return ResponseDto.<Page<CartDto>>builder()
                    .code(-1)
                    .message("Error")
                    .build();
        }
        return ResponseDto.<Page<CartDto>>builder()
                .success(true)
                .message("Ok")
                .data(carts.map(this.cartMapper::toDto))
                .build();

    }
}
