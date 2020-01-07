import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * #############################
 * ###          RDZ          ###
 * #############################
 * 
 * Alejandro Rodríguez Rey - 22/10/2019
 * 
 * ### PELUQUERÍA DE LOS HORRORES ###\
 */

public class Main {

	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";

	private static final Peluquero[] PELUQUEROS = new Peluquero[3];
	private static final Silla[] SILLAS = new Silla[6];
	private static final int TP = 6; // seconds
	private static final int TC = 3; // seconds

	public static void main(String[] args) {

		System.err.println("\r\n" +
		"/*\r\n" + 
		" * #############################\r\n" + 
		" * ###          RDZ          ###\r\n" + 
		" * #############################\r\n" + 
		" * \r\n" + 
		" * Alejandro Rodríguez Rey - 22/10/2019\r\n *\r\n"
		+ " * ### PELUQUERÍA DE LOS HORRORES ###\r\n" + 
		" */" + "\r\n");

		int i = 0;
		while (i < SILLAS.length) {
			SILLAS[i] = new Silla(i + 1);
			i++;
		}

		ExecutorService ex = Executors.newCachedThreadPool();

		i = 0;
		while (i < PELUQUEROS.length) {
			PELUQUEROS[i] = new Peluquero(i + 1, SILLAS, TP);
			ex.execute(PELUQUEROS[i]);
			i++;
		}

		while (true) {
			Cliente cliente = new Cliente();
			try {
				Thread.sleep((long) ((Math.random() * TC*10000) / 10));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean dePie = true;
			for (Silla silla : SILLAS) {
				int a1 = silla.getNumero() - 1;
				int a2 = Peluquero.lastSilla;
				if (silla.getCliente() == null && dePie && a1 != a2) {
					silla.setCliente(cliente);
					dePie = false;
					Silla.annadirCola();
					System.out.println(cliente.getNombre() + " se sento en la 'Silla " + silla.getNumero() + "'\r\n");

					i = 0;
					while (i < PELUQUEROS.length) {
						synchronized (PELUQUEROS[i]) {
							PELUQUEROS[i].notifyAll();
						}
						i++;
					}
				}
			}

			if (dePie) {
				cliente.marchar();
				cliente = null;
			}

		}

	}

}
