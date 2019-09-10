package vista;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import controlador.EmpleadoControlador;
import modelo.Empleado;
import modelo.Persona;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EmpleadoVista extends Herramientas {
	
	public static final String SECCION = "empleados";
	public String menuLista = super.menuLista + " [GRUPO]";

	public EmpleadoVista() {}
	
	public static boolean main(String[] args) {
		EmpleadoVista cv = new EmpleadoVista();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Empleado emp;
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-["+SECCION.toUpperCase()+"]-");		
			cv.echo(cv.crudMenu());
			cv.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "list":
					
					EmpleadoVista.lista();
					break;
					
				case "new":
					
					cv.echo("indique el DNI del empleado");
					emp = EmpleadoVista.buscar();
					if (emp != null) {
						cv.echo("Ya existe un empleado con el DNI indicado");
						cv.echo(emp);
					} else {
						cv.echo("NO existe empleado con el DNI indicado, vamos a crearlo");
						EmpleadoVista.nuevo();
					}
					break;
					
				case "edit":					
					EmpleadoVista.editar();
					
					break;
				case "find":
					EmpleadoVista.find();
					
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
		EmpleadoVista cv = new EmpleadoVista();		
		EmpleadoControlador cc = new EmpleadoControlador(SECCION+".txt");
		List<Persona> lista = cc.lista();
		int total = lista.size();
		Iterator<Persona> iterador = lista.iterator();
		cv.echo("Total de "+SECCION+": "+total);
		cv.echo(cv.menuLista);
		while(iterador.hasNext()) {
			cv.echo(iterador.next().toString());
		}
		cv.echo(cv.menuLista);
		cv.echo("Total de "+SECCION+": "+total);
	}
	
	public static Empleado buscar () {
		Empleado ret = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		EmpleadoControlador cc = new EmpleadoControlador(SECCION+".txt");
		EmpleadoVista cv = new EmpleadoVista();
		String dni;
		cv.echo("DNI:");
		dni = sc.nextLine();
		
		if(!dni.isEmpty() && !dni.equals("")) {
			ret = (Empleado) cc.buscarPordni(dni);
		} else {
			cv.echo("[ERROR] El DNI no puede estar vacio");
		}
		return ret;		
	}
	
	public static int nuevo () {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int ret = 0; // valor de retorno por defecto
		Empleado p;
		String nombre;
		String dni;
		String edad;
		String grupo;
		EmpleadoVista cv = new EmpleadoVista();
		EmpleadoControlador cc = new EmpleadoControlador(SECCION+".txt");
		p = new Empleado();		
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
			
			cv.echo("Grupo: (1 = Atencion al cliente, 2 = Relaciones publicas, 3 = Responsable de atraccion, 4 = Ayudante de atraccion)");
			grupo = sc.nextLine();
			if(!grupo.isEmpty() && !grupo.equals("")) {
				p.setGrupo(Integer.parseInt(grupo));
			}

			cc.nuevo(p);
			ret = p.getId();
			cv.echo("Empleado creado correctamente");	
		} else {
			cv.echo("[ERROR] El DNI no puede estar vacio");	
			ret = -1;
		}		
		
		return ret;
	}
	
	public static void find() {
		EmpleadoVista cv = new EmpleadoVista();	
		Empleado emp;
		cv.echo("indique el DNI del empleado");
		emp = EmpleadoVista.buscar();
		if (emp != null) {
			cv.echo("Ya existe un empleado con el DNI indicado");
			cv.echo(cv.menuLista);
			cv.echo(emp);
		} else {
			cv.echo("NO existe empleado con el DNI indicado");
		}
	}
	
	public static void editar() {
		EmpleadoVista cv = new EmpleadoVista();		
		EmpleadoControlador cc = new EmpleadoControlador(SECCION+".txt");
		cv.echo("Inserte el DNI del empleado a editar:");
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		String dni = sc1.next();
		Persona p = cc.buscarPordni(dni);
		
		if(p == null) {
			cv.echo("[ERROR] No se ha encontrado al empleado con el DNI: "+ dni);
		} else {
			cv.echo("los datos actuales son:");
			cv.echo(cv.menuLista);
			cv.echo(p.toString());
			cv.echo("Editar Nombre:");
			@SuppressWarnings("resource")
			Scanner sc2 = new Scanner(System.in);
			String nombre = sc2.nextLine();
			if(!nombre.isEmpty() && !nombre.equals("")) {
				p.setNombreCompleto(nombre);
			}
			
			cv.echo("Editar DNI:");
			dni = sc2.nextLine();
			if(!dni.isEmpty() && !dni.equals("")) {
				p.setDni(dni);
			}
			
			cv.echo("Editar Edad:");
			String edad = sc2.nextLine();
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
