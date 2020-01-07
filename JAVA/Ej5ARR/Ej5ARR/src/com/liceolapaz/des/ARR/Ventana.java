package com.liceolapaz.des.ARR;

import java.awt.BorderLayout;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodríguez Rey (16/04/2019)
 * 
 * Descripcion:
 * 		Programa de gestión de negocio con BD.
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Driver;

public class Ventana extends JFrame {
	private Dialogo dialogo;
	private String usuario;
	private String password;
	private JPanel panelCampos;
	private JTextField txtDNI;
	private JTextField txtApe1;
	private JTextField txtApe2;
	private JTextField txtFecha;
	private JTextField txtNombre;
	private String dni;
	private String tempDNI;
	private static final String URL_BASE_DATOS = "jdbc:mysql://localhost/Acme?serverTimezone=Europe/Madrid";

	public Ventana(Dialogo dialogo, String usuario, String password) {
		super();
		this.dialogo = dialogo;
		this.usuario = usuario;
		this.password = password;
		setSize(600, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clientes");
		setLayout(new BorderLayout());
		crearCampos();
		crearBotones();
		crearMenu();
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al registrar el driver", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private Connection crearConexion(String url) throws SQLException {
		return DriverManager.getConnection(url, this.usuario, this.password);
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuOpciones = new JMenu("Opciones");
		menuOpciones.setMnemonic(KeyEvent.VK_O);

		JMenuItem cambiarUsuario = new JMenuItem("Cambiar Usuario");
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
		cargarDatos.setMnemonic(KeyEvent.VK_D);
		cargarDatos.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		cargarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDatos();
			}
		});
		menuOpciones.add(cargarDatos);

		JMenuItem limpiarDatos = new JMenuItem("Limpiar Datos");
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

		JMenuItem facturas = new JMenuItem("Facturas");
		facturas.setMnemonic(KeyEvent.VK_T);
		facturas.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		facturas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarFacturas();
			}
		});
		menuInformes.add(facturas);

		JMenuItem clientes = new JMenuItem("Clientes");
		clientes.setMnemonic(KeyEvent.VK_I);
		clientes.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
		clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarClientes();
			}
		});
		menuInformes.add(clientes);

		menuBar.add(menuInformes);

		setJMenuBar(menuBar);
	}

	private void cargarClientes() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Clientes");
			new Informe(rs).setVisible(true);
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

	private void cargarFacturas() {
		if (!txtDNI.getText().equals("")) {
			mostrarFacturasCliente();
		} else {
			mostrarFacturas();
		}
	}

	private void limpiarDatos() {
		Component[] componentes = panelCampos.getComponents();
		for (int i = 0; i < componentes.length; i++) {
			try {
				JTextField textField = (JTextField) componentes[i];
				textField.setText("");
			} catch (ClassCastException ex) {

			}
		}
	}

	private void crearCampos() {
		panelCampos = new JPanel();
		panelCampos.setLayout(new GridLayout(5, 2, 20, 10));
		panelCampos.setBorder(new EmptyBorder(30, 30, 30, 30));

		JLabel lbDNI = new JLabel("DNI");
		panelCampos.add(lbDNI);
		txtDNI = new JTextField();
		txtDNI.setEditable(true);
		panelCampos.add(txtDNI);

		JLabel lbNombre = new JLabel("Nombre");
		panelCampos.add(lbNombre);
		txtNombre = new JTextField();
		panelCampos.add(txtNombre);

		JLabel lbApe1 = new JLabel("Primer Apellido");
		panelCampos.add(lbApe1);
		txtApe1 = new JTextField();
		panelCampos.add(txtApe1);

		JLabel lbApe2 = new JLabel("Segundo Apellido");
		panelCampos.add(lbApe2);
		txtApe2 = new JTextField();
		panelCampos.add(txtApe2);

		JLabel lbFecha = new JLabel("Fecha Nacimiento");
		panelCampos.add(lbFecha);
		txtFecha = new JTextField();
		panelCampos.add(txtFecha);

		add(panelCampos);
	}

	private void crearBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1, 3, 30, 0));
		panelBotones.setBorder(new EmptyBorder(30, 30, 30, 30));

		JButton bCrear = new JButton("Crear Cliente");
		bCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crearCliente();
			}
		});
		panelBotones.add(bCrear);

		JButton bActualizar = new JButton("Actualizar Cliente");
		bActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarCliente();
			}
		});
		panelBotones.add(bActualizar);

		JButton bEliminar = new JButton("Eliminar Cliente");
		bEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarCliente();
			}
		});
		panelBotones.add(bEliminar);

		add(panelBotones, BorderLayout.SOUTH);
	}

	private void cargarDatos() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion
					.prepareStatement("SELECT Nombre, Ape1, Ape2, " + "Fec_Nac FROM Clientes WHERE DNI = ?");
			dni = JOptionPane.showInputDialog(this, "Introduzca Código");
			if (dni == null || dni.equals("")) {
				conexion.close();
				return;
			}
			try {
				ps.setString(1, dni);
				ResultSet rs = ps.executeQuery();
				if (rs.first()) {
					txtDNI.setText("" + dni);
					txtNombre.setText(rs.getString(1));
					txtApe1.setText(rs.getString(2));
					if (rs.getObject(3) == null) {
						txtApe2.setText("");
					} else {
						txtApe2.setText(rs.getString(3));
					}
					txtFecha.setText("" + rs.getDate(4));
				} else {
					JOptionPane.showMessageDialog(this, "No existe ningún cliente con DNI " + dni, "Error",
							JOptionPane.ERROR_MESSAGE);
					conexion.close();
					return;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al cargar datos", "Error", JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al cargar datos", JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private void cambiarUsuario() {
		this.dialogo.setVisible(true);
		setVisible(false);
	}

	private boolean rellenarPS(Connection conexion, PreparedStatement ps) throws SQLException {
		String nombre = txtNombre.getText();
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Nombre'", "Campo obligatorio",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(1, nombre);

		String ape1 = txtApe1.getText();
		if (ape1.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Primer Apellido'", "Campo obligatorio",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(2, ape1);

		String ape2 = txtApe2.getText();
		if (ape2.equals("")) {
			ps.setObject(3, null);
		} else {
			ps.setString(3, ape2);
		}

		try {
			Date fecha = Date.valueOf(txtFecha.getText());
			if (txtFecha.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Fecha'", "Campo obligatorio",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return false;
			}
			ps.setDate(4, fecha);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor válido para 'Fecha'", "Campo obligatorio",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}

		return true;
	}

	private boolean rellenarCrearPS(Connection conexion, PreparedStatement ps) throws SQLException {
		tempDNI = txtDNI.getText();
		if (tempDNI.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'DNI'", "Campo obligatorio",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(1, tempDNI);

		String nombre = txtNombre.getText();
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Nombre'", "Campo obligatorio",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}
		ps.setString(2, nombre);

		String ape1 = txtApe1.getText();
		if (ape1.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Primer Apellido'", "Campo obligatorio",
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

		try {
			Date fecha = Date.valueOf(txtFecha.getText());
			if (txtFecha.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduzca un valor para 'Fecha'", "Campo obligatorio",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return false;
			}
			ps.setDate(5, fecha);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Introduzca un valor válido para 'Fecha'", "Campo obligatorio",
					JOptionPane.ERROR_MESSAGE);
			conexion.close();
			return false;
		}

		return true;
	}

	protected void eliminarCliente() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM Clientes WHERE DNI = ?");
			if (!txtDNI.getText().equals(dni)) {
				JOptionPane.showMessageDialog(this,
						"Error al borrar registro, el DNI no se corresponde con el cliente cargado", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			try {
				ps.setString(1, txtDNI.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al borrar registro, falló el DNI", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			int filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Se ha(n) actualizado " + filas + " fila(s) base de datos", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			conexion.close();
			limpiarDatos();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al borrar registro", JOptionPane.ERROR_MESSAGE);
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	private void crearCliente() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion
					.prepareStatement("INSERT INTO Clientes (DNI, Nombre, Ape1, Ape2, Fec_Nac) VALUES (?, ?, ?, ?, ?)");
			if (!rellenarCrearPS(conexion, ps)) {
				conexion.close();
				return;
			}
			int filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Se ha(n) actualizado " + filas + " fila(s) base de datos", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			conexion.close();
			dni = tempDNI;
		} catch (SQLException e) {
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

	private void actualizarCliente() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE Clientes SET Nombre = ?, Ape1 = ?," + " Ape2 = ?, Fec_Nac = ? WHERE DNI = ?");
			if (!rellenarPS(conexion, ps)) {
				conexion.close();
				return;
			}
			if (!txtDNI.getText().equals(dni)) {
				JOptionPane.showMessageDialog(this,
						"Error al actualizar datos, el DNI no se corresponde con el cliente cargado", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			try {
				ps.setString(5, txtDNI.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al actualizar datos, falló el DNI", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			int filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(this, "Se ha(n) actualizado " + filas + " fila(s) base de datos", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			conexion.close();
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

	private void mostrarFacturasCliente() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Facturas WHERE Cliente = ?");
			tempDNI = txtDNI.getText();
			if (!tempDNI.equals(dni)) {
				JOptionPane.showMessageDialog(this,
						"Error al cargar facturas, el DNI no se corresponde con el cliente cargado", "Error",
						JOptionPane.ERROR_MESSAGE);
				conexion.close();
				return;
			}
			ps.setString(1, tempDNI);
			ResultSet rs = ps.executeQuery();
			new Informe(rs).setVisible(true);
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

	private void mostrarFacturas() {
		Connection conexion = null;
		try {
			conexion = crearConexion(URL_BASE_DATOS);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Facturas");
			new Informe(rs).setVisible(true);
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
