package vista;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controlador.AtraccionControlador;
import controlador.InformeControlador;
import modelo.Atraccion;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class InformeVista extends Herramientas{

	public String menu = "Generar infome de Clientes (cli), Empleados (emp) o Atracciones (atr)";
	/**
	 * 
	 */
	public InformeVista() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String seccion = "informes";
		InformeVista av = new InformeVista();		
		InformeControlador ic = new InformeControlador(seccion+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			av.echo("\n-["+seccion.toUpperCase()+"]-");
			av.echo(av.menu);
			av.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "cli":
					InformeVista.cliente();
				break;
				
				case "emp":
					break;
					
				case "atr":
					
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

	}
	
	
	public static void cliente() {
		InformeVista iv = new InformeVista();
		iv.echo("-[INFORMES CLIENTES]-");
		iv.echo("Tipos de informes:");
		iv.echo("General (gen) | Por Año (year) | Por Año/Mes (month) | Por Año/Mes/Dia (day)");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			String opcion = scanner.next().toLowerCase();
			char opcionL = opcion.charAt(0);
			switch(opcionL) {
			case 'g':
				//continuar = false;
				break;
			case 'y':
				//continuar = false;
				break;
			case 'm':
				//continuar = false;
				break;
			case 'd':
				//continuar = false;
				break;
			}
		}
	}

}
