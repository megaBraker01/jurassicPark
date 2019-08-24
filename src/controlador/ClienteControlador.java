package controlador;

import modelo.Cliente;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class ClienteControlador extends PersonaControlador {
	
	/**
	 * @param nombreArchivo
	 */
	public ClienteControlador(String nombreArchivo) {
		super(nombreArchivo);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * busca en el fichero especificado en la constante ARCHIVO
	 * y retorna un objeco si el parametro dni coincide con alguno del fichero
	 * retorna null si no
	 * @param dni
	 * @return Persona
	 */
	public Cliente buscarPordni(String dni) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Cliente Cliente = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(campos.length > 3) {
				if(campos[3].equalsIgnoreCase(dni)) {
					Cliente = new Cliente(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3]);
					break;
				}
			}			
		}
		return Cliente;
	}
	
	/**
	 * busca en el fichero especificado en la constante ARCHIVO
	 * y retorna un objeco si el parametro id coincide con alguno del fichero
	 * retorna null si no
	 * @param id
	 * @return Persona
	 */
	public Cliente buscarPorId(int id) {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		Cliente Cliente = null;
		for(int i = fila.length - 1; i >= 0; i--) {
			String registros = fila[i];
			String[] campos = registros.split(", ");
			if(Integer.parseInt(campos[0]) == id) {
				Cliente = new Cliente(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2].trim()), campos[3]);
				break;
			}
		}
		return Cliente;
	}

}
