package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {

	private static final int MAX_RESERVAS = 120;
	private int numReservas;
	private Reserva[] coleccionReservas;

	/* Constructores */
	public Reservas() {
		coleccionReservas = new Reserva[MAX_RESERVAS];
		numReservas = 0;
	}

	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	/* Metodos */
	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
		numReservas = reservas.numReservas;
	}

	private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
		Reserva[] copiaReservas = new Reserva[reservas.length];
		for (int i = 0; i < reservas.length && reservas[i] != null; i++) {
			copiaReservas[i] = new Reserva(reservas[i]);
		}
		return copiaReservas;
	}

	public Reserva[] getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return numReservas;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (!indiceNoSuperaTamano(indice)) {
			coleccionReservas[indice] = new Reserva(reserva);
			numReservas++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("La reserva ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más reservas.");
			}
		}
	}

	private int buscarIndiceReserva(Reserva reserva) {
		int indice = 0;
		boolean reservaEncontrada = false;
		while (indiceNoSuperaTamano(indice) && !reservaEncontrada) {
			if (coleccionReservas[indice].equals(reserva)) {
				reservaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numReservas;
	}

	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_RESERVAS;
	}

	public Reserva buscar(Reserva reserva) {
		int indice = 0;
		indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			return new Reserva(coleccionReservas[indice]);
		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numReservas - 1; i++) {
			coleccionReservas[i] = coleccionReservas[i + 1];
		}
		coleccionReservas[numReservas] = null;
		numReservas--;
	}

	public String[] representar() {
		String[] representacion = new String[numReservas];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			representacion[i] = coleccionReservas[i].toString();
		}
		return representacion;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("No se pueden buscar las reservas de un profesor nulo.");
		}
		int posicion = 0;
		Reserva[] busquedaProfesor = new Reserva[MAX_RESERVAS];
		for (int i = 0; i < coleccionReservas.length - 1 && coleccionReservas[i] != null; i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				System.out.println(coleccionReservas[i]);
				busquedaProfesor[posicion] = new Reserva(coleccionReservas[i]);
				posicion++;
			}
		}
		return busquedaProfesor;
	}

	public Reserva[] getReservasAula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("No se pueden buscar las reservas de una aula nula.");
		}
		int posicion = 0;
		Reserva[] busquedaAula = new Reserva[MAX_RESERVAS];
		for (int i = 0; i < coleccionReservas.length - 1 && coleccionReservas[i] != null; i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				System.out.println(coleccionReservas[i]);
				busquedaAula[posicion] = new Reserva(coleccionReservas[i]);
				posicion++;
			}
		}
		return busquedaAula;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new IllegalArgumentException("No se pueden buscar las reservas por una permanencia nula.");
		}
		int posicion = 0;
		Reserva[] busquedaPermanencia = new Reserva[MAX_RESERVAS];
		for (int i = 0; i < coleccionReservas.length - 1 && coleccionReservas[i] != null; i++) {
			if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
				System.out.println(coleccionReservas[i]);
				busquedaPermanencia[posicion] = new Reserva(coleccionReservas[i]);
				posicion++;
			}
		}
		return busquedaPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		}
		if (permanencia == null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; i < coleccionReservas.length - 1 && coleccionReservas[i] != null; i++) {
			if (coleccionReservas[i].getAula().equals(aula) & coleccionReservas[i].getPermanencia().equals(permanencia)) {
				return false;
			}
		}
		return true;
	}
}