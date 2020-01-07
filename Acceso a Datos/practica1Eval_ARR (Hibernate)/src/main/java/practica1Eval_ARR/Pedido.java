package practica1Eval_ARR;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Pedido {

	@Id
	private int numero;

	@CreationTimestamp
	private Date fecha;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	Cliente cliente;

	@ManyToMany
	List<Articulo> articuloNormal = new ArrayList<Articulo>();

	public Pedido(int numero, Cliente cliente, List<Articulo> articuloNormal) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.articuloNormal = articuloNormal;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Articulo> getArticuloNormal() {
		return articuloNormal;
	}

	public void setArticuloNormal(List<Articulo> articuloNormal) {
		this.articuloNormal = articuloNormal;
	}

}
