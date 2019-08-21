package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Entrada;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EntradaControlador {

	public String ARCHIVO = this.getClass().getSimpleName()+".txt";
	
	public EntradaControlador(String nombreArchivo) {
		this.ARCHIVO = nombreArchivo;
	}
	
	/**
	 * lee el fichero especificado en la constante ARCHIVO
	 * y retorna una lista de objetos
	 * @return lista Entrada
	 */
	public List<Entrada> lista() {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		List<Entrada> lista = new ArrayList<>();
		if(fila.length > 0) {
			for(String registros : fila) {
				String[] campos = registros.split(", ");
				Entrada Entrada = new Entrada(
						Integer.parseInt(campos[0].trim()), 
						Integer.parseInt(campos[1].trim()),
						Integer.parseInt(campos[2].trim()),
						Double.parseDouble(campos[3]),
						Integer.parseInt(campos[4].trim()),
						Boolean.parseBoolean(campos[6]),
						campos[7]
								);
				lista.add(Entrada);
				
			}
		}		
		return lista;
	}
	
	
	/**
	 * inserta un nuevo Entrada al final fichero 
	 * especificado en la constante ARCHIVO
	 * @param Entrada
	 */
	public void nuevo(Entrada Entrada) {
		Entrada.setId(this.generarId());
		ArchivoControlador.editar(ARCHIVO, Entrada.toString());
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
			String[] fila = archivo.split("\n");			
			if(fila.length > 0) {
				int ultimo = fila.length-1;
				String[] campoid = fila[ultimo].split(", ");
				ultimoID = Integer.parseInt(campoid[0]);
			}
		}
		
    	return ++ultimoID;
    }


}
