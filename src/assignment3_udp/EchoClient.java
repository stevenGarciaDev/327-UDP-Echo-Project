package assignment3_udp;

import java.net.*;
import java.util.*;

public class EchoClient {
	private DatagramSocket dsocket;
	private DatagramPacket dpacket;
	private InetAddress host; // IP address
	private int serverPort;
	private String message;
	
	public void createSocketConnection()
	{
		try {
			dsocket = new DatagramSocket(this.serverPort, this.host);
		} catch (SocketException e) {
			System.out.println("Unable to create socket connection.");
		}
	}
	
	public void promptUser() {
		Scanner scan = new Scanner(System.in);
		boolean isInvalidInput = true;
		
		while (isInvalidInput) {
		
			try {
				System.out.print("Please enter the IP address: ");
				this.host = InetAddress.getByName(scan.nextLine());
				
				System.out.print("\n\nPlease enter the port number of the server: ");
				this.serverPort = Integer.parseInt(scan.nextLine());
				
				System.out.print("\n\nPlease enter the message to be sent: ");
				this.message = scan.nextLine();
				
				System.out.println("host " + this.host);
				System.out.println("serverPort " + serverPort);
				System.out.println("message " + message);
				
				isInvalidInput = false;
			
			} catch (Exception e) {
				System.out.println("ERROR: Invalid input entered. Please try again.\n");
			}
		}
		
		scan.close();
	}
}
