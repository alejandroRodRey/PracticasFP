package practica1Eval_ARR;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Normal extends Articulo {

	@Column
	private int posicionVendidos;

	public Normal(int codigo, String nombre, int precio, int posicionVendidos) {
		super(codigo, nombre, precio);
		this.posicionVendidos = posicionVendidos;
	}

	public int getPosicionVendidos() {
		return posicionVendidos;
	}

	public void setPosicionVendidos(int posicionVendidos) {
		this.posicionVendidos = posicionVendidos;
	}

}
