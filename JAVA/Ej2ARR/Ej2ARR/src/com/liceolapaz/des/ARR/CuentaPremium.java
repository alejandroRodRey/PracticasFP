package com.liceolapaz.des.ARR;

public class CuentaPremium extends CuentaBancaria{
	
	private double saldo;
	
	public CuentaPremium(double saldo) {
		super();
		this.saldo = saldo;
	}
	
	public void ingresarDinero(double cantidad) {
		if (cantidad>=0) {
			saldo = saldo + cantidad;
			System.out.println(String.format("El saldo de la cuenta es %.2f", saldo));
		}
		else {
			System.out.println("LA CANTIDAD DEBE SER POSITIVA!");
		}
		
	}
	
	public void retirarDinero(double cantidad) {
		if (cantidad>=0) {
			saldo = saldo - cantidad;
			System.out.println(String.format("El saldo de la cuenta es %.2f", saldo));
		}
		else {
			System.out.println("LA CANTIDAD DEBE SER POSITIVA!");
		}
	}
	
	public double consultarSaldo() {
		return saldo;
	}
}