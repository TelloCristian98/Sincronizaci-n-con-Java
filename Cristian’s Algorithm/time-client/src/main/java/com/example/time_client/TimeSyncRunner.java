package com.example.time_client;

import com.example.time_client.client.TimeSyncClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TimeSyncRunner implements CommandLineRunner {

    private final TimeSyncClient timeSyncClient;

    public TimeSyncRunner(TimeSyncClient timeSyncClient) {
        this.timeSyncClient = timeSyncClient;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando sincronización de tiempo...");
        timeSyncClient.synchronizeTime();
    }
}
