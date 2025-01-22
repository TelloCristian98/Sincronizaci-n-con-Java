import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
    int id;
    int clockTime; // Tiempo en segundos (para facilitar cálculos)

    public Node(int id, int clockTime) {
        this.id = id;
        this.clockTime = clockTime;
    }

    public void adjustTime(int adjustment) {
        clockTime += adjustment;
        System.out.println("🕒 Nodo " + id + " ajustó su tiempo a " + clockTime + " segundos.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Node> nodes = new ArrayList<>();

        System.out.println("🕒 Hora actual del sistema: " + LocalTime.now());

        // Obtener la hora exacta del servidor NTP
        long ntpTime = obtenerTiempoNTP();
        System.out.println("🌐 Hora obtenida desde NTP (Google): " + Instant.ofEpochSecond(ntpTime));

        // Pedir al usuario ingresar nodos
        System.out.print("Ingrese el número de nodos: ");
        int numNodes = scanner.nextInt();

        // Capturar tiempos de los nodos ingresados manualmente
        for (int i = 0; i < numNodes; i++) {
            System.out.print("Ingrese el tiempo del nodo " + i + " (en segundos): ");
            int time = scanner.nextInt();
            nodes.add(new Node(i, time));
        }

        // Sincronización de relojes (Algoritmo de Berkeley)
        int totalTime = 0;
        for (Node node : nodes) {
            totalTime += node.clockTime;
        }
        int averageTime = (int) ((totalTime + ntpTime) / (nodes.size() + 1)); // Incluir el tiempo NTP

        System.out.println("⏳ Tiempo promedio calculado: " + averageTime + " segundos");

        // Ajustar todos los nodos al tiempo promedio
        for (Node node : nodes) {
            int adjustment = averageTime - node.clockTime;
            node.adjustTime(adjustment);
        }

        System.out.println("✅ Sincronización completada.");
        scanner.close();
    }

    // Método para obtener la hora desde el servidor NTP de Google
    public static long obtenerTiempoNTP() {
        String ntpServer = "time.google.com";
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(ntpServer);
            byte[] buffer = new byte[48];
            buffer[0] = 0x1B; // Código para solicitud NTP

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, 123);
            socket.send(request);

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            socket.close();

            // Convertir la respuesta a tiempo en segundos
            long secondsSince1900 = ((buffer[43] & 0xFF) | (buffer[42] & 0xFF) << 8 | (buffer[41] & 0xFF) << 16 | (buffer[40] & 0xFF) << 24);
            long secondsSince1970 = secondsSince1900 - 2208988800L;
            return secondsSince1970;
        } catch (Exception e) {
            System.err.println("⚠️ Error obteniendo tiempo NTP: " + e.getMessage());
            return System.currentTimeMillis() / 1000; // Si falla, usa la hora local
        }
    }
}
