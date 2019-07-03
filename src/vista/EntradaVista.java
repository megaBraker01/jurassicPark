package vista;

import java.util.Iterator;
import java.util.Scanner;

import controlador.EntradaControlador;
import modelo.Entrada;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EntradaVista extends Herramientas {

	public EntradaVista() {
	}

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {
		EntradaVista cv = new EntradaVista();		
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-[Entradas]-");		
			cv.crudMenu();
			cv.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "list":
					
					Iterator<Entrada> iterador = ec.lista().iterator();
					cv.echo("\n[ID] [IDCLIENTE] [TIPO] [PRECIO] [FECHA DE LA COMPRA] [DESCUENTO] [ESVIP?]");
					while(iterador.hasNext()) {
						cv.echo(iterador.next().toString());
					}
					break;
					
				case "new":
					break;
					
				case "edit":
					break;
				case "back":
					
					cv.echo("Saliendo de la seccion [Entrada]");
					continuar = false;
					break;
					
				default:
					cv.echo("[ERROR] \""+opcion+"\" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}

}
