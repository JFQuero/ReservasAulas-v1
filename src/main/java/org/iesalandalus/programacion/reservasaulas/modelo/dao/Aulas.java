package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

public class Aulas {

	private static final int MAX_AULAS = 120;
	private int numAulas;
	private Aula[] coleccionAulas;

	/* Constructores */
	public Aulas() {
		coleccionAulas = new Aula[MAX_AULAS];
		numAulas = 0;
	}

	public Aulas(Aulas aulas) {
		setAulas(aulas);
	}

	/* Metodos */
	private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		}
		coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
		numAulas = aulas.numAulas;
	}

	private Aula[] copiaProfundaAulas(Aula[] aulas) {
		Aula[] copiaAulas = new Aula[aulas.length];
		for (int i = 0; i < aulas.length && aulas[i] != null; i++) {
			copiaAulas[i] = new Aula(aulas[i]);
		}
		return copiaAulas;
	}

	public Aula[] getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

	public int getNumAulas() {
		return numAulas;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		}
		int indice = buscarIndiceAula(aula);
		if (!indiceNoSuperaTamano(indice)) {
			coleccionAulas[indice] = new Aula(aula);
			numAulas++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("El aula ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan mas aulas.");
			}
		}
	}

	private int buscarIndiceAula(Aula aula) {
		int indice = 0;
		boolean aulaEncontrada = false;
		while (indiceNoSuperaTamano(indice) && !aulaEncontrada) {
			if (coleccionAulas[indice].equals(aula)) {
				aulaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numAulas;
	}

	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_AULAS;
	}

	public Aula buscar(Aula aula) {
		int indice = 0;
		indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			return new Aula(coleccionAulas[indice]);
		} else {
			return null;
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
		int indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numAulas - 1; i++) {
			coleccionAulas[i] = new Aula(coleccionAulas[i + 1]);
		}
		coleccionAulas[numAulas] = null;
		numAulas--;
	}

	public String[] representar() {
		String[] representacion = new String[numAulas];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			representacion[i] = new Aula(coleccionAulas[i]).toString();
		}
		return representacion;
	}
}
