import java.net.*;
import java.io.*;
public class echoServerTest
{
    public static void main(String args[])
    {
        //assignment says to get port automatically, not hard coded
        //ip address seams to be auto, not sure about port?

        
        // server creation
        DatagramSocket serverDGSocket = null;
        try
        {
            int port = Integer.parseInt(args[0]);
            serverDGSocket = new DatagramSocket(port);
            System.out.println("UDP Echo Server created, port:" + port);
        }
        catch(IOException e)
        {
            //server couldnt be created
            System.out.println("Error: server could not be created: " + e.getMessage());
            System.exit(1);
        }
        try
        {
            byte storage[] = new byte[1024];
            DatagramPacket messagePacket = new DatagramPacket(storage, storage.length);
            String message;
            while(true)
            {
                System.out.println("listening for datagram packets...");
                // listen for datagram packets
                serverDGSocket.receive(messagePacket);
                message = new String(messagePacket.getData(), 0, messagePacket.getLength());
                System.out.println("Received from server: " + message);

                // echo the data 
                DatagramPacket response = new DatagramPacket(messagePacket.getData(), messagePacket.getLength(), messagePacket.getAddress(), messagePacket.getPort());

                // send received packet back to the client
                serverDGSocket.send(response);
                System.out.println("listening for datagram packets...");
            }
        }
        catch(IOException e)
        {
            //could not receive data correctly
            System.out.println(e);
        }
    }
}