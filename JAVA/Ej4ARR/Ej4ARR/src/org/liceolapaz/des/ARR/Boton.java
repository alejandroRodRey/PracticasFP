package org.liceolapaz.des.ARR;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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

public class Boton extends JButton {

	private Ventana ventana;
	private Tablero tablero;
	public int fila;
	public int columna;
	public int valor;
	public int pulsado;

	public Boton(Ventana ventana, Tablero tablero, int fila, int columna, int valor, int pulsado) {
		this.ventana = ventana;
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.valor = valor;
		this.pulsado = pulsado;
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		setFont(new Font("Lucida Console", Font.BOLD, 24));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isEnabled()) {
					pulsar();
				}
			}
		});
		if (this.pulsado == 1) {
			setBackground(Color.WHITE);
			setText("" + this.valor);
			setEnabled(false);
		}
	}

	/////////////////////////////////////////////////////// METODOS//////////////////////////////////////////////////////////////////////////////////

	private void pulsar() {
		setBackground(Color.WHITE);
		setText("" + this.valor);
		setEnabled(false);
		this.pulsado = 1;
		comprobar();
	}

	void comprobar() {
		if (tablero.c == false) {
			tablero.a[0] = fila;
			tablero.a[1] = columna;
			tablero.c = true;
		} else {
			tablero.b[0] = fila;
			tablero.b[1] = columna;
			if (tablero.celdas[tablero.a[0]][tablero.a[1]].valor == tablero.celdas[tablero.b[0]][tablero.b[1]].valor) {
				tablero.pN--;
				tablero.parejasN.setText("" + tablero.pN);
				JOptionPane.showMessageDialog(this.tablero, "Los números destapados son iguales", "Bien hecho",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this.tablero, "Los números destapados no son iguales", "Sigue buscando",
						JOptionPane.INFORMATION_MESSAGE);
				tablero.celdas[tablero.a[0]][tablero.a[1]].ocultar();
				tablero.celdas[tablero.b[0]][tablero.b[1]].ocultar();
			}
			tablero.c = false;
			tablero.iN++;
			tablero.intentosN.setText("" + tablero.iN);
			if (tablero.pN == 0) {
				tablero.timer.stop();
				JOptionPane.showMessageDialog(this.tablero, "¡¡¡Has ganado en " + tablero.iN + " intento(s)!!!",
						"Enhorabuena", JOptionPane.INFORMATION_MESSAGE);

				if (this.ventana.almresc.isSelected()) {
					String s = JOptionPane.showInputDialog(this.tablero, "Introduzca su nombre:");
					if (s != null) {
						tablero.saveRes(s);
					}
				}

				if (0 == JOptionPane.showConfirmDialog(this.tablero, "¿Quieres jugar otra partida?", "Fin de partida",
						JOptionPane.YES_NO_OPTION)) {
					this.ventana.reset();
				} else {
					System.exit(0);
				}
			}
		}
	}

	private void ocultar() {
		setBackground(Color.BLACK);
		setIcon(null);
		setText("");
		setEnabled(true);
		this.pulsado = 0;
	}

}
