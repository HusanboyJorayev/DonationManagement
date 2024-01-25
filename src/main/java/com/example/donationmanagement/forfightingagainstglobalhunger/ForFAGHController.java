package com.example.donationmanagement.forfightingagainstglobalhunger;

import com.example.donationmanagement.dto.ResponseDto;
import com.example.donationmanagement.forcoveringstudentstudyloans.ForCSSLDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("forFAGH")
public class ForFAGHController {

    private final ForFAGHService forFAGHService;

    @PostMapping("/create")
    public String create(@RequestBody ForFAGHDto dto) {
        return this.forFAGHService.create(dto);
    }

    @GetMapping("/get")
    public ResponseDto<ForFAGHDto> get(@RequestParam Integer id) {
        return this.forFAGHService.get(id);
    }

    @GetMapping("/getAllBalance")
    public double getAllBalance() {
        return this.forFAGHService.getAllBalance();
    }
}
