package vista;


import java.util.Iterator;
import java.util.Scanner;

//import controlador.ClienteControlador;
import controlador.PersonaControlador;
import modelo.Persona;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class PersonaVista extends Herramientas {

	public PersonaVista() {
	}
	
	public static boolean main(String nombreSeccion) {
		PersonaVista cv = new PersonaVista();		
		PersonaControlador cc = new PersonaControlador(nombreSeccion+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-["+nombreSeccion.toUpperCase()+"]-");		
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
					if(iterador.hasNext()) {
						cv.echo("\n[ID] [NOMBRE] [EDAD] [DNI]");
						while(iterador.hasNext()) {
							cv.echo(iterador.next().toString());
						}
					}					
					break;
					
				case "new":
					
					Scanner sc = new Scanner(System.in);
					p = new Persona();
					
					cv.echo("Nombre:");
					nombre = sc.nextLine();
					if(!nombre.isEmpty() && !nombre.equals("")) {
						p.setNombreCompleto(nombre);
					}
					
					cv.echo("DNI:");
					dni = sc.nextLine();
					if(!dni.isEmpty() && !dni.equals("")) {
						Persona p2 = new Persona();
						p2 = cc.buscarPordni(dni);						
						if(p2 != null) {
							cv.echo("[ADVERTENCIA!!] ya existe un registro con este DNI");
							cv.echo(p2);
							break;
						} else {
							p.setDni(dni);
						}
					}
					
					cv.echo("Edad:");
					edad = sc.nextLine();
					if(!edad.isEmpty() && !edad.equals("")) {
						p.setEdad(Integer.parseInt(edad));
					}
					
					cc.nuevo(p);
					cv.echo(nombreSeccion.toUpperCase()+" insertado correctamente");
					break;
					
				case "edit":
					
					cv.echo("Inserte el DNI del "+nombreSeccion.toUpperCase()+" a editar:");
					Scanner sc1 = new Scanner(System.in);
					dni = sc1.next();
					p = cc.buscarPordni(dni);
					
					if(p != null) {
						cv.echo("los datos actuales son:");
						cv.echo("[ID] [NOMBRE] [EDAD] [DNI]");
						cv.echo(p.toString());
						cv.echo("Editar Nombre:");
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
						cv.echo("[ID] [NOMBRE] [EDAD] [DNI]");
						cv.echo(p);
					} else {
						cv.echo("[ERROR] No se ha encontrado al "+nombreSeccion.toUpperCase()+" con el DNI: "+ dni);
					}
					
					break;
				case "find":
					break;
				case "back":
					
					cv.echo("Saliendo de la seccion ["+nombreSeccion.toUpperCase()+"]");
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
