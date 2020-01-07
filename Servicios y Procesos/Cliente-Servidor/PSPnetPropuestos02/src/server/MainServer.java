package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

	public static void main(String[] args) throws Exception {
		System.err.println("SERVIDOR");
		ServerSocket serverSocket = new ServerSocket(6000);
		Socket socket = serverSocket.accept();

		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		System.out.print("Text input: ");
		pw.println(new Scanner(System.in).next());

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String text = br.readLine();
		System.out.println("Recibiendo: " + text);

		socket.close();
		serverSocket.close();
		System.err.println("EXIT");
	}

}
