package com.liceolapaz.des.ARR;

import java.util.Scanner;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodríguez Rey (14/11/2018)
 * 
 * Descripcion:
 * 		Programa de cajero automatico con opciones de crear dos cuentas, ingresar y retirar dinero.
 */

public class Cajero {
	
	private static double saldo;
	private static CuentaBancaria cuenta = null;
	private static double cantidad;
	
	////////////////////////////////////////////////////// EJECUTABLE ////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		while (true) {
			menu();
			String opt = opt();
			limpiar();
			
			switch (opt) {
				case "1":
					opt = "-1";
					while(!opt.equals("1")&&!opt.equals("2")&&!opt.equals("0")) {
						submenu();
						opt = opt();
						switch (opt) {
							case "1":
								saldo = -1;
								while (saldo < 0) {
									limpiar();
									System.out.println("CREANDO CUENTA NORMAL");
									System.out.println("Escriba el saldo inicial:");
									saldo = saldoIn();
									cuenta = new CuentaNormal(saldo);
								}
								break;
							case "2":
								limpiar();
								System.out.println("CREANDO CUENTA PREMIUM");
								System.out.println("Escriba el saldo inicial:");
								saldo = saldoIn();
								cuenta = new CuentaPremium(saldo);
								break;
							case "0":
								break;
							default:
								limpiar();
								System.out.println("OPCION NO VALIDA!");
								break;
						}
					}
					limpiar();
					break;
				case "2":
					if (cuenta != null) {
						System.out.println("INGRESAR DINERO\r\nEscriba la cantidad:");
						cantidad = saldoIn();
						limpiar();
						cuenta.ingresarDinero(cantidad);
					}
					else {
						System.out.println("PRIMERO DEBES CREAR UNA CUENTA!");
					}
					break;
				case "3":
					
					if (cuenta != null) {
						System.out.println("RETIRAR DINERO\r\nEscriba la cantidad:");
						cantidad = saldoIn();
						limpiar();
						cuenta.retirarDinero(cantidad);
					}
					else {
						System.out.println("PRIMERO DEBES CREAR UNA CUENTA!");
					}
					break;
				case "4": 
					if (cuenta != null) {
						System.out.println("El saldo de la cuenta es " + cuenta.consultarSaldo());
					}
					else {
						System.out.println("PRIMERO DEBES CREAR UNA CUENTA!");
					}
					break;
				case "0":
					System.out.println("EXIT");
					System.exit(0);
				default:
					System.out.println("OPCION NO VALIDA!");
					break;
			}
		}

	}
	
	/////////////////////////////////////////////////////// METODOS //////////////////////////////////////////////////////////////////////////////////

	private static void limpiar() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	private static void menu() {
		System.out.println("\r\n"
				+ "BANCO\r\n" + 
				"1. Crear cuenta\r\n" + 
				"2. Ingresar dinero\r\n" + 
				"3. Retirar dinero\r\n" + 
				"4. Consultar saldo\r\n" + 
				"0. Salir\r\n" + 
				"Escoja una opción:");
		
	}
	
	private static void submenu() {
		System.out.println("\r\n"
				+ "Tipo de cuenta\r\n" + 
				"1. Cuenta normal\r\n" + 
				"2. Cuenta Premium\r\n" + 
				"0. Cancelar\r\n" + 
				"Escoja una opción:");
	}
	
	@SuppressWarnings(value = { "all" })
	private static String opt() {
		Scanner scan = new Scanner(System.in);
		return scan.next();
		
	}
	
	@SuppressWarnings(value = { "all" })
	private static double saldoIn() {
		Scanner scan = new Scanner(System.in);
		return scan.nextDouble();
		
	}
}
