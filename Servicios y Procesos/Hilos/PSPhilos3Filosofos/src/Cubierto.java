public class Cubierto {
	
	char name = ' ';
	boolean libre = true;

	public Cubierto(char name) {
		this.name = name;
	}
	
	public char getName() {
		return name;
	}
	
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	
	public boolean getLibre() {
		return libre;
	}

}
