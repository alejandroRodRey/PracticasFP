package hilosPSP;

public class Productor implements Runnable {

	private Buffer buffer;
	private final String letras = "abcdefghijklmnñopqrstuvwxyz";

	public Productor(Buffer b) {
		this.buffer = b;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			char c = letras.charAt((int) (Math.random() * letras.length()));
			buffer.poner(c, i);
			// System.out.println(i + "Productor: " + c);
			try {
				Thread.sleep(400);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}