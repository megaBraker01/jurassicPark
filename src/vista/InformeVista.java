package vista;

import java.util.Scanner;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class InformeVista extends Herramientas{

	public String menu = "Generar infome de:\nEntradas (ent), Atracciones (atr), Salir (back)";
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
					
				case "atr":
					InformeVista.atracciones();
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
		iv.echo("-[INFORMES ENTRADAS]-");		
		EntradaVista.buscarPorFecha();
	}
	
	
	
	public static void atracciones() {
		InformeVista iv = new InformeVista();
		iv.echo("-[INFORMES ATRACCIONES]-");
		AtraccionVista.AtraccionesFuncionando();
	}

}
