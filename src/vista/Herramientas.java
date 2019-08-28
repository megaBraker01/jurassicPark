package vista;

import java.util.Scanner;


/**
 * @author Rafael Perez Sanchez
 *
 */
public abstract class Herramientas {
	public String menuLista = "[ID] [NOMBRE] [EDAD] [DNI]";
	
	public String crudMenu() {
		return "Listar (list) | Nuevo (new) | Editar (edit) | Buscar (find) | Atras (back)";
	}
	
	public String mostrarEncabezado() {
		return "\n[ID] [NOMBRE] [EDAD] [DNI]";
	}
	
	public String entrada() {
		try(Scanner scanner = new Scanner(System.in)){
			return scanner.next();
		}		
	}
	
	public void echo(Object obj) {
		System.out.println(obj);
	}
	
	public boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public String opcionElegida() {
		String salida = "has elegido la opcion ";		
		Scanner scanner = new Scanner(System.in);
		String ret = "";
		boolean continuar = true;		
		while(continuar) {			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "list":
					this.echo(salida+ "Listar");
					ret = opcion;
					continuar = false;
					break;
				case "new":
					this.echo(salida+ "Nuevo");
					ret = opcion;
					continuar = false;
					break;
				case "edit":
					this.echo(salida+ "Editar");
					ret = opcion;
					continuar = false;
					break;
					/*
				case "find":
					this.echo(salida+ "Buscar");
					ret = opcion;
					continuar = false;
					break;
					*/
				default:
					System.out.println("[ERROR] \""+opcion+"\" NO es una opcion valida \nElige ota vez:");				
					break;
			}
		}
		scanner.close();
		return ret;
	}
}