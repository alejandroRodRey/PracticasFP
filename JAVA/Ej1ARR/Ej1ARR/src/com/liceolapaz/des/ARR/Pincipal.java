package com.liceolapaz.des.ARR;

import java.util.Random;
import java.util.Scanner;

/* 
 * #############
 * ### ~RDZ~ ###
 * #############
 * 
 * Autor:
 * 		Alejandro Rodríguez Rey (8/11/2018)
 * 
 * Descripcion:
 * 		Programa que simula una partida de Blackjack, con dos modos:
 * 			1.Facil (cartas descubiertas)
 * 			2.Normal (cartas ocualtas)
 */

public class Pincipal {
	
	////////////////////////////////////////////////////// EJECUTABLE ////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {															//metodo ejecutable
		
		int totalCartasJ;																				//declaracion de variables
		int puntosJ;
		int totalCartasB;
		int puntosB;
		int carta;
		String option2;
		
		while (true) {																					//bucle principal
			
			totalCartasJ = 0;																			//asignacion de valor inicial a las variables
			puntosJ = 0;
			totalCartasB = 0;
			puntosB = 0;
			carta = 0;
			option2 = "0";
			System.out.println("			\r\n\r\nBLACKJACK\r\n" + 									//menu principal
											"1. Modo fácil\r\n" + 
											"2. Modo normal\r\n" + 
											"0. Salir\r\n" + 
											"Escoja una opción:");
						
			String option = opt();																		//escoger opcion
			limpiar();																					//limpiar
			
			switch(option) {																			//comparar opcion introducida
				default: limpiar();																		//por defecto, opcion no valida, regresar al menu
					System.out.println("OPCIÓN NO VÁLIDA!");											
					break;
				case "0": System.out.println("\r\nPROGRAMA TERMINADO");									//terminar programa
					System.exit(0);
				
				case "1":																				//modo facil
					
					
					do {																				//bucle generar cartas del banco hasta sumar 15
						carta = genNum(1,11);
						totalCartasB++;
						puntosB += carta;
						
						
						
						//puntosB = _tester();															//METODO PARA TESTEAR
						
						
						
					} while (puntosB<=15);
					
					
					while (!option2.equals("2")&&puntosJ<21) {											//bucle juego facil
						
						option2 = "0";																	//restablecer a 0
						carta = genNum(1,11);															//generar carta jugador
						puntosJ = carta + puntosJ;														//sumar valor cartas
						totalCartasJ++;																	//sumar +1 al numero de cartas cada vez que se repida el bucle
						
						
						
						//puntosJ = _tester();															//METODO PARA TESTEAR
						
						
						
						System.out.println("\r\nBANCA   | Total Cartas: " + totalCartasB + " Total Puntuación: " + puntosB);
						System.out.println("JUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
						
						if (puntosB>21) {
							System.out.println("\r\nLa banca se ha pasado de 21. PIERDE LA BANCA!");	//si la banca se pasa de 21, pierde
							break;
						}
						
						while (!option2.equals("1")&&!option2.equals("2")&&puntosJ<21) {				//bucle submenu
							System.out.println("\r\n¿Seguir jugando?\r\n" + 
												"1. Sí\r\n" + 
												"2. No\r\n" + 
												"Escoja una opción:");
							option2 = opt();
							if (option2.equals("1")) {													//sacar otra carta
								limpiar();
								System.out.println("SACANDO CARTA...");
							}
							else if (option2.equals("2")) {												//plantarse
								break;
							}
							else {																		//por defecto, opcion no valida, repite bucle
								limpiar();
								System.out.println("OPCIÓN NO VÁLIDA!");
								System.out.println("\r\nBANCA   | Total Cartas: " + totalCartasB + " Total Puntuación: " + puntosB);
								System.out.println("JUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
							}
						}
					}
					if (puntosB<=21) {																	//juego terminado, comparar resultados
						limpiar();
						System.out.println("TE PLANTAS!");
						System.out.println("\r\nBANCA   | Total Cartas: " + totalCartasB + " Total Puntuación: " + puntosB);
						System.out.println("JUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
						if (puntosJ>21) {																//si te pasas de 21 puntos, pierdes
							System.out.println("\r\nTe has pasado de 21. HAS PERDIDO!");
						}
						
						else if (puntosJ==21&&puntosB<21) {												//si tienes 21 puntos y la banca menos, ganas
							System.out.println("\r\n21 Blackjack. PIERDE LA BANCA!");
						}
						
						
						else {																			
							if (puntosJ>puntosB) {														//si el jugador tiene mas puntos que la banca, ganas
								System.out.println("\r\nHas ganado. PIERDE LA BANCA!");
							}
							else if (puntosJ==puntosB) {												
								System.out.print("\r\nEmpate. ");
								if (totalCartasJ<totalCartasB) {										//mismos puntos, si jugador tiene menos cartas que la banca, ganas
									System.out.print("Tienes menos cartas. PIERDE LA BANCA!");
								}
								else if (totalCartasJ==totalCartasB) {									//mismos puntos, si jugador tiene las mismas cartas que la banca, ganas
									System.out.print("Teneis la misma cantidad de cartas. PIERDE LA BANCA!");
								}
								else {																	//mismos puntos, si jugador tiene mas cartas que la banca, pierdes
									System.out.print("La banca tiene menos cartas. HAS PERDIDO!");
								}
							}
							else {																		//si el jugador tiene menos puntos que la banca, pierdes
								System.out.println("\r\nGana la banca. HAS PERDIDO!");
							}
						}
					}
					break;																				//fin modo facil
			
				case "2":																				//modo normal
					
					
					do {																				//bucle generar cartas banca hasta sumar 15
						carta = genNum(1,11);
						totalCartasB++;
						puntosB += carta;
						
						
						
						//puntosB = _tester();															//METODO PARA TESTEAR
						
						
						
					} while (puntosB<=15);
					
					
					while (!option2.equals("2")&&puntosJ<21) {											//bucle modo normal
						
						option2 = "0";
						carta = genNum(1,11);
						puntosJ = carta + puntosJ;
						totalCartasJ++;
						
						
						
						//puntosJ = _tester();															//METODO PARA TESTEAR
						
						
						
						System.out.println("\r\nJUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
						
						while (!option2.equals("1")&&!option2.equals("2")&&puntosJ<21) {				//bucle submenu
							System.out.println("\r\n¿Seguir jugando?\r\n" + 
												"1. Sí\r\n" + 
												"2. No\r\n" + 
												"Escoja una opción:");
							option2 = opt();
							if (option2.equals("1")) {
								limpiar();
								System.out.println("SACANDO CARTA...");
							}
							else if (option2.equals("2")) {
								break;
							}
							else {
								limpiar();
								System.out.println("OPCIÓN NO VÁLIDA!");
								System.out.println("\r\nJUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
							}
						}
					}
					if (puntosB<=21) {																	//juego terminado, comparar resultados
						limpiar();
						System.out.println("TE PLANTAS!");
						System.out.println("\r\nBANCA   | Total Cartas: " + totalCartasB + " Total Puntuación: " + puntosB);
						System.out.println("JUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
						if (puntosJ>21) {
							System.out.println("\r\nTe has pasado de 21. HAS PERDIDO!");
						}
						else if (puntosJ==21&&totalCartasJ<totalCartasB) {
							System.out.println("\r\n21 Blackjack. PIERDE LA BANCA!");
						}
						else /*(puntosJ<21||totalCartasJ>totalCartasB||totalCartasJ==totalCartasB)*/{
							if (puntosJ>puntosB) {
								System.out.println("\r\nHas ganado. PIERDE LA BANCA!");
							}
							else if (puntosJ==puntosB) {
								System.out.print("\r\nEmpate. ");
								if (totalCartasJ<totalCartasB) {
									System.out.print("Tienes menos cartas. PIERDE LA BANCA!");
								}
								else if (totalCartasJ==totalCartasB) {
									System.out.print("Teneis la misma cantidad de cartas. PIERDE LA BANCA!");
								}
								else/*(totalCartasJ>totalCartasB)*/{
									System.out.print("La banca tiene menos cartas. HAS PERDIDO!");
								}
							}
							else /*(puntosJ<puntosB)*/{
								System.out.println("\r\nGana la banca. HAS PERDIDO!");
							}
							break;
						}
					}
					
					else if(puntosB>21&&puntosJ<=21) {
						limpiar();
						System.out.println("TE PLANTAS!");
						System.out.println("\r\nBANCA   | Total Cartas: " + totalCartasB + " Total Puntuación: " + puntosB);
						System.out.println("JUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
						System.out.println("\r\nLa banca se ha pasado. PIERDE LA BANCA!");
					}
					
					else /*(puntosB>21)*/{
						limpiar();
						System.out.println("TE PLANTAS!");
						System.out.println("\r\nBANCA   | Total Cartas: " + totalCartasB + " Total Puntuación: " + puntosB);
						System.out.println("JUGADOR | Total Cartas: " + totalCartasJ + " Total Puntuación: " + puntosJ + " Carta: " + carta);
						
						if (puntosJ<puntosB) {
							System.out.println("\r\nOs habeis pasado. PIERDE LA BANCA!");
						}
						
						else if (puntosJ>puntosB) {
							System.out.println("\r\nOs habeis pasado. HAS PERDIDO!");
						}
						
						else /*(puntosJ==puntosB)*/{
								System.out.print("\r\nEmpate. ");
								if (totalCartasJ<totalCartasB) {
									System.out.print("Tienes menos cartas. PIERDE LA BANCA!");
								}
								else if (totalCartasJ==totalCartasB) {
									System.out.print("Teneis la misma cantidad de cartas. PIERDE LA BANCA!");
								}
								else/*(totalCartasJ>totalCartasB)*/{
									System.out.print("La banca tiene menos cartas. HAS PERDIDO!");
								}
						}
					}
			}																							//fin switch
		}																								//fin bucle principal
	}																									//fin metodo ejecutable
			
	/////////////////////////////////////////////////////// METODOS //////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private static int _tester() {																		//METODO PARA TESTEAR
		System.out.println("VALOR CARTA/PUNTUACIÓN: ");
		return optInt();
	}

	private static int genNum(int min, int max) {														//generar numero aleatorio
		Random rand = new Random();
		return rand.nextInt((max-min)+1)+min;
	}
	
	@SuppressWarnings("resource")
	private static String opt() {																		//introducir opcion String
		Scanner scan = new Scanner(System.in);
		return scan.next();
	}
	
	@SuppressWarnings("resource")
	private static int optInt() {																		//introducir opcion numerica
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}
	
	private static void limpiar() {																		//limpiar pantalla
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

}
