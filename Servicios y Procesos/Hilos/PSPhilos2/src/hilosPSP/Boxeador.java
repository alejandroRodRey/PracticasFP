package hilosPSP;

public class Boxeador implements Runnable {

	private String nombre;
	private Ring ring;
	private int golpes = 0;
	private boolean tiempo;
	private int tGolpe;
	private int maxGolpes;

	public Boxeador(String nombre, Ring ring, boolean tiempo, int tGolpe, int maxGolpes) {
		this.nombre = nombre;
		this.ring = ring;
		this.tiempo = tiempo;
		this.tGolpe = tGolpe;
		this.maxGolpes = maxGolpes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ring getRing() {
		return ring;
	}

	public void setRing(Ring ring) {
		this.ring = ring;
	}

	public int getGolpes() {
		return golpes;
	}

	public void setGolpes(int golpes) {
		this.golpes = golpes;
	}

	@Override
	public void run() {
		try {
			Thread.sleep((long) (((Math.random() * 1000 * 10) / 10)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean asalto = true;
		while (ring.getGolpes() < maxGolpes && asalto) {
			synchronized (this) {
				golpes++;
				ring.setGolpes(1);
				System.out.println("Pegada de " + getNombre() + " (" + getGolpes() + ")");
			}
			if (tiempo) {
				if (ring.getTime() <= 0) {
					asalto = false;
					ring.stopTimer();
				}
			}
			try {
				Thread.sleep((long) ((Math.random() * tGolpe * 10 / 10)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (tiempo) {
			ring.stopTimer();
		}
	}

}
