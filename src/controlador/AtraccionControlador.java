/**
 * 
 */
package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Atraccion;

/**
 * @author rafael
 *
 */
public class AtraccionControlador {

	public String ARCHIVO = this.getClass().getSimpleName()+".txt";
	
	public AtraccionControlador(String nombreArchivo) {
		this.ARCHIVO = nombreArchivo;
	}
	
	/**
	 * lee el ultimo registro de fichero especificado en la constante ARCHIVO
	 * y retorna el ultimo id incrementandolo en uno (1)
	 * @return ultimoID
	 */
	private int generarId() {
		int ultimoID = 0;
		if(ArchivoControlador.existe(ARCHIVO)) {
			String archivo = ArchivoControlador.leer(ARCHIVO);
			String[] filas = archivo.split("\n");			
			if(filas.length > 0) {
				int ultimo = filas.length-1;
				String[] campoid = filas[ultimo].split(", ");
				ultimoID = (!campoid[0].isEmpty()) ? Integer.parseInt(campoid[0]) : 0 ;
			}
		}
		
    	return ++ultimoID;
    }
	
	/**
	 * inserta un nuevo Atraccion al final fichero 
	 * especificado en la constante ARCHIVO
	 * @param Atraccion
	 */
	public void nuevo(Atraccion Atraccion) {
		Atraccion.setId(this.generarId());
		ArchivoControlador.editar(ARCHIVO, Atraccion.toString());
	}
	
	/**
	 * lee el fichero especificado en la constante ARCHIVO
	 * y retorna una lista de objetos
	 * @return lista Entrada
	 */
	public List<Atraccion> lista() {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		List<Atraccion> lista = new ArrayList<>();
		if(fila.length > 0 && !archivo.equals("")) {
			for(String registros : fila) {
				String[] campos = registros.split(", ");
				Atraccion Atraccion = new Atraccion(
					Integer.parseInt(campos[0].trim()),
					campos[1].trim(),
					campos[2].charAt(0),
					Boolean.parseBoolean(campos[4].trim())
				);
				lista.add(Atraccion);
			}
		}		
		return lista;
	}
	

}
