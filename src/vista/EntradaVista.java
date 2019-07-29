package vista;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import controlador.EntradaControlador;
import modelo.Entrada;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EntradaVista extends Herramientas {

	public EntradaVista() {
	}

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {
		EntradaVista cv = new EntradaVista();		
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			cv.echo("\n-[Entradas]-");		
			cv.crudMenu();
			cv.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			Entrada e;
			switch(opcion) {
				case "list":
					
					Iterator<Entrada> iterador = ec.lista().iterator();
					cv.echo("\n[ID] [IDCLIENTE] [TIPO] [PRECIO] [FECHA DE LA COMPRA] [DESCUENTO] [ESVIP?]");
					while(iterador.hasNext()) {
						cv.echo(iterador.next().toString());
					}
					break;
					
				case "new":
					
					//Scanner sc = new Scanner(System.in);
					e = new Entrada();
					
					// insertamos el cliente nuevo
					int idCliente = ClienteVista.nuevo();
					
					// si se crea el cliente, entonces creamos la entrada
					if(idCliente > 0) {
						e.setIdCliente(idCliente);
						e.setTipo(Entrada.TIPO_GENERAL);
						e.setPrecio(79);
						Date fecha = new Date();
						DateFormat fechaCompra = new SimpleDateFormat("dd/MM/yyyy");
						//String fechaCompra = fecha.getDay()+"-"+fecha.getMonth()+"-"+fecha.getYear();
						e.setFechaCompra(fechaCompra.format(fecha));
						e.setDescuento(1);
						e.setVip(true);
						
						// luego de setear todos los campos de la entrada, ahora la grabamos en su archivo correspondiente
						ec.nuevo(e);
					}
					break;
					
				case "edit":
					break;
				case "back":
					
					cv.echo("Saliendo de la seccion [Entrada]");
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
