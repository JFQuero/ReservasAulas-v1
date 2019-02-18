package org.iesalandalus.programacion.reservasaulas.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.*;

public class IUTextual {

	private static final String ERROR = "ERROR: ";
	private static final String VACIO = "No hay reservas que listar.";
	private static final String NOMBRE_VALIDO = "Juan Fernandez Quero";
	private static final String CORREO_VALIDO = "correo@valido.com";
	private ModeloReservasAulas modelo;

	/* Constructores */
	public IUTextual() {
		modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}

	/* Metodos */
	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		System.out.println("Adios! o/ ");
	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.instertarAula(aula);
			System.out.println("Aula añadida con exito.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			Aula aula = new Aula(Consola.leerNombreAula());
			List<Reserva> buscarReservas = modelo.getReservasAula(aula);
			if (!buscarReservas.isEmpty()) {
				System.out.println(ERROR + "No se puede borrar un aula con reservas activas.");
			}
			modelo.borrarAula(aula);
			System.out.println("Aula borrada con exito.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		try {
			Aula aula = new Aula(Consola.leerNombreAula());
			aula = modelo.buscarAula(aula);
			if (aula != null) {
				System.out.println("Aula encontrado con exito.");
				System.out.println("El aula buscada es: " + aula);
			} else {
				System.out.println("El aula buscada no existe.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("Listar Aulas");
		List<String> aulas = modelo.representarAulas();
		if (!aulas.isEmpty()) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			modelo.insertarProfesor(profesor);
			System.out.println("Profesor registrado con exito.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			Profesor profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
			List<Reserva> buscarReservas = modelo.getReservasProfesor(profesor);
			if (!buscarReservas.isEmpty()) {
				System.out.println(ERROR + "No se puede borrar un profesor con reservas activas.");
			} else {
				modelo.borrarProfesor(profesor);
				System.out.println("Profesor borrado con exito.");
			}
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		try {
			Profesor profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
			profesor = modelo.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("Profesor encontrado con exito.");
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("El profesor buscado no existe.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listar Profesores");
		List<String> profesores = modelo.representarProfesores();
		if (!profesores.isEmpty()) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que listar.");
		}
	}

	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar Reserva");
		try {
			Profesor profesor = modelo.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO));
			Reserva reserva = leerReserva(profesor);
			modelo.realizarReserva(reserva);
			System.out.println("Reserva realizada con exito.");
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Aula aula = new Aula(Consola.leerAula());
		return new Reserva(profesor, aula, permanencia);
	}

	public void anularReserva() {
		Consola.mostrarCabecera("Anular una Reserva");
		try {
			Profesor profesor = new Profesor(NOMBRE_VALIDO, CORREO_VALIDO);
			Reserva reserva = leerReserva(profesor);
			modelo.anularReserva(reserva);
			System.out.println("Reserva anulada con exito.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarReservas() {
		Consola.mostrarCabecera("Listar Reservas");
		try {
			List<String> reservas = modelo.representarReservas();
			if (!reservas.isEmpty()) {
				for (String reserva : reservas) {
					System.out.println(reserva);
				}
			} else {
				System.out.println(VACIO);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas de un aula");
		try {
			Aula aula = new Aula(Consola.leerNombreAula());
			List<Reserva> reservas = modelo.getReservasAula(aula);
			if (modelo.buscarAula(aula) == null) {
				System.out.println("El aula introducida no esta registrada.");
			}
			if (reservas.isEmpty()) {
				System.out.println(VACIO);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar reservas de un profesor");
		try {
			Profesor profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
			List<Reserva> reservas = modelo.getReservasProfesor(profesor);
			if (modelo.buscarProfesor(profesor) == null) {
				System.out.println("El profesor introducido no esta registrado.");
			}
			if (reservas.isEmpty()) {
				System.out.println(VACIO);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas de una permanencia");
		try {
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			List<Reserva> reservas = modelo.getReservasPermanencia(permanencia);
			if (reservas.isEmpty()) {
				System.out.println(VACIO);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar Disponibilidad");
		try {
			Aula aula = new Aula(Consola.leerNombreAula());
			if (modelo.buscarAula(aula) == null) {
				System.out.println("El aula introducida no esta registrada.");
			} else {
				Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
				if (modelo.consultarDisponibilidad(aula, permanencia)) {
					System.out.println("El aula " + aula + " esta disponible para la permanencia " + permanencia + ".");
				} else {
					System.out.println("El aula " + aula + " no esta disponible para la permanencia elegida.");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
}
