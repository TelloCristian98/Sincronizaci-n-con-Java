package com.example.timeserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TimeController {

    @GetMapping("/time")
    public Instant getTime() {
        return Instant.now();
    }

}
