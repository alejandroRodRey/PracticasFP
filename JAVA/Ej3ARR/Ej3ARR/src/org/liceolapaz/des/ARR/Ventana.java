package org.liceolapaz.des.ARR;

import java.awt.BorderLayout;
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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodríguez Rey (13/02/2019)
 * 
 * Descripcion:
 * 		Creador y editor de ficheros de texto plano sencillo.
 */

public class Ventana extends JFrame {

	private File f;
	private String ruta;
	JTextArea areaEscritura;

	/////////////////////////////////////////////////////// METODOS//////////////////////////////////////////////////////////////////////////////////

	public Ventana() {

		setSize(1024, 768);
		setLocationRelativeTo(null);
		setTitle("Documento Nuevo");
		setIconImage(new ImageIcon(getClass().getResource("/img/new.png")).getImage());
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		inicializarMenu();
		areaEscritura = new JTextArea();
		areaEscritura.setLineWrap(true);
		areaEscritura.setWrapStyleWord(true);
		areaEscritura.setFont(new Font("Lucida Console", Font.BOLD, 14));
		areaEscritura.setBackground(Color.DARK_GRAY);
		areaEscritura.setForeground(Color.GREEN);
		JScrollPane scroll = new JScrollPane(areaEscritura);
		scroll.setBorder(new LineBorder(Color.BLACK, 5));
		add(scroll);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				salir();
			}
		});
	}

	private void inicializarMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBorder(new LineBorder(Color.BLACK, 1));
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setForeground(Color.WHITE);
		menuArchivo.setMnemonic(KeyEvent.VK_A);

		JMenuItem nuevo = new JMenuItem();
		nuevo.setBackground(Color.DARK_GRAY);
		nuevo.setForeground(Color.WHITE);
		nuevo.setIcon(new ImageIcon(getClass().getResource("/img/new.png")));
		nuevo.setText("Nuevo");
		nuevo.setMnemonic(KeyEvent.VK_N);
		nuevo.setAccelerator(KeyStroke.getKeyStroke("control N"));
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		menuArchivo.add(nuevo);

		JMenuItem abrir = new JMenuItem();
		abrir.setBackground(Color.DARK_GRAY);
		abrir.setForeground(Color.WHITE);
		abrir.setIcon(new ImageIcon(getClass().getResource("/img/open.png")));
		abrir.setText("Abrir...");
		abrir.setMnemonic(KeyEvent.VK_B);
		abrir.setAccelerator(KeyStroke.getKeyStroke("control B"));
		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		menuArchivo.add(abrir);

		JMenuItem guardar = new JMenuItem();
		guardar.setBackground(Color.DARK_GRAY);
		guardar.setForeground(Color.WHITE);
		guardar.setIcon(new ImageIcon(getClass().getResource("/img/save.png")));
		guardar.setText("Guardar");
		guardar.setMnemonic(KeyEvent.VK_G);
		guardar.setAccelerator(KeyStroke.getKeyStroke("control G"));
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ruta != null) {
					guardar(ruta);
				} else {
					guardarComo();
				}
			}
		});
		menuArchivo.add(guardar);

		JMenuItem guardarComo = new JMenuItem();
		guardarComo.setBackground(Color.DARK_GRAY);
		guardarComo.setForeground(Color.WHITE);
		guardarComo.setIcon(new ImageIcon(getClass().getResource("/img/saveas.png")));
		guardarComo.setText("Guardar como...");
		guardarComo.setMnemonic(KeyEvent.VK_U);
		guardarComo.setAccelerator(KeyStroke.getKeyStroke("control U"));
		guardarComo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		menuArchivo.add(guardarComo);

		JMenuItem salir = new JMenuItem();
		salir.setBackground(Color.DARK_GRAY);
		salir.setForeground(Color.WHITE);
		salir.setIcon(new ImageIcon(getClass().getResource("/img/exit.png")));
		salir.setText("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke("control S"));
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		menuArchivo.add(salir);

		menuBar.add(menuArchivo);
		setJMenuBar(menuBar);
	}

	protected void guardarComo() {
		File tempFile = null;
		String tempRuta = null;
		try {
			JFileChooser selector = new JFileChooser();
			selector.setCurrentDirectory(this.f);
			FileFilter ff = new FileNameExtensionFilter("Archivos *.txt", "txt", "TXT");
			selector.addChoosableFileFilter(ff);
			selector.setFileFilter(ff);
			if (selector.showSaveDialog(Ventana.this) == selector.APPROVE_OPTION) {
				tempFile = selector.getSelectedFile();
				if (tempFile != null) {
					tempRuta = tempFile.getPath();
					if (!(tempRuta.endsWith(".txt"))) {
						tempRuta = (tempFile + ".txt");
					}
					guardar(tempRuta);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Ventana.this, "Formato de archivo incorrecto", "Error",
					JOptionPane.OK_OPTION);
		}
	}

	protected void guardar(String tempRuta) {
		try {
			this.f = new File(tempRuta);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.print(this.areaEscritura.getText());
			bw.close();
			this.ruta = tempRuta;
			setTitle(this.ruta);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Ventana.this, "No se ha podido guardar el archivo", "Error",
					JOptionPane.OK_OPTION);
		}
	}

	protected void abrir() {
		String tempRuta = null;
		File tempFile = null;
		String tituloAlerta = "Abrir Fichero";
		String textoAlerta = "abrir un fichero?";
		if (alerta(tituloAlerta, textoAlerta) == 0) {
			tempRuta = JOptionPane.showInputDialog("Introduzca la ruta completa del fichero:");
			if (tempRuta != null) {
				tempFile = new File(tempRuta);
				if (tempFile.isFile() && tempFile.getName().endsWith(".txt")) {
					cargarFichero(tempRuta);
				} else {
					JOptionPane.showMessageDialog(Ventana.this,
							"La ruta \"" + tempRuta + "\" no es un fichero de texto ", "Error", JOptionPane.OK_OPTION);
				}
			}
		}
	}

	private void cargarFichero(String tempRuta) {
		String leido = new String();
		try {
			this.f = new File(tempRuta);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				leido += linea + "\n";
			}
			br.close();
			this.areaEscritura.setText(leido);
			this.ruta = tempRuta;
			setTitle(this.ruta);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Ventana.this, "No se ha podido leer el archivo", "Error",
					JOptionPane.OK_OPTION);
		}
	}

	protected void nuevo() {
		String tituloAlerta = "Nuevo Documento";
		String textoAlerta = "crear un nuevo documento?";
		if (alerta(tituloAlerta, textoAlerta) == 0) {
			this.ruta = null;
			// this.f = null;
			// SIN COMENTAR: Abre JFileChooser en "Mis Documentos" siempre
			// COMENTADO: Conserva la ruta del ultimo archivo para abrir JFileChooser
			setTitle("Documento Nuevo");
			areaEscritura.setText(null);
		}
	}

	private void salir() {
		String tituloAlerta = "Salir";
		String textoAlerta = "salir?";
		if (alerta(tituloAlerta, textoAlerta) == 0) {
			System.exit(0);
		}
	}

	protected int alerta(String tituloAlerta, String textoAlerta) {
		int r = JOptionPane.showConfirmDialog(Ventana.this,
				"El texto que no se haya guardado" + " se perderá. ¿Quiere " + textoAlerta, tituloAlerta,
				JOptionPane.YES_NO_OPTION);
		return r;
	}

}
