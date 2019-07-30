package vista;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import controlador.EntradaControlador;
import modelo.Entrada;
import modelo.Persona;

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
		EntradaVista ev = new EntradaVista();		
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			ev.echo("\n-[Entradas]-");		
			ev.crudMenu();
			ev.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			Entrada e;
			switch(opcion) {
				case "list":
					
					Iterator<Entrada> iterador = ec.lista().iterator();
					ev.echo("\n[ID] [IDCLIENTE] [TIPO] [PRECIO] [FECHA DE LA COMPRA] [DESCUENTO] [ESVIP?]");
					while(iterador.hasNext()) {
						ev.echo(iterador.next().toString());
					}
					break;
					
				case "new":
					EntradaVista.nuevo();
					/*
					Scanner sc = new Scanner(System.in);
					int ret = EntradaVista.nuevo();
					ev.echo("el resultado de ret es: "+ ret);
					if ( ret == -1 ) {
						ev.echo("quieres usar estos datos para la entrada? (si, no)");
						switch(sc.nextLine().toLowerCase()) {
						case "si":
							
						break;
						case "no":
							
						break;
						default:
							ev.echo("opcion NO valida");
						break;
						}
					}
					
					break;
					*/
				case "edit":
					break;
				case "back":
					
					ev.echo("Saliendo de la seccion [Entrada]");
					continuar = false;
					break;
					
				default:
					ev.echo("[ERROR] \""+opcion+"\" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}
	
	public static int nuevo() {	
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		EntradaVista ev = new EntradaVista();
		int ret = 0;
		Scanner sc = new Scanner(System.in);
		Entrada e = new Entrada();
		ev.echo("Indique el DNI del cliente");
		Persona cli = ClienteVista.buscar();
		if(cli != null) {
			ev.echo("Ya existe un cliente con el DNI indicado");
			ev.echo(cli);
			ev.echo("Desea usar estos datos para la entrada? (si, no)");
			String respuesta = sc.nextLine().toLowerCase();
			switch(respuesta) {
				case "si":
					e.setIdCliente(cli.getId());
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
					ret = 1;
					break;
				case "no":
					break;
			}
			
		}
		/*
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
			ret = 1;
			
		} else {
			ret = idCliente;
		}
		*/
		
		return ret;
	}

}
