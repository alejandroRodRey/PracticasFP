package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP2 {

	public static void main(String[] args) throws Exception {
		System.err.println("SERVIDOR");

		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] recibidos = new byte[1024];
		byte[] enviados = new byte[1024];
		String cadena;

		while (true) {
			System.out.println("Esperando datagrama...");
			recibidos = new byte[1024];
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido);
			cadena = new String(paqRecibido.getData());

			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
			System.out.println("\tMensaje recibido: " + cadena.trim());

			String mayuscula = cadena.trim().toUpperCase();
			enviados = mayuscula.getBytes();

			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);

			if (cadena.trim().equals("*"))
				break;
		}
		serverSocket.close();

		System.err.println("EXIT");
	}

}
