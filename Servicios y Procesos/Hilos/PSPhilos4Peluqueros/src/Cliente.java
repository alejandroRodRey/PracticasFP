
public class Cliente {

	private static int contador = 0;
	private int numero;
	private String nombre;

	public Cliente() {
		contador++;
		this.numero = contador;
		this.nombre = "'Cliente " + numero + "'";
	}

	public String getNombre() {
		return nombre;
	}

	public void marchar() {
		System.out.println(nombre + " dice: En esta peluquería nunca tienen sitio...");
	}

}
