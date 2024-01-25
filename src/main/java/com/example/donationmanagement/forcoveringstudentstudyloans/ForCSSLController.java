package com.example.donationmanagement.forcoveringstudentstudyloans;

import com.example.donationmanagement.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("forCSSL")
public class ForCSSLController {

    private final ForCSSLService forCSSLService;

    @PostMapping("/create")
    public String create(@RequestBody ForCSSLDto dto) {
        return this.forCSSLService.create(dto);
    }

    @GetMapping("/get")
    public ResponseDto<ForCSSLDto> get(@RequestParam Integer id) {
        return this.forCSSLService.get(id);
    }

    @GetMapping("/getAllBalance")
    public double getAllBalance() {
        return this.forCSSLService.getAllBalance();
    }
}
