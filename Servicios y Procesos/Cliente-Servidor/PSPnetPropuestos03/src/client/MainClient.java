package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainClient {

	public static void main(String[] args) throws Exception {
		System.err.println("CLIENTE");

		Socket socket = new Socket("localhost", 55000);

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String s = null;

		s = br.readLine();
		System.out.println(s);

		br.close();
		socket.close();
		System.err.println("EXIT");
	}

}
