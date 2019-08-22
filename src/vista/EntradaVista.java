package vista;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controlador.ClienteControlador;
import controlador.EntradaControlador;
import modelo.Cliente;
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
		String seccion = "entradas";
		EntradaVista ev = new EntradaVista();		
		EntradaControlador ec = new EntradaControlador(seccion+".txt");
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
					List<Entrada> lista = ec.lista();
					Iterator<Entrada> iterador = lista.iterator();
					int total = lista.size();
					ev.echo("Total de "+seccion+": "+total);
					ev.echo(ev.mostrarMenuLista());
					while(iterador.hasNext()) {
						ev.echo(iterador.next().toString());
					}
					ev.echo(ev.mostrarMenuLista());
					ev.echo("Total de "+seccion+": "+total);
					break;
					
				case "new":
					EntradaVista.nuevo();
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
	
	public String mostrarMenuLista() {
		return "\n[ID] [IDCLIENTE] [TIPO] [PRECIO] [DESCUENTO] [PRECIO FINAL] [ESVIP?] [FECHA DE LA COMPRA]";
	}
	
	public static int nuevo() {	
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		ClienteControlador clienteController = new ClienteControlador("clientes.txt");
		EntradaVista ev = new EntradaVista();
		int ret = 0;
		Scanner sc = new Scanner(System.in);
		Entrada e = new Entrada();
		ev.echo("Indique el DNI del cliente");
		Persona cli = ClienteVista.buscar();
		Date fecha = new Date();
		DateFormat fechaCompra = new SimpleDateFormat("dd-MM-yyyy");
		float precioEntrada = 60;
		int descuento = 0;
		if(cli != null) {
			ev.echo("Ya existe un cliente con el DNI indicado");
			ev.echo(cli);
			ev.echo("Desea usar estos datos para la entrada? (si, no)");
			String respuesta = sc.nextLine().toLowerCase();
			switch(respuesta) {
				case "si":
					e.setIdCliente(cli.getId());					
					break;
				case "no":
					int idCliente = ClienteVista.nuevo();
					cli = clienteController.buscarPorId(idCliente);
					e.setIdCliente(idCliente);
					break;
			}
		} else {
			ev.echo("NO existe cliente con el DNI indicado, vamos a crearlo");
			int idCliente = ClienteVista.nuevo();
			cli = clienteController.buscarPorId(idCliente);
			e.setIdCliente(idCliente);
		}
		
		ev.echo("Indique el tipo de entrada (1 = general, 2 = dia laborable (lunes a jueves), 3 = tarde (16h en adelante),  4 = familiar)");
		String tipoEntrada = sc.nextLine().toLowerCase();
		ev.echo("Es cliente VIP? (si, no)");
		String esVIP = sc.nextLine().toLowerCase();
		switch(tipoEntrada) {
			case "1":
				e.setTipo(Entrada.TIPO_GENERAL);
				break;
			case "2":
				e.setTipo(Entrada.TIPO_LABORABLE);
				/**
				 * este tipo de entrada es 10% menor que la entrada general
				 */
				precioEntrada = precioEntrada - ((precioEntrada * 10)/100);
				break;
			case "3":
				/**
				 * este tipo de entrada es 20% menor que la entrada general
				 */
				precioEntrada = precioEntrada - ((precioEntrada * 20)/100);
				e.setTipo(Entrada.TIPO_TARDE);
				break;
			case "4":
				/**
				 * este tipo de entrada es 8% menor que la entrada general
				 */
				precioEntrada = precioEntrada - ((precioEntrada * 8)/100);
				e.setTipo(Entrada.TIPO_FAMILIAR);
				break;
			default:
				e.setTipo(Entrada.TIPO_GENERAL);
				break;
		}
		
		/**
		 * si es VIP NO se le aplica descuento
		 */
		if(esVIP.equals("si")) {
			precioEntrada += 50;
			e.setVip(true);
		} else {
			if(cli.getEdad() < 18) {
				descuento += 50;
			} else {
				descuento += 35;
			}
		}
		e.setDescuento(descuento);
		e.setPrecio(precioEntrada);
		double precioF = precioEntrada + ((precioEntrada * descuento) / 100);
		e.setPrecioFinal(precioF);
		e.setFechaCompra(fechaCompra.format(fecha));
		ec.nuevo(e);
		ev.echo("Entrada insertada correctamente");
		ret = 1;
		
		return ret;
	}

}
