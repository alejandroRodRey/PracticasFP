package client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import pojos.Persona;

public class MainClient {

	public static void main(String[] args) throws Exception {
		System.err.println("CLIENTE");

		Persona persona = new Persona("Rodro", 24);
		DatagramSocket socket = new DatagramSocket();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		new ObjectOutputStream(bos).writeObject(persona);
		byte[] buf = bos.toByteArray();

		DatagramPacket dataSender = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 55000);
		System.out.println("ENVIA: " + persona.toString());
		socket.send(dataSender);

		buf = new byte[1024];
		DatagramPacket dataReceiver = new DatagramPacket(buf, buf.length);
		socket.receive(dataReceiver);

		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf));
		persona = (Persona) ois.readObject();
		System.out.println("RECIVE: " + persona.toString());

		System.err.println("EXIT");
	}

}
