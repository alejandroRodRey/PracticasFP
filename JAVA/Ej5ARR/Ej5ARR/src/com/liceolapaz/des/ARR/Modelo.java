package com.liceolapaz.des.ARR;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodr�guez Rey (16/04/2019)
 * 
 * Descripcion:
 * 		Programa de gesti�n de negocio con BD.
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class Modelo extends DefaultTableModel {
	public Modelo(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();

			for (int i = 1; i <= cols; i++) {
				addColumn(rsmd.getColumnName(i));
			}

			while (rs.next()) {
				Object[] fila = new Object[cols];

				for (int i = 1; i <= cols; i++) {
					fila[i - 1] = rs.getObject(i);
				}

				addRow(fila);
			}
		} catch (SQLException e) {

		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
