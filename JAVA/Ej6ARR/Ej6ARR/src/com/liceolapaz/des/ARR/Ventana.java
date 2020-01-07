package com.liceolapaz.des.ARR;

import java.awt.Color;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodríguez Rey (02/05/2019)
 * 
 * Descripcion:
 * 		Programa de gestión de BD.
 */

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.mysql.cj.jdbc.Driver;

public class Ventana extends JFrame {
	private Dialogo dialogo;
	private String usuario;
	private String password;
	private JPanel pn;
	private JTextField txtEXP;
	private JTextField txtDNI;
	private JComboBox<String> txtCurso;
	private JTextField txtNombre;
	private JTextField txtApe1;
	private JTextField txtApe2;
	private JTextField txtDir;
	private JTextField txtFecha;
	private JTextField txtFAlta;
	private JTextField txtFMod;
	private JTextField txtFBaja;
	private Timestamp fechaAlta;
	private Timestamp fechaMod;
	private Timestamp fechaBaja;
	private String motivoBaja;
	private JTextField txtMotivoBaja;
	private JTextArea txtObservaciones;
	private String exp;
	private String listaCursos[] = new String[] { "ESO1A", "ESO1B", "ESO1C", "ESO1D", "ESO2A", "ESO2B", "ESO2C",
			"ESO2D", "ESO3A", "ESO3B", "ESO3C", "ESO3D", "ESO4A", "ESO4B", "ESO4C", "ESO4D" };
	private static final String URL_BASE_DATOS = "jdbc:mysql://localhost/Instituto?serverTimezone=Europe/Madrid";

	public Ventana(Dialogo dialogo, String usuario, String password) {
		super();
		this.dialogo = dialogo;
		this.usuario = usuario;
		this.password = password;
		setSize(1024, 720);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Instituto");
		pn = new JPanel();
		pn.setLayout(new GridLayout(14, 2, 5, 10));
		pn.setBackground(Color.DARK_GRAY);
		pn.setForeground(Color.WHITE);
		add(pn);
		crearCampos();
		crearMenu();
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al registrar el driver", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		try {
			Connection conexion = null;
			conexion = DriverManager.getConnection(URL_BASE_DATOS, this.usuario, this.password);
			conexion.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos, el programa puede no funcionar correctamente", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private Connection crearConexion(String url) throws SQLException {
		return DriverManager.getConnection(url, this.usuario, this.password);
	}

