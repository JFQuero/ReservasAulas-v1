package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

public class Aula {

	private String nombre;

	/* Constructores */
	public Aula(String nombre) {
		setNombre(nombre);
	}

	public Aula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede copiar un aula nula.");
		}
		setNombre(aula.nombre);
	}

	/* Metodos */
	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
		}
		if (nombre.equals("")) {
			throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	/* Otros Metodos */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equalsIgnoreCase(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[nombre=" + nombre + "]";
	}
}
