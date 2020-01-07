package practica1Eval_ARR;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Preventa extends Articulo {

	@Column
	private Date fechaSalida;

	public Preventa(int codigo, String nombre, int precio, Date fechaSalida) {
		super(codigo, nombre, precio);
		this.fechaSalida = fechaSalida;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

}
