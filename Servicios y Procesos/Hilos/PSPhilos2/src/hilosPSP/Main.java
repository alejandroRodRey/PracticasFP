package hilosPSP;

import java.io.IOException;
import java.util.Scanner;

/*
 * #############################
 * ###          RDZ          ###
 * #############################
 * 
 * Alejandro Rodríguez Rey - 23/10/2019
 */

public class Main {

	private static final int T_ASALTO = 30; // seconds
	private static final int T_GOLPE = 100; // milliseconds
	private static final int MAX_GOLPES = 100;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.err.println("\r\n" +
				" *\r\n" + 
				" * #############################\r\n" + 
				" * ###          RDZ          ###\r\n" + 
				" * #############################\r\n" + 
				" * \r\n" + 
				" * Alejandro Rodríguez Rey - 23/10/2019\r\n" +
				" *" + "\r\n");
		while (true) {
			System.out.print("\u001b[36;1m\r\n"
					+ "###################\r\n"
					+ "# THREAD FIGHTERS #\r\n"
					+ "###################\r\n" + "\u001b[32;1m\r\n" + "1 -> " + MAX_GOLPES + " golpes\r\n" + "2 -> "
					+ T_ASALTO + " segundos\r\n\u001b[0m\r\n" + "Introduce una opción: ");
			boolean tiempo = false;
			boolean o = false;
			Scanner scan = new Scanner(System.in);
			switch (scan.nextLine()) {
			case "1":
				System.out.println();
				tiempo = false;
				o = true;
				break;
			case "2":
				System.out.println();
				tiempo = true;
				o = true;
				break;
			case "0":
				System.err.println("\r\nSaliendo... OK!");
				System.exit(0);
			default:
				System.err.println("\r\nOpción no válida!");
				break;
			}
			if (o){
				Ring ring = new Ring(T_ASALTO, tiempo);
				Boxeador b1 = new Boxeador("John Von Johnson ", ring, tiempo, T_GOLPE, MAX_GOLPES);
				Boxeador b2 = new Boxeador("Petter Gryffindor", ring, tiempo, T_GOLPE, MAX_GOLPES);
		
				Thread t1 = new Thread(b1);
				Thread t2 = new Thread(b2);
				t1.start();
				t2.start();
		
				t1.join();
				t2.join();
		
				System.err.println("\u001b[31;1m\r\nFin del combate");
		
				String s = "Ha ganado ";
				if (b1.getGolpes() > b2.getGolpes()) {
					s += b1.getNombre();
				} else if (b1.getGolpes() < b2.getGolpes()) {
					s += b2.getNombre();
				} else {
					s = "Han quedado en empate";
				}
		
				System.err.println(s + "\r\n\u001b[0m");
				Thread.sleep(1000);
			}
		}
	}

}
