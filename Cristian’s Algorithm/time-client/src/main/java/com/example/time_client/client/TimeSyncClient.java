package com.example.time_client.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Component
public class TimeSyncClient {

    private static final String SERVER_URL = "http://localhost:8080/time";
//    private static final String SERVER_URL = "http://192.168.1.10:8080/time";

    public void synchronizeTime() {
        // Tiempo inicial (T0)
        Instant T0 = Instant.now();

        // Realizar solicitud al servidor
        RestTemplate restTemplate = new RestTemplate();
        Instant serverTime = restTemplate.getForObject(SERVER_URL, Instant.class);

        // Tiempo final (T1)
        Instant T1 = Instant.now();

        // Calcular latencia
        long latencyMillis = (T1.toEpochMilli() - T0.toEpochMilli()) / 2;

        // Ajustar hora del servidor
        Instant adjustedTime = serverTime.plusMillis(latencyMillis);

        System.out.println("Hora del servidor: " + serverTime);
        System.out.println("Latencia estimada: " + latencyMillis + " ms");
        System.out.println("Hora ajustada: " + adjustedTime);
    }
}
