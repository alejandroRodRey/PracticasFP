import java.util.Map;
import java.util.Random;

public class Filosofo implements Runnable {

	Map<Character, Cubierto> cubiertos;
	Cubierto cubiertoD;
	Cubierto cubiertoI;
	boolean comiendo = false;

	public Filosofo(Map<Character, Cubierto> cubiertos) {
		this.cubiertos = cubiertos;

	}

	@Override
	public void run() {
		String miNombre = Thread.currentThread().getName();
		switch (miNombre) {
		case "Filosofo 1":
			cubiertoI = cubiertos.get('A');
			cubiertoD = cubiertos.get('B');
			// AB
			break;
		case "Filosofo 2":
			cubiertoI = cubiertos.get('B');
			cubiertoD = cubiertos.get('C');
			// BC
			break;
		case "Filosofo 3":
			cubiertoI = cubiertos.get('C');
			cubiertoD = cubiertos.get('D');
			// CD
			break;
		case "Filosofo 4":
			cubiertoI = cubiertos.get('D');
			cubiertoD = cubiertos.get('E');
			// DE
			break;
		case "Filosofo 5":
			cubiertoI = cubiertos.get('E');
			cubiertoD = cubiertos.get('A');
			// EA
			break;
		}
		Random generator = new Random();
		while (true) {
			synchronized (Filosofo.class) {
				if (cubiertoD.getLibre() && cubiertoI.getLibre()) {
					cubiertoD.setLibre(false);
					cubiertoI.setLibre(false);
					comiendo = true;
					System.out.println(miNombre + " comiendo...");
				}
			}

			int milisegs = (1 + generator.nextInt(5)) * 1000;
			if (milisegs < 0) {
				milisegs *= -1;
			}
			if (comiendo) {
				esperarTiempoAzar(miNombre, milisegs);
			}
			if (comiendo) {
				synchronized (Filosofo.class) {
					cubiertoD.setLibre(true);
					cubiertoI.setLibre(true);
					comiendo = false;
					System.out.println("Se ha puesto a pensar" + miNombre);
				}
				esperarTiempoAzar(miNombre, milisegs);
			}
		}
	}

	private void esperarTiempoAzar(String miNombre, int milisegs) {
		try {
			Thread.sleep(milisegs);
		} catch (InterruptedException e) {
			System.err.println(miNombre + " interrumpido!!. Saliendo...");
		}
	}

}
