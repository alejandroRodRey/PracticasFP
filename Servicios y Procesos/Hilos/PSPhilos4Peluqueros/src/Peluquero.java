public class Peluquero implements Runnable {

	private int numero;
	private String nombre;
	private static Silla[] sillas;
	private static int tiempo;
	static int lastSilla = -1;

	public Peluquero(int numero, Silla[] sillas, int tiempo) {
		this.numero = numero;
		this.nombre = "'Peluquero " + this.numero + "'";
		Peluquero.sillas = sillas;
		Peluquero.tiempo = tiempo;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (Silla.getCola() <= 0) {
					try {
						System.out.println(nombre + " se duerme\r\n");
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			int i = 0;
			boolean bool = false;
			synchronized (sillas) {
				do {
					i = (int) ((Math.random() * sillas.length));
				} while (i == lastSilla);
				if (sillas[i].getCliente() != null && !sillas[i].isAtentiendo()) {
					bool = true;
					lastSilla = i;
				}
			}
			if (bool) {
				bool = false;
				sillas[i].setAtentiendo(true);
				cortarPelo(sillas[i].getCliente(), sillas[i]);
			}
		}

	}

	public void cortarPelo(Cliente cliente, Silla silla) {
		System.err.println("El " + this.nombre + " le está cortando el pelo al " + cliente.getNombre()
				+ " en la 'Silla " + silla.getNumero() + "'\r\n");
		try {
			Thread.sleep((long) ((Math.random() * tiempo*10000) / 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Silla.quitarCola();
		silla.liberar();
		silla.setAtentiendo(false);
		System.out.println("El " + cliente.getNombre() + " se marchó satisfecho sin pagar!!!\r\n");
	}

}
