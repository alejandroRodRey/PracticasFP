package hilosPSP;

public class Buffer {

	private char contenido;
	private boolean disponible = false;

	public synchronized void recoger(int i) {
		while (!disponible) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		disponible = false;
		System.out.println(i + "Consumidor: " + contenido);
		notify();
		// return contenido;
	}

	public synchronized void poner(char c, int i) {
		while (disponible) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		contenido = c;
		System.out.println(i + "Productor: " + c);
		disponible = true;
		notify();
	}
}
