package modelo;

/**
 * 
 * @author Rafael Perez Sanchez
 *
 */
public class Persona {
	protected String nombreCompleto;
	protected int edad;
	protected int id;
	protected String dni;
	
	public Persona(int id, String nombreCompleto, int edad, String dni) {
		setId(id);
		setNombreCompleto(nombreCompleto);
		setEdad(edad);
		setDni(dni);
	}
	
	public Persona(String nombreCompleto, int edad, String dni) {
		setNombreCompleto(nombreCompleto);
		setEdad(edad);
		setDni(dni);
	}
	
	public Persona(String nombreCompleto, int edad) {
		setNombreCompleto(nombreCompleto);
		setEdad(edad);
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni.toUpperCase();
	}

	public String toString() {
		return getId() + ", " + getNombreCompleto() + ", " + getEdad() + ", " + getDni();
	}
	
	
}
                                     