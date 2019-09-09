package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import modelo.Persona;
/**
 * @author Rafael Perez Sanchez
 *
 */
public class EmpleadoControlador extends PersonaControlador {
	
	/**
	 * @param nombreArchivo
	 */
	public EmpleadoControlador(String nombreArchivo) {
		super(nombreArchivo);
	}
	
	/**
	 * lee el fichero especificado en la constante ARCHIVO
	 * y retorna una lista de objetos
	 * @return lista Empleado
	 */
	public List<Persona> lista() {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		List<Persona> lista = new ArrayList<>();
		if(fila.length > 0 && !archivo.equals("")) {
			for(String registros : fila) {
				String[] campos = registros.split(", ");
				Empleado Empleado = new Empleado(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3], Integer.parseInt(campos[4].trim()));
				lista.add(Empleado);
			}
		}	
		return lista;
	}
	
	/**
	 * busca en el fichero especificado en la constante ARCHIVO
	 * y retorna un objeco si el parametro dni coincide con alguno del fichero
	 * retorna null si no
	 * @param dni
	 * @return Empleado
	 */
	public Empleado buscarPordni(String dni) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Empleado Empleado = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(campos.length > 3) {
				if(campos[3].equalsIgnoreCase(dni)) {
					Empleado = new Empleado(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3], Integer.parseInt(campos[4].trim()));
					break;
				}
			}
		}
		return Empleado;
	}
	
	/**
	 * busca en el fichero especificado en la constante ARCHIVO
	 * y retorna un objeco si el parametro id coincide con alguno del fichero
	 * retorna null si no
	 * @param id
	 * @return Empleado
	 */
	public Empleado buscarPorId(int id) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Empleado Empleado = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(Integer.parseInt(campos[0]) == id) {
				Empleado = new Empleado(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3], Integer.parseInt(campos[4].trim()));
				break;
			}
		}
		return Empleado;
	}

}
