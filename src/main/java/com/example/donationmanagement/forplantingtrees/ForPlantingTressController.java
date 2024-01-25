package com.example.donationmanagement.forplantingtrees;

import com.example.donationmanagement.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("forPlantingTrees")
public class ForPlantingTressController {

    private final ForPlantingTreesService forPlantingTreesService;

    @PostMapping("/create")
    public String create(@RequestBody ForPlantingTreesDto dto) {
        return this.forPlantingTreesService.create(dto);
    }

    @GetMapping("/get")
    public ResponseDto<ForPlantingTreesDto> get(@RequestParam Integer id) {
        return this.forPlantingTreesService.get(id);
    }

    @GetMapping("/getAllBalance")
    public double getAllBalance() {
        return this.forPlantingTreesService.getAllBalance();
    }
}
