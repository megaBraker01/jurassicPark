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
		return "\n[ID] [IDCLIENTE] [NOMBRE] [ESVIP?] [TIPO] [TEMPORADA] [PRECIO] [DESCUENTO] [PRECIO FINAL] [FECHA COMPRA]";
	}
	
	/**
	 * 
	 * @return
	 */
	public static int nuevo() {	
		
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		ClienteControlador clienteController = new ClienteControlador("clientes.txt");
		EntradaVista ev = new EntradaVista();
		int ret = 0;
		Scanner sc = new Scanner(System.in);
		
		ev.echo("Indique el DNI del cliente");
		Cliente cli = (Cliente) ClienteVista.buscar();
		int idCliente = 0;
		if(cli != null) {
			ev.echo("Ya existe un cliente con el DNI indicado");
			ev.echo(cli);
			ev.echo("Desea usar estos datos para la entrada? (si, no)");
			String respuesta = sc.nextLine().toLowerCase();
			switch(respuesta) {
				case "si":
				case "s":
					idCliente = cli.getId();
					break;
				case "no":
				case "n":
					idCliente = ClienteVista.nuevo();
					break;
				default:
					idCliente = cli.getId();
					break;
			}
		} else {
			ev.echo("NO existe cliente con el DNI indicado, vamos a crearlo");
			idCliente = ClienteVista.nuevo();
		}
		
		if(-1 == idCliente) {
			ret = idCliente;
		} else {
			cli = (Cliente) clienteController.buscarPorId(idCliente);
			
			ev.echo("Indique el tipo de entrada (1 = general, 2 = dia laborable (lunes a jueves), 3 = tarde (16h en adelante),  4 = familiar)");
			String entrada = sc.nextLine();
			int tipoEntrada = (entrada.equals("")) ? 1 :  Integer.parseInt(entrada);
			
			ev.echo("Es cliente VIP? (si, no)");
			boolean esVIP = "si".equals(sc.nextLine().toLowerCase());
			
			ev.echo("Indique la temporada (1 = baja, 2 = media, 3 = alta)");
			String temp = sc.nextLine().trim();
			int temporada = (temp.equals("")) ? 2 : Integer.parseInt(temp);
			
			Entrada e = ec.validador(cli, esVIP, tipoEntrada, temporada);
			Date fecha = new Date();
			DateFormat fechaCompra = new SimpleDateFormat("dd-MM-yyyy");
			e.setFechaCompra(fechaCompra.format(fecha));
			ec.nuevo(e);
			ev.echo("Entrada insertada correctamente");
			ev.echo(ev.mostrarMenuLista());
			ev.echo(e);
			ret = 1;
		}
		
		return ret;
	}

}
