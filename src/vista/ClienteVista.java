package vista;


import java.util.Iterator;
import java.util.Scanner;

import controlador.ClienteControlador;
import modelo.Persona;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class ClienteVista extends Herramientas {

	public ClienteVista() {
	}
	
	public static boolean main(String[] args) {
		ClienteVista cv = new ClienteVista();		
		ClienteControlador cc = new ClienteControlador("clientes.txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-[Clientes]-");		
			cv.crudMenu();
			cv.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			Persona p;
			String nombre;
			String dni;
			String edad;
			switch(opcion) {
				case "list":
					
					Iterator<Persona> iterador = cc.lista().iterator();
					cv.echo("\n[ID] [NOMBRE] [EDAD] [DNI]");
					while(iterador.hasNext()) {
						cv.echo(iterador.next().toString());
					}
					break;
					
				case "new":
					
					Scanner sc = new Scanner(System.in);
					cv.echo("Nombre:");
					nombre = sc.nextLine();
					cv.echo("DNI:");
					dni = sc.next();
					cv.echo("Edad:");
					edad = sc.next();				
					p = new Persona(nombre, Integer.parseInt(edad), dni);
					cc.nuevo(p);
					cv.echo("Cliente insertado correctamente");
					break;
					
				case "edit":
					
					cv.echo("Inserte el DNI del cliente a editar:");
					Scanner sc1 = new Scanner(System.in);
					dni = sc1.next();
					p = cc.buscarPordni(dni);
					if(p != null) {
						cv.echo("los datos actuales son: \n"+p.toString() );
						cv.echo("Editar Nombre:");
						sc1.next();
						sc1.next();
						nombre = sc1.next();
						p.setNombreCompleto(nombre);
						cv.echo("Editar DNI:");
						dni = sc1.next();
						p.setDni(dni);
						cv.echo("Editar Edad:");
						edad = sc1.next();
						p.setEdad(Integer.parseInt(edad));
						cc.editar(p);
					} else {
						cv.echo("[ERROR] No se ha encontrado al cliente con el DNI: "+ dni);
					}
					
					break;
				case "back":
					
					cv.echo("Saliendo de la seccion [Clientes]");
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