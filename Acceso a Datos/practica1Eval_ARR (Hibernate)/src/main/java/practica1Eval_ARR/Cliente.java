package practica1Eval_ARR;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class Cliente {

	@Id
	private int identificador;

	@Column
	private Date fechaAlta;

	@ColumnTransformer(write = " MD5(?) ")
	private String password;

	public Cliente(int identificador, Date fechaAlta, String password) {
		super();
		this.identificador = identificador;
		this.fechaAlta = fechaAlta;
		this.password = password;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
