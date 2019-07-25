package vista;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controlador.EmpleadoControlador;
import modelo.Cliente;
import modelo.Persona;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EmpleadoVista extends Herramientas {

	public EmpleadoVista() {}
	
	public static boolean main(String[] args) {
		String seccion = "empleados";
		EmpleadoVista cv = new EmpleadoVista();		
		EmpleadoControlador cc = new EmpleadoControlador(seccion+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-["+seccion.toUpperCase()+"]-");		
			cv.crudMenu();
			cv.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			Persona p;
			String nombre;
			String dni;
			String edad;
			switch(opcion) {
				case "list":
					List<Persona> lista = cc.lista();
					int total = lista.size();
					Iterator<Persona> iterador = lista.iterator();
					cv.echo("Total de "+seccion+": "+total);
					cv.echo(cv.mostrarEncabezado());
					while(iterador.hasNext()) {
						cv.echo(iterador.next().toString());
					}
					cv.echo("Total de "+seccion+": "+total);
					break;
					
				case "new":
					
					Scanner sc = new Scanner(System.in);
					p = new Cliente();
					
					cv.echo("Nombre:");
					nombre = sc.nextLine();
					if(!nombre.isEmpty() && !nombre.equals("")) {
						p.setNombreCompleto(nombre);
					}
					
					cv.echo("DNI:");
					dni = sc.nextLine();
					if(!dni.isEmpty() && !dni.equals("")) {
						Persona p2 = new Cliente();
						p2 = cc.buscarPordni(dni);						
						if(p2 != null) {
							cv.echo("[ADVERTENCIA!!] ya existe un registro con este DNI");
							cv.echo(p2);
							break;
						} else {
							p.setDni(dni);
						}
					} else {
						cv.echo("[ERROR] El DNI no puede estar vacio");
						break;
					}
					
					cv.echo("Edad:");
					edad = sc.nextLine();
					if(!edad.isEmpty() && !edad.equals("")) {
						p.setEdad(Integer.parseInt(edad));
					}
					
					cc.nuevo(p);
					cv.echo("Empleado insertado correctamente");
					break;
					
				case "edit":
					
					cv.echo("Inserte el DNI del empleado a editar:");
					Scanner sc1 = new Scanner(System.in);
					dni = sc1.next();
					p = cc.buscarPordni(dni);
					
					if(p == null) {
						cv.echo("[ERROR] No se ha encontrado al empleado con el DNI: "+ dni);
					} else {
						cv.echo("los datos actuales son:");
						cv.echo(cv.mostrarEncabezado());
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
						cv.echo(cv.mostrarEncabezado());
						cv.echo(p);
					}
					
					break;
				case "find": /* de momento no hace nada*/
					break;
				case "back":
					
					cv.echo("Saliendo de la seccion ["+seccion.toUpperCase()+"]");
					continuar = false;
					break;
					
				default:
					cv.echo("[ERROR] "+opcion+" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}
}
