package com.liceolapaz.des.ARR;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Dialogo extends JDialog {
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private Ventana ventana;

	public Dialogo() {
		super();
		setTitle("Introduzca el usuario y contraseña");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.WHITE);		
		JLabel lbUsuario = new JLabel("Usuario");
		lbUsuario.setForeground(Color.WHITE);
		panel.add(lbUsuario);
		txtUsuario = new JTextField("root");
		txtUsuario.setBackground(Color.LIGHT_GRAY);
		txtUsuario.setBorder(null);
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aceptar();
				}
			}
		});
		panel.add(txtUsuario);
		JLabel lbPassword = new JLabel("Contraseña");
		lbPassword.setForeground(Color.WHITE);
		panel.add(lbPassword);
		txtPassword = new JPasswordField();
		txtPassword.setBackground(Color.LIGHT_GRAY);
		txtPassword.setBorder(null);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aceptar();
				}
			}
		});
		panel.add(txtPassword);
		JButton bAceptar = new JButton("Aceptar");
		bAceptar.setBackground(Color.DARK_GRAY);
		bAceptar.setForeground(Color.WHITE);
		bAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		panel.add(bAceptar);
		JButton bCancelar = new JButton("Cancelar");
		bCancelar.setBackground(Color.DARK_GRAY);
		bCancelar.setForeground(Color.WHITE);
		bCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		panel.add(bCancelar);
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel, BorderLayout.CENTER);
	}

	private void cancelar() {
		if (ventana == null) {
			System.exit(0);
		}
		ventana.setVisible(true);
		setVisible(false);
	}

	private void aceptar() {
		String usuario;
		String password;
		usuario = txtUsuario.getText();
		if (usuario.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		password = new String(txtPassword.getPassword());
		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ventana = new Ventana(this, usuario, password);
		ventana.setVisible(true);
		setVisible(false);
	}
}
