package com.example.ntpservice.controller;

import com.example.ntpservice.services.NTPService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NTPController {

    private final NTPService ntpService;

    public NTPController(NTPService ntpService) {
        this.ntpService = ntpService;
    }

    @GetMapping("/ntp-time")
    public String getNTPTime() {
        return ntpService.getSyncedTime();
    }
}
