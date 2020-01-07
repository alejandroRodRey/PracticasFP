package com.liceolapaz.des.ARR;

public class CuentaNormal extends CuentaBancaria {
	
	private double saldo;
	
	public CuentaNormal(double saldo) {
		super();
		this.saldo = saldo;
	}

	public void ingresarDinero(double cantidad) {
		if (cantidad>=0) {
			this.saldo = saldo + cantidad;
			System.out.println(String.format("El saldo de la cuenta es %.2f", saldo));
		}
		else {
			System.out.println("LA CANTIDAD DEBE SER POSITIVA!");
		}
		
	}
	
	public void retirarDinero(double cantidad) {
		if (cantidad>=0&&saldo>=cantidad) {
			this.saldo -= cantidad;
			System.out.println(String.format("El saldo de la cuenta es %.2f", saldo));
		}
		else if (cantidad>=0&&saldo<cantidad) {
			System.out.println("NO TIENES SUFICIENTE DINERO!");
		}
		else {
			System.out.println("LA CANTIDAD DEBE SER POSITIVA!");
		}
	}
	
	public double consultarSaldo() {
		return saldo;
	}
}
