package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.vista.*;

public class MainApp {

	public static void main(String[] args) {
		Consola.mostrarCabecera("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		Consola.mostrarCabecera("Juan Fernandez Quero - 1ºDAM");		
		IUTextual vista = new IUTextual();
		vista.comenzar();
	}
}