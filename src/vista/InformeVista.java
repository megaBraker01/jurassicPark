package vista;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controlador.AtraccionControlador;
import controlador.EntradaControlador;
import controlador.InformeControlador;
import modelo.Atraccion;
import modelo.Entrada;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class InformeVista extends Herramientas{

	public String menu = "Generar infome de:\n Entradas (ent), Empleados (emp) o Atracciones (atr)";
	public static final String SECCION = "informes";
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
		InformeControlador ic = new InformeControlador(SECCION+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			av.echo("\n-["+seccion.toUpperCase()+"]-");
			av.echo(av.menu);
			av.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "ent":
					InformeVista.entradas();
				break;
				
				case "emp":
					break;
					
				case "atr":
					
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

	}
	
	
	public static void entradas() {
		InformeVista iv = new InformeVista();
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		iv.echo("-[INFORMES ENTRADAS]-");		
		EntradaVista.buscarPorFecha();
	}

}
