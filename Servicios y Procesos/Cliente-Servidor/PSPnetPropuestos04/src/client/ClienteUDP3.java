package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP3 {

	public static void main(String[] args) throws Exception {
		System.err.println("CLIENTE");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket clientSocket = new DatagramSocket();
		byte[] recibidos = new byte[1024];
		byte[] enviados = new byte[1024];

		InetAddress IPServidor = InetAddress.getLocalHost();
		int puerto = 9876;

		while (true) {
			System.out.println("Introduce mensaje: ");

			String cadena = null;
			try {
				cadena = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (cadena.trim().equals("*")) {
				enviados = cadena.getBytes();
				DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
				clientSocket.send(envio);
				break;
			}

			enviados = cadena.getBytes();

			System.out.println("Enviados " + enviados.length + " bytes al servidor.");
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
			clientSocket.send(envio);

			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
			System.out.println("Esperando datagrama...");
			clientSocket.setSoTimeout(5000);
			try {
				clientSocket.receive(recibo);

				String mayuscula = new String(recibo.getData());

				InetAddress iPOrigen = recibo.getAddress();
				int puertoOrigen = recibo.getPort();
				System.out.println("\tProcedente de: " + iPOrigen + ":" + puertoOrigen);
				System.out.println("\tDatos: " + mayuscula.trim());
			} catch (InterruptedIOException e) {
				System.err.println("El paquete se ha perdido por el camino...");
			}
		}

		System.err.println("EXIT");
	}

}
