package modelo;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class Atraccion {
	
	private int id;
	
	private String nombre;
	
	/**
	 * los posibles tipos de atracciones que pueden haber
	 * indicando la cantidad de ayudantes necesarios
	 * para que la atraccion funcione 
	 */
	public static final int A = 6, B = 5, C = 3, D = 5, E = 7;
	
	/**
	 * indica el tipo de atraccion que es.
	 * sus valores permitidos son A, B, C, D, E
	 */
	private char tipo;
	
	/**
	 * indica la cantidad de ayudantes necesarios para
	 * que la atraccion pueda funcionar
	 */
	private int ayudantes;
	
	/**
	 * indica si la atraccion se puede poner en marcha,
	 * esto puede depender de varios factores
	 */
	private boolean disponible = false;
	
	/**
	 * indica si la atraccion se encuentra encendida
	 */
	private boolean enciendida = false;

	/**
	 * 
	 */
	public Atraccion() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Atraccion(String nombre, char tipo, int ayudantes, boolean disponible) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.ayudantes = ayudantes;
		this.disponible = disponible;
	}



	public Atraccion(int id, String nombre, char tipo, int ayudantes, boolean disponible) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.ayudantes = ayudantes;
		this.disponible = disponible;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public char getTipo() {
		return tipo;
	}



	public void setTipo(char tipo) {
		this.tipo = tipo;
	}



	public int getAyudantes() {
		return ayudantes;
	}



	public void setAyudantes(int ayudantes) {
		this.ayudantes = ayudantes;
	}



	public boolean isDisponible() {
		return disponible;
	}



	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}



	public boolean isEnciendida() {
		return enciendida;
	}



	public void setEnciendida(boolean enciendida) {
		this.enciendida = enciendida;
	}
	
	
	public String toString() {
		return "Nombre: " +getNombre()+ " Tipo: " +getTipo()+ " Encendida?: " +isEnciendida();
	}

}
