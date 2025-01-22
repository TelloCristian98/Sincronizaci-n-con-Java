package com.example.ntpclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeService {

    private final String ntpServiceUrl = "http://localhost:8080/ntp-time";

    public String getTimeFromNTPService() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ntpServiceUrl, String.class);
    }
}
