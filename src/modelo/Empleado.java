package modelo;

import interfaz.personaInterfaz;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class Empleado extends Persona implements personaInterfaz {

	/**
	 * indica al grupo al que pertenece:
	 * 1 = Atencion al cliente
	 * 2 = Relaciones publicas
	 * 3 = Responsable de atraccion
	 * 4 = Ayudante de atraccion
	 */
	private int grupo;
	
	public Empleado(String nombre, int edad) {
		super(nombre, edad);
	}
	
	public Empleado(String nombreCompleto, int edad, String dni) {
		super(nombreCompleto, edad, dni);
	}

	public Empleado(int id, String nombreCompleto, int edad, String dni) {
		super(id, nombreCompleto, edad, dni);
	}
	
	public Empleado(int id, String nombreCompleto, int edad, String dni, int grupo) {
		super(id, nombreCompleto, edad, dni);
		setGrupo(grupo);
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	public String toString() {
		return super.toString() + ", " + this.getGrupo();
		
	}

}
