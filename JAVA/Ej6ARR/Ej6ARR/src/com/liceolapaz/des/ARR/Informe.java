package com.liceolapaz.des.ARR;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 */

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Informe extends JDialog {
	private ResultSet rs;

	public Informe(ResultSet rs, String title) {
		super();
		this.rs = rs;
		setSize(1200, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(title);
		setLayout(new BorderLayout());
		crearTabla();
	}

	private void crearTabla() {
		Modelo modelo = new Modelo(rs);
		JTable tabla = new JTable(modelo);
		tabla.setFillsViewportHeight(true);
		tabla.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(tabla);
		add(scrollPane, BorderLayout.CENTER);
	}
}
