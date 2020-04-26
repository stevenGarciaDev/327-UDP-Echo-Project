import java.net.*;
import java.io.*;
import java.util.*;

public class EchoServer {
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        DatagramSocket aSocket = null;
        DatagramPacket request = null;
        DatagramPacket reply = null;
        int serverPort = 0;

        try {
            serverPort = Integer.parseInt(args[0]);
            InetAddress aHost = InetAddress.getLocalHost();
            aSocket = new DatagramSocket(serverPort, aHost);
            System.out.println("UDP Echo Server created, port:" + serverPort);

            while (true) {
                System.out.println("listening for datagram packets...");
                byte[] buffer = new byte[1000];
                request = new DatagramPacket(buffer, buffer.length);
                System.out.println("about to receive packet");
                aSocket.receive(request);
                System.out.println("received packet");

                // output to console 
                System.out.println("Received message: " + new String(request.getData()));

                reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }

        } catch (SocketException e) {
            System.out.println("Error: Socket Exception " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: IOException " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
                System.out.println("Closed socket");
            }
        }

        scan.close();
    }
}