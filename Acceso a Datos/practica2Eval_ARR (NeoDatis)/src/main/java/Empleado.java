
public class Empleado {

	private int codigoE;
	private String nombreE;
	private String apellidos;
	private String puesto;
	private float salario;
	private int departamento;

	public Empleado() {
		super();
	}

	public Empleado(int codigoE, String nombreE, String apellidos, String puesto, float salario, int departamento) {
		super();
		this.codigoE = codigoE;
		this.nombreE = nombreE;
		this.apellidos = apellidos;
		this.puesto = puesto;
		this.salario = salario;
		this.departamento = departamento;
	}

	public int getCodigoE() {
		return codigoE;
	}

	public void setCodigoE(int codigoE) {
		this.codigoE = codigoE;
	}

	public String getNombreE() {
		return nombreE;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleados [codigoE=" + codigoE + ", nombreE=" + nombreE + ", apellidos=" + apellidos + ", puesto="
				+ puesto + ", salario=" + salario + ", departamento=" + departamento + "]";
	}

}
