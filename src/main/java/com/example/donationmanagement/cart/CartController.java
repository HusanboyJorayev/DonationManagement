package com.example.donationmanagement.cart;

import com.example.donationmanagement.dto.ResponseDto;
import com.example.donationmanagement.dto.SimpleCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController implements SimpleCrud<Integer, CartDto> {
    private final CartService cartService;

    @Override
    @PostMapping("/create")
    public ResponseDto<CartDto> create(@RequestBody @Valid CartDto dto) {
        return this.cartService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<CartDto> get(@RequestParam Integer id) {
        return this.cartService.get(id);
    }


    @Override
    @PutMapping("/update")
    public ResponseDto<CartDto> update(@Valid @RequestBody CartDto dto, @RequestParam Integer id) {
        return this.cartService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<CartDto> delete(@RequestParam Integer id) {
        return this.cartService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<CartDto>> getAll() {
        return this.cartService.getAll();
    }
}
