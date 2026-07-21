package com.hotelcore.controller;

import com.hotelcore.dto.RateRequest;
import com.hotelcore.dto.RateResponse;
import com.hotelcore.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public List<RateResponse> getAllRate(){
        return rateService.findAll();
    }

    @GetMapping("/{id}")
    public RateResponse getRateById(@PathVariable Long id) {
        return rateService.findById(id);
    }

    @PostMapping
    public RateResponse createRate(@RequestBody RateRequest request) {
        return rateService.saveRate(request);
    }

    @PutMapping("/{id}")
    public RateResponse updateRate(@PathVariable Long id,
                                   @RequestBody RateRequest request) {
        return rateService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteRate(@PathVariable Long id) {
        rateService.deleted(id);
    }
}
