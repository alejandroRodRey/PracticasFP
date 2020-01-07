package org.liceolapaz.des.ARR;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

public class Dialogo extends JDialog {

	Ventana ventana;

	public Dialogo(Ventana ventana) {

		this.ventana = ventana;

		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("Nivel de dificultad");
		setIconImage(new ImageIcon(getClass().getResource("/img/miniLogo.png")).getImage());
		setResizable(false);

		JPanel p2 = new JPanel();
		p2.setBackground(Color.DARK_GRAY);
		p2.setForeground(Color.WHITE);
		p2.setLayout(null);

		JRadioButton rFacil = new JRadioButton("Fácil");
		rFacil.setBackground(Color.DARK_GRAY);
		rFacil.setForeground(Color.WHITE);
		rFacil.setBounds(30, 20, 80, 40);

		JRadioButton rMedio = new JRadioButton("Medio");
		rMedio.setBackground(Color.DARK_GRAY);
		rMedio.setForeground(Color.WHITE);
		rMedio.setBounds(180, 20, 80, 40);

		JRadioButton rDificil = new JRadioButton("Difícil");
		rDificil.setBackground(Color.DARK_GRAY);
		rDificil.setForeground(Color.WHITE);
		rDificil.setBounds(330, 20, 80, 40);

		JRadioButton rPers = new JRadioButton("Personalizado");
		rPers.setBackground(Color.DARK_GRAY);
		rPers.setForeground(Color.WHITE);
		rPers.setBounds(450, 20, 120, 40);

		ButtonGroup rgDif = new ButtonGroup();
		rgDif.add(rFacil);
		rgDif.add(rMedio);
		rgDif.add(rDificil);
		rgDif.add(rPers);

		JLabel lfilas = new JLabel("Filas");
		lfilas.setForeground(Color.WHITE);
		lfilas.setBounds(30, 110, 270, 40);
		p2.add(lfilas);

		JTextField tfilas = new JTextField("" + this.ventana.filas);
		tfilas.setBackground(Color.DARK_GRAY);
		tfilas.setForeground(Color.WHITE);
		tfilas.setBounds(300, 110, 270, 40);
		p2.add(tfilas);

		JLabel lcolumnas = new JLabel("Columnas");
		lcolumnas.setForeground(Color.WHITE);
		lcolumnas.setBounds(30, 160, 270, 40);
		p2.add(lcolumnas);

		JTextField tcolumnas = new JTextField("" + this.ventana.columnas);
		tcolumnas.setBackground(Color.DARK_GRAY);
		tcolumnas.setForeground(Color.WHITE);
		tcolumnas.setBounds(300, 160, 270, 40);
		p2.add(tcolumnas);

		if (this.ventana.filas == 3 && this.ventana.columnas == 4) {
			rFacil.setSelected(true);
			tfilas.setEditable(false);
			tcolumnas.setEditable(false);
		} else if (this.ventana.filas == 4 && this.ventana.columnas == 5) {
			rMedio.setSelected(true);
			tfilas.setEditable(false);
			tcolumnas.setEditable(false);
		} else if (this.ventana.filas == 6 && this.ventana.columnas == 6) {
			rDificil.setSelected(true);
			tfilas.setEditable(false);
			tcolumnas.setEditable(false);
		} else {
			rPers.setSelected(true);
			tfilas.setText("" + this.ventana.filas);
			tcolumnas.setText("" + this.ventana.columnas);
			tfilas.setEditable(true);
			tcolumnas.setEditable(true);
		}

		rFacil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfilas.setText("" + 3);
				tcolumnas.setText("" + 4);
				tfilas.setEditable(false);
				tcolumnas.setEditable(false);
			}
		});
		p2.add(rFacil);

		rMedio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfilas.setText("" + 4);
				tcolumnas.setText("" + 5);
				tfilas.setEditable(false);
				tcolumnas.setEditable(false);
			}
		});
		p2.add(rMedio);

		rDificil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfilas.setText("" + 6);
				tcolumnas.setText("" + 6);
				tfilas.setEditable(false);
				tcolumnas.setEditable(false);
			}
		});
		p2.add(rDificil);

		rPers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfilas.setText("" + Dialogo.this.ventana.filas);
				tcolumnas.setText("" + Dialogo.this.ventana.columnas);
				tfilas.setEditable(true);
				tcolumnas.setEditable(true);
			}
		});
		p2.add(rPers);

		JButton p2ok = new JButton("Aceptar");
		p2ok.setBackground(Color.DARK_GRAY);
		p2ok.setForeground(Color.WHITE);
		p2ok.setBounds(190, 270, 100, 45);
		p2ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.parseInt(tfilas.getText()) * Integer.parseInt(tcolumnas.getText()) % 2 == 0) {
						Dialogo.this.ventana.filas = Integer.parseInt(tfilas.getText());
						Dialogo.this.ventana.columnas = Integer.parseInt(tcolumnas.getText());
						Dialogo.this.ventana.reset();
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(Dialogo.this, "El número de casillas debe ser par", "Error",
								JOptionPane.OK_OPTION);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(Dialogo.this, "El número de casillas debe ser par", "Error",
							JOptionPane.OK_OPTION);
				}
			}
		});
		p2.add(p2ok);

		JButton p2back = new JButton("Cancelar");
		p2back.setBackground(Color.DARK_GRAY);
		p2back.setForeground(Color.WHITE);
		p2back.setBounds(310, 270, 100, 45);
		p2back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		p2.add(p2back);

		add(p2);

	}

}
