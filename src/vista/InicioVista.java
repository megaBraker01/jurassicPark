package vista;


import java.io.IOException;
import java.util.Scanner;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class InicioVista {

	public InicioVista() {
	}

	public static void main(String[] args) throws IOException {
		InicioVista m = new InicioVista();
		m.echo("Bienvenid@s a Jurassic Park");
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			m.echo("\n-[Seccion Principal]-");
			m.menu();
			m.echo("elige una opcion:"+(char)94);			
			String opcion = scanner.next().toLowerCase();		
			switch(opcion) {
				case "ent":
					EntradaVista.main(null);
					break;
				case "cli":
					ClienteVista.main(null);
					break;
				case "atr":
					AtraccionVista.main(null);
					break;
				case "emp":
					EmpleadoVista.main(null);
					/*m.echo("has elegido la opcion Empleados");
					continuar = false;*/
					break;
				case "inf":
					InformeVista.main(null);
					break;
				case "exit":
					m.echo("Se estan guardando los datos...");
					m.echo("Hasta la proxima!!.\n(^_^)/");
					continuar = false;
					break;
				default:
					m.echo("[ERROR] \""+opcion+"\" NO es una opcion valida \nIntenta ota vez:");				
					break;	
			}			
		}
		scanner.close();
	}
	
	/**
	 * Imprime el parametro de entrada,
	 * es una version corta de System.out.println();
	 * @param obj
	 */
	public void echo(Object obj) {
		System.out.println(obj);
	}
	
	/**
	 * Muestra el menu de la seccion principal
	 */
	public void menu() {
		echo("Entradas (ent) | Clientes (cli) | Atracctiones (atr) | Empleados (emp) | Informes (inf) | Salir (exit)");
	}	
	
}
