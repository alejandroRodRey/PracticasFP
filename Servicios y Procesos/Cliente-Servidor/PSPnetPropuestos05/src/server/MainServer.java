package server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import pojos.Persona;

public class MainServer {

	public static void main(String[] args) throws Exception {
		System.err.println("SERVIDOR");

		int port = 55000;
		InetAddress adress = InetAddress.getByName("localhost");
		DatagramSocket serverSocket = new DatagramSocket(port);
		byte[] buf = new byte[1024];

		DatagramPacket dataReceiver = new DatagramPacket(buf, buf.length);
		serverSocket.receive(dataReceiver);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf));
		Persona persona = (Persona) ois.readObject();
		System.out.println("RECIBE: " + persona.toString());

		persona.setNombre("Javisripto");
		persona.setEdad(20);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		new ObjectOutputStream(bos).writeObject(persona);
		buf = bos.toByteArray();		
		
		DatagramPacket dataSender = new DatagramPacket(buf, buf.length, dataReceiver.getAddress(),
				dataReceiver.getPort());
		System.out.println("ENVIA: " + persona.toString());
		serverSocket.send(dataSender);

		System.err.println("EXIT");
	}

}
