package org.liceolapaz.des.ARR;

import static javax.swing.SwingConstants.CENTER;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

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

public class Ventana extends JFrame {

	Tablero tablero;
	Dialogo optD;
	JCheckBox almresc;
	public int filas = 3;
	public int columnas = 4;

	public Ventana() {

		setSize(1024, 768);
		setLocationRelativeTo(null);
		setTitle("Buscar Parejas - Alejandro Rodríguez Rey");
		setIconImage(new ImageIcon(getClass().getResource("/img/miniLogo.png")).getImage());
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		JPanel inicio = new JPanel();
		inicio.setBorder(new LineBorder(Color.BLACK, 10));
		inicio.setBackground(Color.DARK_GRAY);
		inicio.setLayout(null);

		JTextField titulo = new JTextField("Buscar Parejas");
		titulo.setFont(new Font("Lucida Console", Font.BOLD, 44));
		titulo.setHorizontalAlignment(CENTER);
		titulo.setEditable(false);
		titulo.setBorder(null);
		titulo.setBackground(Color.DARK_GRAY);
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(262, 60, 500, 50);
		inicio.add(titulo);

		JButton bIni = new JButton();
		bIni.setIcon(new ImageIcon(getClass().getResource("/img/logo.png")));
		bIni.setBorder(null);
		bIni.setBackground(Color.DARK_GRAY);
		bIni.setBounds(337, 150, 350, 353);
		bIni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(inicio);
				inicializarMenu();
				setResizable(true);
				tablero = new Tablero(Ventana.this, filas, columnas);
				add(tablero);
				setVisible(true);
			}
		});
		inicio.add(bIni);

		JTextField textLogo = new JTextField("Pulse en la imagen para empezar a jugar");
		textLogo.setFont(new Font("Lucida Console", Font.BOLD, 14));
		textLogo.setHorizontalAlignment(CENTER);
		textLogo.setEditable(false);
		textLogo.setBorder(null);
		textLogo.setBackground(Color.DARK_GRAY);
		textLogo.setForeground(Color.WHITE);
		textLogo.setBounds(262, 500, 500, 50);
		inicio.add(textLogo);

		JTextField subtitulo = new JTextField("Autor: Alejandro Rodríguez Rey");
		subtitulo.setFont(new Font("Lucida Console", Font.BOLD, 24));
		subtitulo.setHorizontalAlignment(CENTER);
		subtitulo.setEditable(false);
		subtitulo.setBorder(null);
		subtitulo.setBackground(Color.DARK_GRAY);
		subtitulo.setForeground(Color.WHITE);
		subtitulo.setBounds(262, 594, 500, 50);
		inicio.add(subtitulo);

		add(inicio);

	}

	/////////////////////////////////////////////////////// METODOS//////////////////////////////////////////////////////////////////////////////////

	private void inicializarMenu() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBorder(new LineBorder(Color.BLACK, 1));

		JMenu partida = new JMenu("Partida");
		partida.setForeground(Color.WHITE);
		partida.setMnemonic(KeyEvent.VK_P);

		JMenuItem nuevo = new JMenuItem();
		nuevo.setIcon(new ImageIcon(getClass().getResource("/img/miniLogo.png")));
		nuevo.setText("Nueva partida");
		nuevo.setMnemonic(KeyEvent.VK_N);
		nuevo.setAccelerator(KeyStroke.getKeyStroke("control N"));
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		partida.add(nuevo);

		JMenuItem guardar = new JMenuItem();
		guardar.setIcon(new ImageIcon(getClass().getResource("/img/save.png")));
		guardar.setText("Guardar partida");
		guardar.setMnemonic(KeyEvent.VK_G);
		guardar.setAccelerator(KeyStroke.getKeyStroke("control G"));
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		partida.add(guardar);

		JMenuItem cargar = new JMenuItem();
		cargar.setIcon(new ImageIcon(getClass().getResource("/img/open.png")));
		cargar.setText("Cargar partida");
		cargar.setMnemonic(KeyEvent.VK_C);
		cargar.setAccelerator(KeyStroke.getKeyStroke("control C"));
		cargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cargar();
			}
		});
		partida.add(cargar);

		JMenuItem salir = new JMenuItem();
		salir.setIcon(new ImageIcon(getClass().getResource("/img/exit.png")));
		salir.setText("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke("control S"));
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		partida.add(salir);

		JMenu opciones = new JMenu("Opciones");
		opciones.setBackground(Color.DARK_GRAY);
		opciones.setForeground(Color.WHITE);
		opciones.setMnemonic(KeyEvent.VK_O);

		almresc = new JCheckBox("Almacenar resultados");
		opciones.add(almresc);

		JMenuItem dificultad = new JMenuItem();
		dificultad.setText("Nivel de dificultad");
		dificultad.setMnemonic(KeyEvent.VK_D);
		dificultad.setAccelerator(KeyStroke.getKeyStroke("control D"));
		dificultad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optD = new Dialogo(Ventana.this);
				optD.setVisible(true);
			}
		});
		opciones.add(dificultad);

		menuBar.add(partida);
		menuBar.add(opciones);
		setJMenuBar(menuBar);
	}

	public void reset() {
		remove(this.tablero);
		tablero = new Tablero(Ventana.this, filas, columnas);
		add(this.tablero);
		setVisible(true);
	}

	public void guardar() {
		File tempFile = null;
		String tempRuta = null;
		try {
			if (tablero.c) {
				JOptionPane.showMessageDialog(Ventana.this, "Termina primero tu jugada", "Error",
						JOptionPane.OK_OPTION);
			} else {
				JFileChooser selector = new JFileChooser();
				selector.setCurrentDirectory(null);
				if (selector.showSaveDialog(Ventana.this) == JFileChooser.APPROVE_OPTION) {
					tempFile = selector.getSelectedFile();
					tempRuta = tempFile.getPath();
					if (!(tempRuta.endsWith(".txt"))) {
						tempRuta = (tempFile + ".txt");
					}
					PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(tempRuta)));
					salida.println(tablero.guardado());
					salida.close();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Ventana.this, "No se ha podido guardar la partida", "Error",
					JOptionPane.OK_OPTION);
		}
	}

	public void cargar() {
		File tempFile = null;
		try {
			JFileChooser selector = new JFileChooser();
			selector.setCurrentDirectory(null);
			if (selector.showOpenDialog(Ventana.this) == JFileChooser.APPROVE_OPTION) {
				tempFile = selector.getSelectedFile();
				BufferedReader entrada = new BufferedReader(new FileReader(tempFile));
				tablero.cargado(entrada);
				entrada.close();
				remove(this.tablero);
				add(this.tablero);
				setVisible(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Ventana.this, "No se ha podido cargar la partida", "Error",
					JOptionPane.OK_OPTION);
		}
	}

	public void exit() {
		String tituloAlerta = "Salir";
		String textoAlerta = "salir?";
		if (alerta(tituloAlerta, textoAlerta) == 0) {
			System.exit(0);
		}
	}

	public int alerta(String tituloAlerta, String textoAlerta) {
		int r = JOptionPane.showConfirmDialog(Ventana.this, "¿Quiere " + textoAlerta, tituloAlerta,
				JOptionPane.YES_NO_OPTION);
		return r;
	}

}
