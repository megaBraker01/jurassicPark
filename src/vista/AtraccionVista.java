/**
 * 
 */
package vista;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controlador.AtraccionControlador;
import modelo.Atraccion;

/**
 * @author rafael
 *
 */
public class AtraccionVista extends Herramientas {
	
	public String menuLista = "[ID] [NOMBRE] [TIPO] [AYUDANTES NECESARIOS] [DISPONIBLE?]";

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {
		String seccion = "atracciones";
		AtraccionVista av = new AtraccionVista();		
		AtraccionControlador ac = new AtraccionControlador(seccion+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			av.echo("\n-["+seccion.toUpperCase()+"]-");		
			av.crudMenu();
			av.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "list":
					List<Atraccion> lista = ac.lista();
					Iterator<Atraccion> iterador = lista.iterator();
					int total = lista.size();
					av.echo("Total de "+seccion+": "+total);
					av.echo(av.menuLista);
					while(iterador.hasNext()) {
						av.echo(iterador.next().toString());
					}
					av.echo(av.menuLista);
					av.echo("Total de "+seccion+": "+total);
					break;
					
				case "new":
					AtraccionVista.nuevo();
				case "edit":
					break;
				case "back":
					
					av.echo("Saliendo de la seccion ["+seccion.toUpperCase()+"]");
					continuar = false;
					break;
					
				default:
					av.echo("[ERROR] \""+opcion+"\" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}
	
	public static int nuevo() {
		AtraccionControlador ac = new AtraccionControlador("atracciones.txt");
		AtraccionVista av = new AtraccionVista();
		Atraccion a = new Atraccion();
		int ret = 0;
		Scanner sc = new Scanner(System.in);
		av.echo("Nombre de la Atraccion");
		String nombre = sc.nextLine().toLowerCase();
		
		av.echo("Tipo de Atraccion (a, b, c, d, e");
		char tipo = sc.nextLine().toUpperCase().charAt(0);
		
		av.echo("Esta disponible? (si, no)");
		String disp = sc.nextLine().toLowerCase();
		boolean disponible = (disp.equals("si") || disp.equals("s") || disp.equals(""));
		a.setNombre(nombre);
		a.setTipo(tipo);
		a.setDisponible(disponible);
		ac.nuevo(a);
		av.echo("Atraccion insertada correctamente");
		ret = 1;
		return ret;
	}

}
