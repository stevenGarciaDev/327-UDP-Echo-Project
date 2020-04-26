import java.net.*;
import java.util.*;
import java.io.*;

public class EchoClient {

	public static void main(String[] args) {
		InetAddress aHost = null;
		DatagramSocket aSocket = null;
		DatagramPacket request = null;
		int serverPort = 0;
		String inputMessage = "";
		byte[] message = null;

		// promp user for input
		Scanner scan = new Scanner(System.in);
		boolean isInvalidInput = true;
		
		while (isInvalidInput) {
		
			try {
				System.out.print("Please enter the IP address: ");
				aHost = InetAddress.getByName(scan.nextLine());
				
				System.out.print("\n\nPlease enter the port number of the server: ");
				serverPort = Integer.parseInt(scan.nextLine());

				System.out.print("\n\nPlease enter the message to be sent: ");
				inputMessage = scan.nextLine();
				message = inputMessage.getBytes();
				System.out.println("the length is " + message.length);

				aSocket = new DatagramSocket();

				// send the message to the server
				request = new DatagramPacket(message, message.length, aHost, serverPort);
				aSocket.send(request);
				System.out.println("Message sent");

				// display the response by the server 
				byte[] buffer = new byte[100];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);
				System.out.println("Reply: " + new String(reply.getData()));
				
				isInvalidInput = false;
			
			} catch (SocketException e) {
				System.out.println("ERROR with Socket: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("ERROR with IO: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			} finally {
				if (aSocket != null) aSocket.close();
			}
		}
		
		scan.close();
	}
}
