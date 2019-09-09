package vista;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import controlador.ClienteControlador;
import modelo.Cliente;
import modelo.Persona;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class ClienteVista extends Herramientas {
	
	public static final String SECCION = "clientes";

	public ClienteVista() {}
	
	public static boolean main(String[] args) {
		ClienteVista cv = new ClienteVista();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-["+SECCION.toUpperCase()+"]-");		
			cv.echo(cv.crudMenu());
			cv.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "list":
					ClienteVista.lista();
					break;
					
				case "new":					
					cv.echo("indique el DNI del cliente");
					Persona cli = ClienteVista.buscar();
					if (cli != null) {
						cv.echo("Ya existe un cliente con el DNI indicado");
						cv.echo(cli);
					} else {
						cv.echo("NO existe cliente con el DNI indicado, vamos a crearlo");
						ClienteVista.nuevo();
					}
					break;
					
				case "edit":					
					ClienteVista.editar();					
					break;
					
				case "find":
					ClienteVista.find();
					break;
					
				case "back":
					
					cv.echo("Saliendo de la seccion ["+SECCION.toUpperCase()+"]");
					continuar = false;
					break;
					
				default:
					cv.echo("[ERROR] "+opcion+" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}
	
	public static void lista() {
		ClienteVista cv = new ClienteVista();		
		ClienteControlador cc = new ClienteControlador(SECCION+".txt");
		List<Persona> lista = cc.lista();
		int total = lista.size();
		Iterator<Persona> iterador = lista.iterator();
		cv.echo("Total de "+SECCION+": "+total);
		cv.echo(cv.menuLista);
		while(iterador.hasNext()) {
			cv.echo(iterador.next().toString());
		}
		cv.echo("Total de "+SECCION+": "+total);
	}
	
	public static void find () {
		ClienteVista cv = new ClienteVista();		
		cv.echo("indique el DNI del cliente");
		Cliente cli = ClienteVista.buscar();
		if (cli != null) {
			cv.echo("Ya existe un cliente con el DNI indicado");
			cv.echo(cv.menuLista);
			cv.echo(cli);
		} else {
			cv.echo("NO existe cliente con el DNI indicado");
		}
	}
	
	public static Cliente buscar () {
		Cliente ret = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		ClienteControlador cc = new ClienteControlador(SECCION+".txt");
		ClienteVista cv = new ClienteVista();
		String dni;
		cv.echo("DNI:");
		dni = sc.nextLine();
		
		if(!dni.isEmpty() && !dni.equals("")) {
			ret = (Cliente) cc.buscarPordni(dni);
		} else {
			cv.echo("[ERROR] El DNI no puede estar vacio");
		}
		return ret;		
	}
	
	public static int nuevo () {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int ret = 0; // valor de retorno por defecto
		Persona p;
		String nombre;
		String dni;
		String edad;
		ClienteVista cv = new ClienteVista();
		ClienteControlador cc = new ClienteControlador(SECCION+".txt");
		p = new Cliente();		
		cv.echo("DNI:");
		dni = sc.nextLine();
		if(!dni.isEmpty() && !dni.equals("")) {	
			p.setDni(dni);
			
			cv.echo("Nombre:");
			nombre = sc.nextLine();
			if(!nombre.isEmpty() && !nombre.equals("")) {
				p.setNombreCompleto(nombre);
			}
			
			cv.echo("Edad:");
			edad = sc.nextLine();
			if(!edad.isEmpty() && !edad.equals("")) {
				p.setEdad(Integer.parseInt(edad));
			}
			
			// creamos el nuevo cliente y seteamos ret para que devuelva su id
			cc.nuevo(p);
			ret = p.getId();
			cv.echo("Cliente creado correctamente");	
		} else {
			cv.echo("[ERROR] El DNI no puede estar vacio");	
			ret = -1;
		}		
		
		return ret;
	}
	
	public static void editar() {
		ClienteVista cv = new ClienteVista();		
		ClienteControlador cc = new ClienteControlador(SECCION+".txt");
		cv.echo("Inserte el DNI del cliente a editar:");
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		String dni = sc1.next();
		Persona p;
		String nombre;
		String edad;
		p = cc.buscarPordni(dni);
		
		if(p == null) {
			cv.echo("[ERROR] No se ha encontrado al cliente con el DNI: "+ dni);
		} else {
			cv.echo("los datos actuales son:");
			cv.echo(cv.menuLista);
			cv.echo(p.toString());
			cv.echo("Editar Nombre:");
			@SuppressWarnings("resource")
			Scanner sc2 = new Scanner(System.in);
			nombre = sc2.nextLine();
			if(!nombre.isEmpty() && !nombre.equals("")) {
				p.setNombreCompleto(nombre);
			}
			
			cv.echo("Editar DNI:");
			dni = sc2.nextLine();
			if(!dni.isEmpty() && !dni.equals("")) {
				p.setDni(dni);
			}
			
			cv.echo("Editar Edad:");
			edad = sc2.nextLine();
			if(!edad.isEmpty() && !edad.equals("")) {
				p.setEdad(Integer.parseInt(edad));
			}
			
			cc.editar(p);
			cv.echo("\nLos nuevos datos son:");
			cv.echo(cv.menuLista);
			cv.echo(p);
		}
	}
	
}
