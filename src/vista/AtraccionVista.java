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
	
	public String menuLista = "[ID] [NOMBRE] [TIPO] [DISPONIBLE?] [ENCENDIDA?]";
	public static final String SECCION = "atracciones";

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {
		AtraccionVista av = new AtraccionVista();
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			av.echo("\n-["+SECCION.toUpperCase()+"]-");		
			av.echo("Administrar (admin) | "+av.crudMenu());
			av.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "admin":
					AtraccionVista.admin();
				break;
				
				case "list":
					AtraccionVista.lista();
					break;
					
				case "new":
					AtraccionVista.nuevo();
					break;
					
				case "edit":
					AtraccionVista.edit();
					break;
					
				case "find":
					AtraccionVista.buscar();
					break;
				case "back":
					
					av.echo("Saliendo de la seccion ["+SECCION.toUpperCase()+"]");
					continuar = false;
					break;
					
				default:
					av.echo("[ERROR] \""+opcion+"\" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}
	
	/**
	 * crea una nueva atraccion
	 * @return
	 */
	public static int nuevo() {
		AtraccionControlador ac = new AtraccionControlador(SECCION+".txt");
		AtraccionVista av = new AtraccionVista();
		Atraccion a = new Atraccion();
		int ret = 0;
		@SuppressWarnings("resource")
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
	
	/**
	 * saca por pantalla la lista de atracciones
	 * @return
	 */
	public static List<Atraccion> lista() {
		AtraccionControlador ac = new AtraccionControlador(SECCION+".txt");
		AtraccionVista av = new AtraccionVista();	
		List<Atraccion> lista = ac.lista();
		Iterator<Atraccion> iterador = lista.iterator();
		int total = lista.size();
		av.echo("Las atracciones actualmente funcionando:");
		av.echo("Total de "+SECCION+": "+total);
		av.echo(av.menuLista);
		while(iterador.hasNext()) {
			av.echo(iterador.next().toString());
		}
		av.echo(av.menuLista);
		av.echo("Las atracciones actualmente funcionando:");
		av.echo("Total de "+SECCION+": "+total);
		return lista;
	}
	
	/**
	 * busca una atraccion por nombre o por id
	 * @return
	 */
	public static Atraccion buscar() {
		Atraccion atraccion = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		AtraccionVista h = new AtraccionVista();
		AtraccionControlador ac = new AtraccionControlador(SECCION+".txt");
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
	
	/**
	 * busca y enciende o apaga una atraccion
	 * @return
	 */
	public static Atraccion admin() {
		Atraccion atraccion = AtraccionVista.buscar();
		if(atraccion != null) {
			@SuppressWarnings("resource")
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
	
	/**
	 * busca y edita los datos de una atraccion
	 * @return
	 */
	public static Atraccion edit() {
		Atraccion a = AtraccionVista.buscar();
		if(null != a) {
			AtraccionVista h = new AtraccionVista();
			AtraccionControlador ac = new AtraccionControlador(SECCION+".txt");
			@SuppressWarnings("resource")
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
	
	/**
	 * lista las atracciones que se encuentran en funcionamiento
	 * @return
	 */
	public static List<Atraccion> AtraccionesFuncionando() {
		AtraccionControlador ac = new AtraccionControlador(SECCION+".txt");
		AtraccionVista av = new AtraccionVista();	
		List<Atraccion> lista = ac.atraccionesFuncionando();
		Iterator<Atraccion> iterador = lista.iterator();
		String info = "";		
		int totalFuncionando = lista.size();
		int totalAtracciones = ac.lista().size();
		float porcentaje = ((float)totalFuncionando / totalAtracciones) *100;
		info += "Total de Atracciones en funcionamiento: "+totalFuncionando+"\n";
		info += "Atracciones en Total: "+totalAtracciones+"\n";
		info += "Las Atracciones en funcionamiento es "+av.numFormat(porcentaje)+"% de todas las Atracciones";
		av.echo(info);
		av.echo(av.menuLista);
		while(iterador.hasNext()) {
			av.echo(iterador.next().toString());
		}
		av.echo(av.menuLista);
		av.echo(info);
		return lista;
	}

}
