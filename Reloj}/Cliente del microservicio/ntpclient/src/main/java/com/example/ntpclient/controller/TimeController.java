package com.example.ntpclient.controller;

import com.example.ntpclient.service.TimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/current-time")
    public String getCurrentTime() {
        return timeService.getTimeFromNTPService();
    }
}
