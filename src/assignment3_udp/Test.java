package assignment3_udp;

import java.net.*;

public class Test {
	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		client.promptUser();
		EchoServer server = new EchoServer();
	}
}
