package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import modelo.Persona;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class PersonaControlador {
	
	public static final String ARCHIVO_RUTA = "src"+ File.separator + "archivos" + File.separator;
	public String ARCHIVO = this.getClass().getSimpleName()+".txt";

	/**
	 * 
	 */
	public PersonaControlador(String nombreArchivo) {
		this.ARCHIVO = nombreArchivo;
	}

	/**
	 * @param args
	 */
	
	public String nombre() {
	    return this.getClass().getSimpleName();
	}
	
	/**
	 * lee el fichero especificado en la constante ARCHIVO
	 * y retorna una lista de objetos
	 * @return lista Persona
	 */
	public List<Persona> lista() {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		List<Persona> lista = new ArrayList<>();
		if(fila.length > 0) {
			for(String registros : fila) {
				String[] campos = registros.split(", ");
				Persona Persona = new Persona(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3]);
				lista.add(Persona);
				
			}
		}		
		return lista;
	}
	
	/**
	 * busca en el fichero especificado en la constante ARCHIVO
	 * y retorna un objeco si el parametro id coincide con alguno del fichero
	 * retorna null si no
	 * @param id
	 * @return Persona
	 */
	public Persona buscarPorId(int id) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Persona Persona = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(Integer.parseInt(campos[0]) == id) {
				Persona = new Persona(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3]);
				break;
			}
		}
		return Persona;
	}
	
	/**
	 * busca en el fichero especificado en la constante ARCHIVO
	 * y retorna un objeco si el parametro dni coincide con alguno del fichero
	 * retorna null si no
	 * @param dni
	 * @return Persona
	 */
	public Persona buscarPordni(String dni) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Persona Persona = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(campos[3].equalsIgnoreCase(dni)) {
				Persona = new Persona(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3]);
				break;
			}
		}
		return Persona;
	}
	
	/**
	 * inserta un nuevo Persona al final fichero 
	 * especificado en la constante ARCHIVO
	 * @param Persona
	 */
	public void nuevo(Persona Persona) {
		Persona.setId(this.generarId());
		ArchivoControlador.editar(ARCHIVO, Persona.toString());
	}
	
	/**
	 * edita el fichero especificado en la constante ARCHIVO
	 * actualizando los datos del Persona que se pasa por parametro,
	 * siempre y cuando el id coincida con algun id del fichero
	 * @param Persona
	 */
	public void editar(Persona Persona) {
		Persona existePersona = this.buscarPorId(Persona.getId());
		if(existePersona != null) {
			String archivo = ArchivoControlador.leer(ARCHIVO);
			String[] fila = archivo.split("\n");
			String nuevoContenido = "";
			for(int i = fila.length - 1; i >= 0; i--) {
				String registros = fila[i];
				String[] campos = registros.split(", ");
				if(Integer.parseInt(campos[0]) == Persona.getId()) {
					fila[i] = Persona.toString();
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