	private void cambiarUsuario() {
		this.dialogo.setVisible(true);
		setVisible(false);
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		JMenu menuOpciones = new JMenu("Opciones");
		menuOpciones.setMnemonic(KeyEvent.VK_O);
		menuOpciones.setForeground(Color.WHITE);

		JMenuItem cambiarUsuario = new JMenuItem("Cambiar Usuario");
		cambiarUsuario.setBackground(Color.DARK_GRAY);
		cambiarUsuario.setForeground(Color.WHITE);
		cambiarUsuario.setMnemonic(KeyEvent.VK_U);
		cambiarUsuario.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		cambiarUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarUsuario();
			}
		});
		menuOpciones.add(cambiarUsuario);

		JMenuItem cargarDatos = new JMenuItem("Cargar Datos");
		cargarDatos.setBackground(Color.DARK_GRAY);
		cargarDatos.setForeground(Color.WHITE);
		cargarDatos.setMnemonic(KeyEvent.VK_D);
		cargarDatos.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		cargarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDatos(0);
			}
		});
		menuOpciones.add(cargarDatos);

		JMenuItem limpiarDatos = new JMenuItem("Limpiar Datos");
		limpiarDatos.setBackground(Color.DARK_GRAY);
		limpiarDatos.setForeground(Color.WHITE);
		limpiarDatos.setMnemonic(KeyEvent.VK_L);
		limpiarDatos.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
		limpiarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});
		menuOpciones.add(limpiarDatos);

		menuBar.add(menuOpciones);

		JMenu menuInformes = new JMenu("Informes");
		menuInformes.setMnemonic(KeyEvent.VK_I);
		menuInformes.setForeground(Color.WHITE);

		JMenuItem actuales = new JMenuItem("Actuales");
		actuales.setBackground(Color.DARK_GRAY);
		actuales.setForeground(Color.WHITE);
		actuales.setMnemonic(KeyEvent.VK_T);
		actuales.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		actuales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarActuales();
			}
		});
		menuInformes.add(actuales);

		JMenuItem actBajas = new JMenuItem("Actuales + Bajas");
		actBajas.setBackground(Color.DARK_GRAY);
		actBajas.setForeground(Color.WHITE);
		actBajas.setMnemonic(KeyEvent.VK_B);
		actBajas.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
		actBajas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarActBajas();
			}
		});
		menuInformes.add(actBajas);

		JMenuItem historico = new JMenuItem("Histórico");
		historico.setBackground(Color.DARK_GRAY);
		historico.setForeground(Color.WHITE);
		historico.setMnemonic(KeyEvent.VK_I);
		historico.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
		historico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarHistorico();
			}
		});
		menuInformes.add(historico);

		menuBar.add(menuInformes);

		setJMenuBar(menuBar);
	}

	private void crearCampos() {

		JLabel lbEXP = new JLabel("Número de expediente");
		lbEXP.setForeground(Color.WHITE);
		pn.add(lbEXP);
		txtEXP = new JTextField("");
		txtEXP.setBackground(Color.GRAY);
		txtEXP.setForeground(Color.WHITE);
		txtEXP.setBorder(null);
		txtEXP.setEditable(false);
		pn.add(txtEXP);

		JLabel lbDNI = new JLabel("DNI");
		lbDNI.setForeground(Color.WHITE);
		pn.add(lbDNI);
		txtDNI = new JTextField();
		txtDNI.setBackground(Color.LIGHT_GRAY);
		txtDNI.setBorder(null);
		pn.add(txtDNI);

		JLabel lbCurso = new JLabel("Curso");
		lbCurso.setForeground(Color.WHITE);
		pn.add(lbCurso);
		txtCurso = new JComboBox<String>(listaCursos);
		txtCurso.setBackground(Color.LIGHT_GRAY);
		txtCurso.setBorder(null);
		txtCurso.setSelectedIndex(-1);
		pn.add(txtCurso);

		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setForeground(Color.WHITE);
		pn.add(lbNombre);
		txtNombre = new JTextField();
		txtNombre.setBackground(Color.LIGHT_GRAY);
		txtNombre.setBorder(null);
		pn.add(txtNombre);

		JLabel lbApe1 = new JLabel("Primer Apellido");
		lbApe1.setForeground(Color.WHITE);
		pn.add(lbApe1);
		txtApe1 = new JTextField();
		txtApe1.setBackground(Color.LIGHT_GRAY);
		txtApe1.setBorder(null);
		pn.add(txtApe1);

		JLabel lbApe2 = new JLabel("Segundo Apellido");
		lbApe2.setForeground(Color.WHITE);
		pn.add(lbApe2);
		txtApe2 = new JTextField();
		txtApe2.setBackground(Color.LIGHT_GRAY);
		txtApe2.setBorder(null);
		pn.add(txtApe2);

		JLabel lbDir = new JLabel("Dirección");
		lbDir.setForeground(Color.WHITE);
		pn.add(lbDir);
		txtDir = new JTextField();
		txtDir.setBackground(Color.LIGHT_GRAY);
		txtDir.setBorder(null);
		pn.add(txtDir);

		JLabel lbFecha = new JLabel("Fecha de Nacimiento");
		lbFecha.setForeground(Color.WHITE);
		pn.add(lbFecha);
		txtFecha = new JTextField();
		txtFecha.setBackground(Color.LIGHT_GRAY);
		txtFecha.setBorder(null);
		pn.add(txtFecha);

		JLabel lbFAlta = new JLabel("Fecha de Alta");
		lbFAlta.setForeground(Color.WHITE);
		pn.add(lbFAlta);
		txtFAlta = new JTextField();
		txtFAlta.setBackground(Color.GRAY);
		txtFAlta.setForeground(Color.WHITE);
		txtFAlta.setBorder(null);
		txtFAlta.setEditable(false);
		pn.add(txtFAlta);

		JLabel lbFMod = new JLabel("Fecha de Modificación");
		lbFMod.setForeground(Color.WHITE);
		pn.add(lbFMod);
		txtFMod = new JTextField();
		txtFMod.setBackground(Color.GRAY);
		txtFMod.setForeground(Color.WHITE);
		txtFMod.setBorder(null);
		txtFMod.setEditable(false);
		pn.add(txtFMod);

		JLabel lbFBaja = new JLabel("Fecha de Baja");
		lbFBaja.setForeground(Color.WHITE);
		pn.add(lbFBaja);
		txtFBaja = new JTextField();
		txtFBaja.setBackground(Color.GRAY);
		txtFBaja.setForeground(Color.WHITE);
		txtFBaja.setBorder(null);
		txtFBaja.setEditable(false);
		pn.add(txtFBaja);

		JLabel lbMotivoBaja = new JLabel("Motivo de Baja");
		lbMotivoBaja.setForeground(Color.WHITE);
		pn.add(lbMotivoBaja);
		txtMotivoBaja = new JTextField();
		txtMotivoBaja.setBackground(Color.GRAY);
		txtMotivoBaja.setForeground(Color.WHITE);
		txtMotivoBaja.setBorder(null);
		txtMotivoBaja.setEditable(false);
		pn.add(txtMotivoBaja);

		JLabel lbObservaciones = new JLabel("Observaciones");
		lbObservaciones.setForeground(Color.WHITE);
		pn.add(lbObservaciones);
		txtObservaciones = new JTextArea();
		txtObservaciones.setBackground(Color.LIGHT_GRAY);
		txtObservaciones.setBorder(null);
		JScrollPane scrollObservaciones = new JScrollPane(txtObservaciones);
		scrollObservaciones.setBorder(null);
		pn.add(scrollObservaciones);

		JButton bGrabar = new JButton("Grabar datos");
		bGrabar.setBackground(Color.DARK_GRAY);
		bGrabar.setForeground(Color.WHITE);
		bGrabar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtFBaja.getText().equals("")) {
					if (txtEXP.getText() != null && !txtEXP.getText().equals("")) {
						exp = txtEXP.getText();
						try {
							actualizarDatos();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						return;
					} else {
						grabarDatos();
					}
				} else {
					JOptionPane.showMessageDialog(Ventana.this,
							"No se puede modificar un expediente que ya esté dado de baja", "Error al dar de baja",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pn.add(bGrabar);

		JButton bBaja = new JButton("Dar de baja");
		bBaja.setBackground(Color.DARK_GRAY);
		bBaja.setForeground(Color.WHITE);
		bBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!(txtEXP.getText() == null || txtEXP.getText().equals(""))) {
						realizarBaja();
					} else {
						JOptionPane.showMessageDialog(Ventana.this, "Introduzca un valor para 'Número de expediente'",
								"Error al dar de baja", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pn.add(bBaja);

	}

	private void limpiarDatos() {
		Component[] componentes = pn.getComponents();
		for (int i = 0; i < componentes.length; i++) {
			try {
				JTextField textField = (JTextField) componentes[i];
				textField.setText("");
			} catch (ClassCastException ex) {
			}
		}
		txtCurso.setSelectedIndex(-1);
		txtObservaciones.setText("");
		fechaAlta = null;
		fechaMod = null;
		fechaBaja = null;
		exp = null;
	}

	private void realizarBaja() throws Exception {
		if (txtFBaja.getText() == null || txtFBaja.getText().equals("")) {
			Connection conexion = null;
			try {
				motivoBaja = JOptionPane.showInputDialog(this, "Introduzca Motivo de Baja:", "Motivo de Baja",
						JOptionPane.INFORMATION_MESSAGE);
				if (motivoBaja == null || motivoBaja.equals("")) {
					return;
				}

				try {
					conexion = crearConexion(URL_BASE_DATOS);
					PreparedStatement ps = conexion.prepareStatement("SELECT FecAlta FROM Actual WHERE NumExp = ?");

					ps.setInt(1, Integer.parseInt(exp));
					ResultSet rs = ps.executeQuery();
					if (rs.first()) {
						Timestamp fatemp = rs.getTimestamp(1);
						fechaAlta = fatemp;
					} else {
						JOptionPane.showMessageDialog(this, "Error al realizar baja, fallo al consultar BD", "Error",
								JOptionPane.ERROR_MESSAGE);
						conexion.close();
						return;
					}
					conexion.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error al realizar baja",
							JOptionPane.ERROR_MESSAGE);
					try {
						if (conexion != null) {
							conexion.close();
						}
					} catch (SQLException e1) {
					}
				}

				conexion = crearConexion(URL_BASE_DATOS);
				PreparedStatement ps = conexion.prepareStatement(
						"UPDATE Actual SET FecAlta = ?, FecMod = ?, FecBaja = ?, Motivo = ? WHERE NumExp = ?");

				fechaMod = new Timestamp(new java.util.Date().getTime());
				fechaBaja = fechaMod;

				ps.setTimestamp(1, fechaAlta);
				ps.setTimestamp(2, fechaMod);
				ps.setTimestamp(3, fechaBaja);
				ps.setString(4, motivoBaja);
				ps.setInt(5, Integer.parseInt(exp = txtEXP.getText()));
				int filas = ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "Se han creado los datos", "Info", JOptionPane.INFORMATION_MESSAGE);
				conexion.close();

				////////////////////////////////

				cargarDatos(1);

				////////////////////////////////
				conexion = crearConexion(URL_BASE_DATOS);
				ps = conexion.prepareStatement(
						"INSERT INTO Historico (DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, Curso, FecAlta, FecMod, FecBaja, Motivo, Obs, NumExp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				if (!rellenarPS(conexion, ps, 1, true)) {
					conexion.close();
					return;
				}
				filas = ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "Se han creado los datos en el histórico", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				conexion.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error al dar de baja", JOptionPane.ERROR_MESSAGE);
				try {
					if (conexion != null) {
						conexion.close();
					}
				} catch (SQLException e1) {
				}
			}

		} else {
			JOptionPane.showMessageDialog(this, "El Número de Expediente " + exp + " ya está dado de baja",
					"Error al dar de baja", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refrescarDatos(PreparedStatement ps, Connection conexion, String tempExp) throws Exception {
		try {
			if (tempExp == null || tempExp == "") {
				ps.setInt(1, Integer.parseInt(exp));
			} else {
				ps.setInt(1, Integer.parseInt(tempExp));
			}
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				txtDNI.setText(rs.getString(1));
				txtNombre.setText(rs.getString(2));
				txtApe1.setText(rs.getString(3));
				if (rs.getObject(4) == null) {
					txtApe2.setText("");
				} else {
					txtApe2.setText(rs.getString(4));
				}
				txtDir.setText(rs.getString(5));
				txtFecha.setText("" + rs.getDate(6));
				for (int i = 0; i < listaCursos.length; i++) {
					if (listaCursos[i].equals(rs.getString(12))) {
						txtCurso.setSelectedIndex(i);
					}
				}

				fechaAlta = rs.getTimestamp(7);
				txtFAlta.setText("" + fechaAlta);

				if (rs.getObject(8) != null) {
					txtFMod.setText("" + rs.getTimestamp(8));
				} else {
					txtFMod.setText("");
				}
				if (rs.getObject(9) != null) {
					fechaBaja = rs.getTimestamp(9);
					txtFBaja.setText("" + fechaBaja);
				} else {
					txtFBaja.setText("");
				}
				if (rs.getObject(10) != null) {
					motivoBaja = rs.getString(10);
					txtMotivoBaja.setText("" + motivoBaja);
				} else {
					txtMotivoBaja.setText("");
				}
				if (rs.getObject(11) != null) {
					txtObservaciones.setText("" + rs.getString(11));
				} else {
					txtObservaciones.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(this, "No existe ningún alumno con Número de Expediente " + tempExp,
						"Error", JOptionPane.ERROR_MESSAGE);
				conexion.close();
				tempExp = null;
				return;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No existe ningun alumno con Número de Expediente: " + tempExp, "Error",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			tempExp = null;
			return;
		}
		if (!(tempExp == null || tempExp == "")) {
			exp = tempExp;
		}
		tempExp = null;
		txtEXP.setText("" + exp);
		conexion.close();
	}

	private void cargarDatos(int i) {
		String tempExp = null;
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion.prepareStatement(
					"SELECT DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, FecAlta, FecMod, FecBaja, Motivo, Obs, Curso FROM Actual WHERE NumExp = ?");
			if (i == 1) {
				exp = txtEXP.getText();
			} else if (i == 0) {
				tempExp = JOptionPane.showInputDialog(this, "Introduzca Número de Expediente:");
				if (tempExp == null || tempExp.equals("")) {
					conexion.close();
					return;
				}
			}
			refrescarDatos(ps, conexion, tempExp);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No existe ningun alumno con Número de Expediente " + exp, "Error",
					JOptionPane.ERROR_MESSAGE);
			try {
				limpiarDatos();
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private boolean rellenarPS(Connection conexion, PreparedStatement ps, int i, boolean fmod) throws SQLException {
		if (i == 1) {
			ps.setInt(13, Integer.parseInt(exp));
		}

		if (txtDNI.getText().equals("")) {
			ps.setObject(1, null);
		} else {
			ps.setString(1, txtDNI.getText());
		}

		String nombre = txtNombre.getText();
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Nombre'", "Error",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(2, nombre);

		String ape1 = txtApe1.getText();
		if (ape1.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Primer Apellido'", "Error",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(3, ape1);

		String ape2 = txtApe2.getText();
		if (ape2.equals("")) {
			ps.setObject(4, null);
		} else {
			ps.setString(4, ape2);
		}

		String direccion = txtDir.getText();
		if (direccion.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Dirección'", "Error",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(5, direccion);

		try {
			Date fecha = Date.valueOf(txtFecha.getText());
			if (txtFecha.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Fecha de Nacimiento'", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return false;
			}
			ps.setDate(6, fecha);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor válido para 'Fecha de Nacimiento'", "Error",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}

		try {
			String curso = listaCursos[txtCurso.getSelectedIndex()];
			if (curso.equals("")) {
				JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Curso'", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return false;
			}
			ps.setString(7, curso);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor válido para 'Curso'", "Error",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}

/////////////////////////////
		try {
			if (fechaAlta == null) {
				fechaAlta = new Timestamp(new java.util.Date().getTime());
				ps.setTimestamp(8, fechaAlta);
			} else {
				ps.setTimestamp(8, fechaAlta);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Fecha de Alta", JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
/////////////////////////////

		try {
			if (fmod) {
				if (fechaMod == null) {
					fechaMod = new Timestamp(new java.util.Date().getTime());
					ps.setTimestamp(9, fechaMod);
				} else {
					ps.setTimestamp(9, fechaMod);
					fechaMod = null;
				}
			} else {
				ps.setObject(9, null);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Fecha de Modificación", JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}

/////////////////////////////
		try {
			if (txtFBaja.getText().equals("")) {
				ps.setObject(10, null);
			} else {
				ps.setTimestamp(10, fechaBaja);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Fecha de Baja", JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
/////////////////////////////

		String motivo = txtMotivoBaja.getText();
		if (motivo.equals("")) {
			ps.setObject(11, null);
		} else {
			ps.setString(11, motivo);
		}

		String obs = txtObservaciones.getText();
		if (obs.equals("")) {
			ps.setObject(12, null);
		} else {
			ps.setString(12, obs);
		}

		return true;
	}

	private void grabarDatos() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO Actual (DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, Curso, FecAlta, FecMod, FecBaja, Motivo, Obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			if (!rellenarPS(conexion, ps, 0, false)) {
				conexion.close();
				return;
			}
			int filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Se han creado los datos", "Info", JOptionPane.INFORMATION_MESSAGE);
			conexion.close();

			try {
				conexion = crearConexion(URL_BASE_DATOS);
				ps = conexion.prepareStatement("SELECT NumExp, FecAlta FROM Actual ORDER BY NumExp DESC LIMIT 1");
				ResultSet rs = ps.executeQuery();
				if (rs.first()) {
					exp = "" + rs.getInt(1);
					fechaAlta = rs.getTimestamp(2);
				} else {
					JOptionPane.showMessageDialog(this, "Error al crear nuevo registro, fallo al consultar la BD",
							"Error", JOptionPane.ERROR_MESSAGE);
					conexion.close();
					return;
				}
				conexion.close();

				///////////////////////////////////////////////////
				conexion = crearConexion(URL_BASE_DATOS);
				ps = conexion.prepareStatement(
						"INSERT INTO Historico (DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, Curso, FecAlta, FecMod, FecBaja, Motivo, Obs, NumExp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				if (!rellenarPS(conexion, ps, 1, false)) {
					conexion.close();
					return;
				}
				filas = ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "Se han creado los datos en el histórico", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				conexion.close();
				///////////////////////////////////////////////////

				conexion = crearConexion(URL_BASE_DATOS);
				ps = conexion.prepareStatement(
						"SELECT DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, FecAlta, FecMod, FecBaja, Motivo, Obs, Curso FROM Actual WHERE NumExp = ?");
				if (exp == null || exp.equals("")) {
					conexion.close();
					return;
				}
				refrescarDatos(ps, conexion, null);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error al crear nuevo registro",
						JOptionPane.ERROR_MESSAGE);
				try {
					if (conexion != null) {
						conexion.close();
					}
				} catch (SQLException e1) {
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al crear nuevo registro",
					JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private void actualizarDatos() throws Exception {
		Connection conexion = null;
		try {
			try {
				conexion = crearConexion(URL_BASE_DATOS);
				PreparedStatement ps = conexion.prepareStatement("SELECT FecAlta FROM Actual WHERE NumExp = ?");
				ps.setInt(1, Integer.parseInt(exp));
				ResultSet rs = ps.executeQuery();
				if (rs.first()) {
					fechaAlta = rs.getTimestamp(1);
				} else {
					JOptionPane.showMessageDialog(this, "Error al actualizar datos, fallo al cosultar la BD", "Error",
							JOptionPane.ERROR_MESSAGE);
					conexion.close();
					return;
				}
				conexion.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error al actualizar datos",
						JOptionPane.ERROR_MESSAGE);
				try {
					if (conexion != null) {
						conexion.close();
					}
				} catch (SQLException e1) {
				}
			}

			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE Actual SET DNI = ?, Nombre = ?, Apellido1 = ?, Apellido2 = ?, Direccion = ?, FecNac = ?, Curso = ?, FecAlta = ?, FecMod = ?, FecBaja = ?, Motivo = ?, Obs = ? WHERE NumExp = ?");
			if (!rellenarPS(conexion, ps, 0, true)) {
				conexion.close();
				return;
			}
			try {
				ps.setInt(13, Integer.parseInt(txtEXP.getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al actualizar datos", "Error", JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			int filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Se han creado los datos", "Info", JOptionPane.INFORMATION_MESSAGE);
			conexion.close();

			///////////////////////////////////////////////////
			conexion = crearConexion(URL_BASE_DATOS);
			ps = conexion.prepareStatement(
					"INSERT INTO Historico (DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, Curso, FecAlta, FecMod, FecBaja, Motivo, Obs, NumExp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			if (!rellenarPS(conexion, ps, 1, true)) {
				conexion.close();
				return;
			}
			filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Se han creado los datos en el histórico", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			conexion.close();
			///////////////////////////////////////////////////

			conexion = crearConexion(URL_BASE_DATOS);
			ps = conexion.prepareStatement(
					"SELECT DNI, Nombre, Apellido1, Apellido2, Direccion, FecNac, FecAlta, FecMod, FecBaja, Motivo, Obs, Curso FROM Actual WHERE NumExp = ?");
			if (exp == null || exp.equals("")) {
				conexion.close();
				return;
			}
			refrescarDatos(ps, conexion, null);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al actualizar datos", JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private void cargarActuales() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Actual WHERE FecBaja IS NULL");
			new Informe(rs, "Actuales").setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al mostrar los datos",
					JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private void cargarActBajas() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Actual");
			new Informe(rs, "Actuales + Bajas").setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al mostrar los datos",
					JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private void cargarHistorico() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Historico");
			new Informe(rs, "Histórico").setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al mostrar los datos",
					JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

}
