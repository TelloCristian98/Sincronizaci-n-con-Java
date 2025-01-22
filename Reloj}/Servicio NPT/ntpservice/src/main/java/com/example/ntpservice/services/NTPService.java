package com.example.ntpservice.services;

import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

@Service
public class NTPService {

    private static final String NTP_SERVER = "time.google.com";
    private static final int NTP_PORT = 123;

    public String getSyncedTime() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(NTP_SERVER);

            byte[] buffer = new byte[48];
            buffer[0] = 0b00100011;

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, NTP_PORT);
            socket.send(request);

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getData());
            responseBuffer.position(40);

            long secondsSince1900 = responseBuffer.getInt() & 0xFFFFFFFFL;
            long secondsSince1970 = secondsSince1900 - 2208988800L;
            long currentTimeMillis = secondsSince1970 * 1000;

            return new java.util.Date(currentTimeMillis).toString();
        } catch (Exception e) {
            return "Error al sincronizar el tiempo: " + e.getMessage();
        }
    }
}
