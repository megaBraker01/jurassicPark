package controlador;

import java.io.*;  


/**
 * @author Rafael Perez Sanchez
 *
 */
public class ArchivoControlador {
	
	public static final String ARCHIVO_RUTA = "src"+ File.separator + "archivos" + File.separator;
	//public static final String BBDD_RUTA = "src"+ File.separator + "BBDD" + File.separator;
	
	
	/**
	 * crear un documento dentro de la carpeta archivos
	 * 
	 * @param nombre: nombre del archivo
	 * @param ext: extension del archivo (solo csv o txt)
	 * @param contenido: contenido del archivo 
	 */
	public static void crear(String nombre, String ext, String contenido) {
		String nombreArchivo = nombre + "." + ext;
		File archivo = new File(ARCHIVO_RUTA + nombreArchivo);
		try {
			archivo.createNewFile();
			FileWriter escribir = new FileWriter(archivo.getAbsolutePath());
			for(int i = 0; i < contenido.length(); i++) {
				escribir.write(contenido.charAt(i));
			}
			escribir.flush();
			escribir.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * verifica si existe la ruta (carpeta o folder) especificada
	 * @param path
	 * @return true o false
	 */
	public static boolean existe(String path) {
		File archivo = new File(ARCHIVO_RUTA + path);
		return archivo.exists();
	}
	
	/**
	 * sirve para leer el contenido de un archivo
	 * @param path: nombre de archivo con extencion, ej; archivo.txt
	 * @return String ret: contenido del archivo
	 */
	public static String leer(String path) {
		String ruta = ARCHIVO_RUTA + path;
		File archivo = new File(ruta);
		String ret = "";
		if(archivo.exists()) {
			try {
				FileReader leer = new FileReader(archivo.getAbsolutePath());
				BufferedReader bf = new BufferedReader(leer);
				String contenido;			
				while((contenido = bf.readLine()) != null) {
					ret += contenido+"\n";
				}
				bf.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No hay nada para leer en el directorio:\n"+ruta);
			if(path.contains(".")) {
				String[] documento = path.split("\\.");
				int numero = documento.length;
				ArchivoControlador.crear(documento[0], documento[1], null);
			}			
		}
		
		return ret;		
	}
	
	/**
	 * introduce el contenido al final del archivo,
	 * concatenado con su contenido anterior
	 * @param path: nombre de archivo con extencion, ej; archivo.txt
	 * @param contenido: nuevo contenido
	 */
	public static void editar(String path, String contenido) {
		try {
			BufferedWriter escribir = new BufferedWriter(new FileWriter(ARCHIVO_RUTA + path, true));
			escribir.write(contenido+"\n");
			escribir.flush();
			escribir.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * introduce el contenido al final del archivo,
	 * concatenado con su contenido anterior
	 * @param path: nombre de archivo con extencion, ej; archivo.txt
	 * @param contenido: nuevo contenido
	 */
	public static void editar(String path, String contenido, boolean sobreEscribir) {
		try {
			BufferedWriter escribir = new BufferedWriter(new FileWriter(ARCHIVO_RUTA + path));
			escribir.write(contenido+"\n");
			escribir.flush();
			escribir.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * elimina el archivo especificado en el path
	 * @param path: nombre de archivo con extencion, ej; archivo.txt
	 * @return true en el caso de exito
	 */
	public static boolean eliminar(String path) {
		File archivo = new File(ARCHIVO_RUTA + path);
		return archivo.delete();		
	}
	
	/**
	 * crear un nuevo directorio
	 * @param path: nombre de la carpeta o directorio
	 * @return true en el caso de exito
	 */
	public static boolean crarDirectorio(String path) {
		File directorio = new File(ARCHIVO_RUTA + path);
		return directorio.mkdirs();
	}
	
	/**
	 * muestra en pantalla el contenido de un directorio
	 * @param path: nombre de la carpeta o directorio
	 */
	public static void listarDirectorio(String path) {
		File directorio = new File(path);
		for(int i = 0; i < directorio.list().length; i++) {
			System.out.println(directorio.list()[i]);
		}
	}

}
