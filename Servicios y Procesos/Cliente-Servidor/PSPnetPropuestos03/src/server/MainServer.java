package server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

	private static int n = 3;

	public static void main(String[] args) throws Exception {
		System.err.println("SERVIDOR");

		System.out.print("Sockets number: ");

		n = new Scanner(System.in).nextInt();

		ServerSocket serverSocket = new ServerSocket(55000);
		Socket[] socket = new Socket[n];
		int i = 0;
		PrintWriter pw = null;

		for (Socket socketN : socket) {
			socketN = serverSocket.accept();
			i++;
			System.out.println(i + " - " + socketN.toString());
			pw = new PrintWriter(socketN.getOutputStream(), true);
			pw.println(i + " - " + socketN.toString());
		}

		pw.close();
		serverSocket.close();
		System.err.println("EXIT");
	}

}
