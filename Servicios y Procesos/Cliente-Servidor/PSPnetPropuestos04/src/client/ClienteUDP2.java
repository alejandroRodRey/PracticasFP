package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP2 {

	public static void main(String[] args) throws Exception {
		System.err.println("CLIENTE");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket clientSocket = new DatagramSocket();
		byte[] recibidos = new byte[1024];
		byte[] enviados = new byte[1024];

		InetAddress IPServidor = InetAddress.getLocalHost();
		int puerto = 9876;

		System.out.println("Introduce mensaje: ");
		String cadena = in.readLine();
		enviados = cadena.getBytes();

		System.out.println("Enviados " + enviados.length + " bytes al servidor.");
		DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
		clientSocket.send(envio);

		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("Esperando datagrama...");
		clientSocket.receive(recibo);
		String mayuscula = new String(recibo.getData());

		InetAddress iPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tProcedente de: " + iPOrigen + ":" + puertoOrigen);
		System.out.println("\tDatos: " + mayuscula.trim());

		System.err.println("EXIT");
	}

}
