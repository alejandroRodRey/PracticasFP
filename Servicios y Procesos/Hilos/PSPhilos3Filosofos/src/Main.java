import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		Map<Character, Cubierto> cubiertos = new HashMap<Character, Cubierto>();
		cubiertos.put('A', new Cubierto('A'));
		cubiertos.put('B', new Cubierto('B'));
		cubiertos.put('C', new Cubierto('C'));
		cubiertos.put('D', new Cubierto('D'));
		cubiertos.put('E', new Cubierto('E'));

		new Thread(new Filosofo(cubiertos), "Filosofo 1").start();
		new Thread(new Filosofo(cubiertos), "Filosofo 2").start();
		new Thread(new Filosofo(cubiertos), "Filosofo 3").start();
		new Thread(new Filosofo(cubiertos), "Filosofo 4").start();
		new Thread(new Filosofo(cubiertos), "Filosofo 5").start();

	}

}
