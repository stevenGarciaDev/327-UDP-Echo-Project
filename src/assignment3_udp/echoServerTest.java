import java.net.*;
import java.io.*;
public class echoServerTest
{
    public static void main(String args[])
    {
        //assignment says to get port automatically, not hard coded
        //ip address seams to be auto, not sure about port?

        int port = 8000;
        // server creation
        DatagramSocket serverDGSocket = null;
        try
        {
            serverDGSocket = new DatagramSocket(port);
            System.out.println("UDP Echo Server created, port:" + port);
        }
        catch(IOException e)
        {
            //server couldnt be created i believe
            System.out.println(e);
            System.exit(1);
        }
        try
        {
            byte storage[] = new byte[1024];
            DatagramPacket messagePacket = new DatagramPacket(storage, storage.length);
            String message;
            while(true)
            {
            // listen for datagram packets
            serverDGSocket.receive(messagePacket);
            message = new String(messagePacket.getData(), 0, messagePacket.getLength());
            System.out.println("Received from server: "+message);
            // send received packet back to the client
            serverDGSocket.send(messagePacket);
            }
        }
        catch(IOException e)
        {
            //could not receive data correctly
            System.out.println(e);
        }
    }
}