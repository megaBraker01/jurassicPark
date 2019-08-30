package controlador;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class InformeControlador {

	
	public String ARCHIVO = this.getClass().getSimpleName()+".txt";
	
	/**
	 * 
	 */
	public InformeControlador(String nombreArchivo) {
		this.ARCHIVO = nombreArchivo;
	}

}
