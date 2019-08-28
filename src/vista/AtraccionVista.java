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
			av.echo("Administrar (admin) | "+av.crudMenu());
			av.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "admin":
					AtraccionVista.admin();
				break;
				
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
					AtraccionVista.edit();
					break;
					
				case "find":
					AtraccionVista.buscar();
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
		
		av.echo("Tipo de Atraccion (a, b, c, d, e)");
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
	
	public static Atraccion buscar() {
		Atraccion atraccion = null;
		Scanner sc = new Scanner(System.in);
		AtraccionVista h = new AtraccionVista();
		AtraccionControlador ac = new AtraccionControlador("atracciones.txt");
		h.echo("Indique el nombre o el ID de la atraccion");
		String nombreOid = sc.nextLine().trim().toLowerCase(); 
		if(h.isNumeric(nombreOid)) {
			atraccion = ac.buscarPorId(Integer.parseInt(nombreOid));
		} else {
			atraccion = ac.buscarPorNombre(nombreOid);
		}
		
		if (atraccion != null) {
			h.echo(h.menuLista);
			h.echo(atraccion);
		} else {
			h.echo("No se encontro la traccion que coincida con el id o nombre " + nombreOid);
		}
		return atraccion;
	}
	
	public static Atraccion admin() {
		Atraccion atraccion = AtraccionVista.buscar();
		if(atraccion != null) {
			Scanner sc = new Scanner(System.in);
			AtraccionVista h = new AtraccionVista();
			
			h.echo("Encender la traccion (si, no)");
			String respuesta = sc.nextLine().trim().toLowerCase();
			switch(respuesta) {
				case "si":
				case "s":
					atraccion.setEnciendida(true);
					h.echo("Atraccion encendida");
					break;
				default:
					atraccion.setEnciendida(false);
					h.echo("Atraccion apagada");
					break;
			}
		}
		return atraccion;
		
	}
	
	
	public static Atraccion edit() {
		Atraccion a = AtraccionVista.buscar();
		if(null != a) {
			AtraccionVista h = new AtraccionVista();
			AtraccionControlador ac = new AtraccionControlador("atracciones.txt");
			Scanner sc = new Scanner(System.in);
			h.echo("Nombre de la Atraccion");
			String nombre = sc.nextLine().toLowerCase();
			
			h.echo("Tipo de Atraccion (a, b, c, d, e)");
			String tipo = sc.nextLine().toUpperCase();
			
			h.echo("Esta disponible? (si, no)");
			String disp = sc.nextLine().toLowerCase();			
			boolean disponible = (disp.equals("si") || disp.equals("s") || disp.equals(""));
			
			if (!nombre.equals("")) { a.setNombre(nombre); }
			if (!tipo.equals("")) { a.setTipo(tipo.charAt(0)); }			
			a.setDisponible(disponible);
			ac.editar(a);
			h.echo("Atraccion editada correctamente");
			h.echo(h.menuLista);
			h.echo(a);
			
		}
		
		return a;
	}

}
