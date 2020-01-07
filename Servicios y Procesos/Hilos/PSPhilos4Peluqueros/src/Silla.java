
public class Silla {

	private int numero;
	private Cliente cliente;
	private boolean atentiendo = false;
	private static int cola = 0;

	public Silla(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isAtentiendo() {
		return atentiendo;
	}

	public void setAtentiendo(boolean atentiendo) {
		this.atentiendo = atentiendo;
	}

	public void liberar() {
		this.cliente = null;
		System.err.println("Está libre la 'Silla " + numero + "'\r\n");
	}

	public int getNumero() {
		return numero;
	}

	public static synchronized int getCola() {
		return cola;
	}

	public static void setCola(int cola) {
		Silla.cola = cola;
	}
	
	public static synchronized void annadirCola() {
		cola++;
		// System.out.println(cola);
	}

	public static synchronized void quitarCola() {
		cola--;
		// System.out.println(cola);
	}

}
