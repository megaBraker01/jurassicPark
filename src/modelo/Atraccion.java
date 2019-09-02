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
	 * A = 6, B = 5, C = 3, D = 5, E = 7
	 */
	public static final char TIPO_A = 'A', TIPO_B = 'B', TIPO_C = 'C', TIPO_D = 'D', TIPO_E = 'E';
	
	/**
	 * indica el tipo de atraccion que es.
	 * sus valores permitidos son A, B, C, D, E
	 */
	private char tipo = TIPO_A;
	
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


	public Atraccion(int id, String nombre, char tipo, boolean disponible) {
		this.setId(id);
		this.setNombre(nombre);
		this.setTipo(tipo);
		this.setDisponible(disponible);
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
		switch(tipo) {
			case TIPO_A:
			case TIPO_B:
			case TIPO_D:
			case TIPO_C:
			case TIPO_E:
				this.tipo = tipo;
				break;
		}
		
	}



	public int getAyudantes() {
		int ret;
		switch(this.tipo) {
		case TIPO_A:
			ret = 6;
			break;
		case TIPO_B:
		case TIPO_D:
			ret = 5;
			break;
		case TIPO_C:
			ret = 3;
			break;
		case TIPO_E:
			ret = 7;
			break;
		default:
			ret = 6;
			break;
		}
		return ret;
	}


	public boolean isDisponible() {
		return this.disponible;
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
		return getId()+ ", " +getNombre()+ ", " +getTipo()+ ", " +getAyudantes()+ ", " + isDisponible();
	}

}
