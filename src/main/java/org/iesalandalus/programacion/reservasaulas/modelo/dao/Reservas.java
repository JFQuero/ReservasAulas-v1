package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {

	private List<Reserva> coleccionReservas;

	/* Constructores */
	public Reservas() {
		coleccionReservas = new ArrayList<>();
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
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> copiaReservas = new ArrayList<>();
		for (Reserva reserva : reservas) {
			copiaReservas.add(new Reserva(reserva));
		}
		return copiaReservas;
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		if (coleccionReservas.contains(reserva)) {
			throw new OperationNotSupportedException("La reserva ya existe.");
		} else {
			coleccionReservas.add(new Reserva(reserva));
		}
	}

	public Reserva buscar(Reserva reserva) {
		int indice = coleccionReservas.indexOf(reserva);
		if (indice != -1) {
			return new Reserva(coleccionReservas.get(indice));
		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		if (!coleccionReservas.remove(reserva)) {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			representacion.add(new Reserva(reserva).toString());
		}
		return representacion;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("No se pueden buscar las reservas de un profesor nulo.");
		}
		List<Reserva> busquedaProfesor = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getProfesor().equals(profesor)) {
				busquedaProfesor.add(new Reserva(reserva));
			}
		}
		return busquedaProfesor;
	}

	public List<Reserva> getReservasAula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("No se pueden buscar las reservas de una aula nula.");
		}
		List<Reserva> busquedaAula = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getAula().equals(aula)) {
				busquedaAula.add(new Reserva(reserva));
			}
		}
		return busquedaAula;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new IllegalArgumentException("No se pueden buscar las reservas por una permanencia nula.");
		}
		List<Reserva> busquedaPermanencia = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getPermanencia().equals(permanencia)) {
				busquedaPermanencia.add(new Reserva(reserva));
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

		for (Reserva reserva : coleccionReservas) {
			if (reserva.getAula().equals(aula) & reserva.getPermanencia().equals(permanencia)) {
				return false;
			}
		}
		return true;
	}
}