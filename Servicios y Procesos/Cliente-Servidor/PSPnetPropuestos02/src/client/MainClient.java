package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClient {

	public static void main(String[] args) throws Exception {
		System.err.println("CLIENTE");
		Socket socket = new Socket("localhost", 6000);

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		
		String text = br.readLine();
		System.out.println("Recibiendo: " + text);
		pw.println(text);

		socket.close();
		System.err.println("EXIT");
	}

}
