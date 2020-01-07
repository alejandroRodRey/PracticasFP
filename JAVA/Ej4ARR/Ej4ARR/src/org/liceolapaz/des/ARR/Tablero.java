package org.liceolapaz.des.ARR;

import static javax.swing.SwingConstants.CENTER;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodríguez Rey (07/03/2019)
 * 
 * Descripcion:
 * 		Juego de buscar parejas.
 */

public class Tablero extends JPanel {

	Ventana ventana;
	private int filas;
	private int columnas;
	Boton[][] celdas;
	int[] a = new int[2];
	int[] b = new int[2];
	boolean c = false;
	int pN;
	int iN;
	int t;
	JTextField intentosN;
	JTextField parejasN;
	JTextField tiempoN;
	Timer timer;
	JPanel juego;
	JPanel marcador;
	JTextField intentos;
	JTextField parejas;
	JLabel tiempo;

	public Tablero(Ventana ventana, int filas, int columnas) {

		this.ventana = ventana;
		this.filas = filas;
		this.columnas = columnas;

		pN = (filas * columnas) / 2;
		iN = 0;
		t = 0;

		setLayout(new BorderLayout());

		juego = new JPanel();
		juego.setLayout(new GridLayout(filas, columnas));
		juego.setBorder(new LineBorder(Color.RED, 5));

		List<Integer> list = new ArrayList<>();
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i <= filas * columnas / 2; i++) {
				list.add(i);
			}
		}
		celdas = new Boton[filas][columnas];
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				int x = list.size();
				int y = rand(x);
				int pulsado = 0;
				juego.add(celdas[fila][columna] = new Boton(this.ventana, Tablero.this, fila, columna, list.get(y),
						pulsado));
				list.remove(y);
			}
		}

		add(juego);

		marcador = new JPanel();
		marcador.setBackground(Color.DARK_GRAY);
		marcador.setBorder(new MatteBorder(55, 0, 0, 0, Color.DARK_GRAY));
		marcador.setLayout(new GridLayout(1, 6, 21, 0));

		intentos = new JTextField("Intentos");
		intentos.setFont(new Font("Lucida Console", Font.BOLD, 14));
		intentos.setHorizontalAlignment(CENTER);
		intentos.setEditable(false);
		intentos.setBorder(new LineBorder(Color.BLACK, 5));
		intentos.setBackground(Color.LIGHT_GRAY);
		intentos.setForeground(Color.DARK_GRAY);
		marcador.add(intentos);

		intentosN = new JTextField("0");
		intentosN.setFont(new Font("Lucida Console", Font.BOLD, 14));
		intentosN.setHorizontalAlignment(CENTER);
		intentosN.setEditable(false);
		intentosN.setBorder(new LineBorder(Color.YELLOW, 5));
		intentosN.setBackground(Color.LIGHT_GRAY);
		intentosN.setForeground(Color.DARK_GRAY);
		marcador.add(intentosN);

		parejas = new JTextField("Parejas");
		parejas.setFont(new Font("Lucida Console", Font.BOLD, 14));
		parejas.setHorizontalAlignment(CENTER);
		parejas.setEditable(false);
		parejas.setBorder(new LineBorder(Color.BLACK, 5));
		parejas.setBackground(Color.LIGHT_GRAY);
		parejas.setForeground(Color.DARK_GRAY);
		marcador.add(parejas);

		parejasN = new JTextField("" + pN);
		parejasN.setFont(new Font("Lucida Console", Font.BOLD, 14));
		parejasN.setHorizontalAlignment(CENTER);
		parejasN.setEditable(false);
		parejasN.setBorder(new LineBorder(Color.YELLOW, 5));
		parejasN.setBackground(Color.LIGHT_GRAY);
		parejasN.setForeground(Color.DARK_GRAY);
		marcador.add(parejasN);

		tiempo = new JLabel();
		ImageIcon imgt = new ImageIcon(getClass().getResource("/img/time.png"));
		tiempo.setIcon(imgt);
		tiempo.setHorizontalAlignment(CENTER);
		marcador.add(tiempo);

		tiempoN = new JTextField("000");
		tiempoN.setFont(new Font("Lucida Console", Font.BOLD, 14));
		tiempoN.setHorizontalAlignment(CENTER);
		tiempoN.setEditable(false);
		tiempoN.setBorder(new LineBorder(Color.YELLOW, 5));
		tiempoN.setBackground(Color.LIGHT_GRAY);
		tiempoN.setForeground(Color.DARK_GRAY);
		marcador.add(tiempoN);

		marcador.setPreferredSize(new Dimension(0, 120));
		add(marcador, BorderLayout.SOUTH);

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t++;
				if (t < 10) {
					tiempoN.setText("00" + t);
				} else if (t >= 10 && t < 100) {
					tiempoN.setText("0" + t);
				} else {
					tiempoN.setText("" + t);
				}
			}
		});
		timer.start();
	}

	/////////////////////////////////////////////////////// METODOS//////////////////////////////////////////////////////////////////////////////////

	public int rand(int a) {
		Random rand = new Random();
		return rand.nextInt(a);
	}

	public String guardado() {
		String s;
		s = "581042341" + "\n" + filas + "\n" + columnas + "\n" + pN + "\n" + iN + "\n" + t + "\n";
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				s += celdas[fila][columna].fila + "\n";
				s += celdas[fila][columna].columna + "\n";
				s += celdas[fila][columna].valor + "\n";
				s += celdas[fila][columna].pulsado + "\n";
			}
		}
		return s;
	}

	public void cargado(BufferedReader br) {
		try {
			int code = Integer.parseInt(br.readLine(), 10);
			if (code == 581042341) { // Codigo que identifica que el archivo como compatible
				this.filas = Integer.parseInt(br.readLine(), 10);
				this.ventana.filas = this.filas;
				this.columnas = Integer.parseInt(br.readLine(), 10);
				this.ventana.columnas = this.columnas;
				this.pN = Integer.parseInt(br.readLine(), 10);
				this.iN = Integer.parseInt(br.readLine(), 10);
				this.t = Integer.parseInt(br.readLine(), 10);
				this.a = new int[2];
				this.b = new int[2];
				this.c = false;

				remove(juego);
				juego = new JPanel();
				juego.setLayout(new GridLayout(filas, columnas));
				juego.setBorder(new LineBorder(Color.RED, 5));
				celdas = new Boton[filas][columnas];
				for (int fila = 0; fila < filas; fila++) {
					for (int columna = 0; columna < columnas; columna++) {
						int f = Integer.parseInt(br.readLine(), 10);
						int cl = Integer.parseInt(br.readLine(), 10);
						int valor = Integer.parseInt(br.readLine(), 10);
						int pulsado = Integer.parseInt(br.readLine(), 10);
						juego.add(celdas[fila][columna] = new Boton(this.ventana, Tablero.this, f, cl, valor, pulsado));
					}
				}
				add(juego);

				remove(marcador);
				marcador = new JPanel();
				marcador.setBackground(Color.DARK_GRAY);
				marcador.setBorder(new MatteBorder(55, 0, 0, 0, Color.DARK_GRAY));
				marcador.setLayout(new GridLayout(1, 6, 21, 0));
				marcador.add(intentos);
				intentosN.setText("" + iN);
				marcador.add(intentosN);
				marcador.add(parejas);
				parejasN.setText("" + pN);
				marcador.add(parejasN);
				marcador.add(tiempo);
				tiempoN.setText("" + t);
				marcador.add(tiempoN);
				marcador.setPreferredSize(new Dimension(0, 120));
				add(marcador, BorderLayout.SOUTH);
			} else {
				JOptionPane.showMessageDialog(this.ventana, "No se ha podido cargar la partida", "Error",
						JOptionPane.OK_OPTION);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.ventana, "No se ha podido cargar la partida", "Error",
					JOptionPane.OK_OPTION);
		}
	}

	public void saveRes(String nombre) {
		String dif;
		if (this.filas == 3 && this.columnas == 4) {
			dif = "Fácil";
		} else if (this.filas == 4 && this.columnas == 5) {
			dif = "Medio";
		} else if (this.filas == 6 && this.columnas == 6) {
			dif = "Difícil";
		} else {
			dif = "Personalizado";
		}
		try {
			String directorioUsuario = System.getProperty("user.home");
			File f = new File(directorioUsuario + "\\Score_BuscarParejas.txt");
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("~" + nombre + "~ | Tiempo: " + this.t + " | Dificultad: " + dif + "(" + this.filas + "*"
					+ this.columnas + ") | Fecha: " + new Date());
			bw.close();
			JOptionPane.showMessageDialog(this.ventana, "Resultado guardado", "Done", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.ventana, "No se ha podido guardar el archivo", "Error",
					JOptionPane.OK_OPTION);
		}
	}

}
