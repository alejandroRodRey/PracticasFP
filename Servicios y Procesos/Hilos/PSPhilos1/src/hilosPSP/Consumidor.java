package hilosPSP;

public class Consumidor implements Runnable {

	private Buffer buffer;

	public Consumidor(Buffer b) {
		this.buffer = b;
	}

	@Override
	public void run() {
		char valor;
		for (int i = 0; i < 10; i++) {
			buffer.recoger(i);
			// valor = buffer.recoger(i);
			// System.out.println(i + "Consumidor: " + valor);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}