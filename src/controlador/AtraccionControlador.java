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
	
	public void editar(Atraccion Atraccion) {
		Atraccion existeAtraccion = this.buscarPorId(Atraccion.getId());
		if(existeAtraccion != null) {
			String archivo = ArchivoControlador.leer(ARCHIVO);
			String[] fila = archivo.split("\n");
			String nuevoContenido = "";
			for(int i = fila.length - 1; i >= 0; i--) {
				String registros = fila[i];
				String[] campos = registros.split(", ");
				if(Integer.parseInt(campos[0]) == Atraccion.getId()) {
					fila[i] = Atraccion.toString();
					break;
				}
			}
			
			for(int i = 0; i <= fila.length - 1 ; i++) {
				nuevoContenido += fila[i]+"\n";
			}
			
			ArchivoControlador.editar(ARCHIVO, nuevoContenido.trim(), true);
		}
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
					Boolean.parseBoolean(campos[3].trim()),
					Boolean.parseBoolean(campos[4].trim())
				);
				lista.add(Atraccion);
			}
		}		
		return lista;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Atraccion buscarPorId(int id) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Atraccion Atraccion = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(Integer.parseInt(campos[0]) == id) {
				Atraccion = new Atraccion(
						Integer.parseInt(campos[0].trim()),
						campos[1].trim(),
						campos[2].charAt(0),
						Boolean.parseBoolean(campos[3].trim()),
						Boolean.parseBoolean(campos[4].trim())
					);
				break;
			}
		}
		
		return Atraccion;
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public Atraccion buscarPorNombre(String nombre) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Atraccion Atraccion = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(campos[1].equals(nombre)) {
				Atraccion = new Atraccion(
						Integer.parseInt(campos[0].trim()),
						campos[1].trim(),
						campos[2].charAt(0),
						Boolean.parseBoolean(campos[3].trim()),
						Boolean.parseBoolean(campos[4].trim())
					);
				break;
			}
		}
		
		return Atraccion;
	}
	
	/**
	 * devuelve las atracciones con valor disponible true
	 * @return
	 */
	public List<Atraccion> atraccionesFuncionando() {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		List<Atraccion> lista = new ArrayList<>();
		if(fila.length > 0 && !archivo.equals("")) {
			boolean funcionando = true;
			for(String registros : fila) {
				String[] campos = registros.split(", ");
				if(Boolean.parseBoolean(campos[4].trim()) == funcionando) {
					Atraccion Atraccion = new Atraccion(
						Integer.parseInt(campos[0].trim()),
						campos[1].trim(),
						campos[2].charAt(0),
						Boolean.parseBoolean(campos[3].trim()),
						Boolean.parseBoolean(campos[4].trim())
					);
					lista.add(Atraccion);
				}
				
			}
		}		
		return lista;
	}

}
