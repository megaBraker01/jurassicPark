package vista;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controlador.ClienteControlador;
import controlador.EntradaControlador;
import modelo.Cliente;
import modelo.Entrada;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EntradaVista extends Herramientas {
	
	public String menuLista = "[ID] [IDCLIENTE] [NOMBRE] [ESVIP?] [TIPO] [TEMPORADA] [PRECIO] [DESCUENTO] [PRECIO FINAL] [FECHA COMPRA]";
	
	public static final String SECCION = "entradas";

	public EntradaVista() {
	}

	/**
	 * @param args
	 */
	public static boolean main(String[] args) {
		String seccion = "entradas";
		EntradaVista ev = new EntradaVista();		
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;		
		while(continuar) {
			
			ev.echo("\n-["+SECCION.toUpperCase()+"]-");		
			ev.echo(ev.crudMenu());
			ev.echo("elige una opcion:_");
			
			String opcion = scanner.next().toLowerCase();
			switch(opcion) {
				case "list":
					EntradaVista.listar();
					break;
					
				case "new":
					EntradaVista.nuevo();
					break;
				case "edit":
					EntradaVista.edit();
					break;
				case "find":
					EntradaVista.buscar();
					break;
				case "back":
					
					ev.echo("Saliendo de la seccion ["+SECCION.toUpperCase()+"]");
					continuar = false;
					break;
					
				default:
					ev.echo("[ERROR] \""+opcion+"\" NO es una opcion valida \nIntenta ota vez:");				
					break;
			}
		}
		
		return false;
	}
	
	
	public static void listar() {
		EntradaControlador ec = new EntradaControlador("entradas.txt");
		EntradaVista ev = new EntradaVista();
		List<Entrada> lista = ec.lista();
		Iterator<Entrada> iterador = lista.iterator();
		int total = lista.size();
		ev.echo("Total de "+SECCION+": "+total);
		ev.echo(ev.menuLista);
		while(iterador.hasNext()) {
			ev.echo(iterador.next().toString());
		}
		ev.echo(ev.menuLista);
		ev.echo("Total de "+SECCION+": "+total);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static int nuevo() {	
		
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
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
			
			ev.echo("Indique el tipo de entrada (1 = general, 2 = dia laborable (lunes a jueves), 3 = tarde (16h en adelante), 4 = familiar)");
			String entrada = sc.nextLine();
			int tipoEntrada = (entrada.equals("")) ? 1 : Integer.parseInt(entrada);
			
			ev.echo("Es cliente VIP? (si, no)");
			String vip = sc.nextLine().toLowerCase();
			boolean esVIP = ("si".equals(vip) || "s".equals(vip));
			
			ev.echo("Indique la temporada (1 = baja, 2 = media, 3 = alta)");
			String temp = sc.nextLine().trim();
			int temporada = (temp.equals("")) ? 2 : Integer.parseInt(temp);
			
			Entrada e = ec.validador(cli, esVIP, tipoEntrada, temporada);
			Date fecha = new Date();
			DateFormat fechaCompra = new SimpleDateFormat("dd-MM-yyyy");
			e.setFechaCompra(fechaCompra.format(fecha));
			ec.nuevo(e);
			ev.echo("Entrada insertada correctamente");
			ev.echo(ev.menuLista);
			ev.echo(e);
			ret = 1;
		}
		
		return ret;
	}
	
	public static Entrada buscar() {
		Entrada Entrada = null;
		Scanner sc = new Scanner(System.in);
		EntradaVista h = new EntradaVista();
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		h.echo("Indique el ID de la entrada o del cliente");
		int id = sc.nextInt();
		Entrada = ec.buscarPorId(id);
		if(Entrada != null) {
			h.echo(h.menuLista);
			h.echo(Entrada);
		} else {
			h.echo("No se ha encontrado la entrada");
		}
		return Entrada;
	}
	
	
	public static void buscarPorAnio() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		Scanner sc = new Scanner(System.in);
		ev.echo("Indique el a\u00f1o (yyyy):");
		String anio = sc.nextLine().toLowerCase();
		List<Entrada> lista = ec.buscarPorAnio(anio);
		Iterator<Entrada> iterador = lista.iterator();
		int total = lista.size();
		ev.echo("Total de "+SECCION+": "+total);
		ev.echo(ev.menuLista);
		while(iterador.hasNext()) {
			ev.echo(iterador.next().toString());
		}
		ev.echo(ev.menuLista);
		ev.echo("Total de "+SECCION+": "+total);
	}
	
	
	public static void buscarPorAnioMes() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		Scanner sc = new Scanner(System.in);
		ev.echo("Indique el a\u00f1o (yyyy):");
		String anio = sc.nextLine().toLowerCase();
		ev.echo("Indique el mes (mm):");
		String mes = sc.nextLine().toLowerCase();
		List<Entrada> lista = ec.buscarPorAnioMes(anio, mes);
		Iterator<Entrada> iterador = lista.iterator();
		float totalAnual = (float) ec.getTotalEntradas();
		ev.echo("Total anual: "+totalAnual);
		int total = lista.size();
		ev.echo("Total de "+SECCION+": "+total);
		float porcentaje = ((total/totalAnual) * 100);
		ev.echo("Porcentaje respecto al Total: "+ ev.numFormat(porcentaje) +"%");
		ev.echo(ev.menuLista);
		while(iterador.hasNext()) {
			ev.echo(iterador.next().toString());
		}
		ev.echo(ev.menuLista);
		ev.echo("Total de "+SECCION+": "+total);
	}
	
	
	public static void buscarPorAnioMesDia() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		Scanner sc = new Scanner(System.in);
		int totalAnual = ec.getTotalEntradas();
		ev.echo("Indique el a\u00f1o (yyyy):");
		String anio = sc.nextLine().toLowerCase();
		ev.echo("Indique el mes (mm):");
		String mes = sc.nextLine().toLowerCase();
		ev.echo("Indique el dia (dd):");
		String dia = sc.nextLine().toLowerCase();
		List<Entrada> lista = ec.buscarPorAnioMesDia(anio, mes, dia);
		Iterator<Entrada> iterador = lista.iterator();
		int total = lista.size();
		ev.echo("Total anual: "+totalAnual);
		ev.echo("Total de "+SECCION+": "+total);
		ev.echo(ev.menuLista);
		while(iterador.hasNext()) {
			ev.echo(iterador.next().toString());
		}
		ev.echo(ev.menuLista);
		ev.echo("Total de "+SECCION+": "+total);
	}
	
	
	public static Entrada edit() {
		Entrada e = EntradaVista.buscar();
		if(null != e) {
			EntradaControlador ec = new EntradaControlador(SECCION+".txt");
			ClienteControlador clienteController = new ClienteControlador("clientes.txt");
			EntradaVista ev = new EntradaVista();
			Scanner sc = new Scanner(System.in);
			ev.echo("Indique el tipo de entrada (1 = general, 2 = dia laborable (lunes a jueves), 3 = tarde (16h en adelante), 4 = familiar)");
			String entrada = sc.nextLine();
			int tipoEntrada = (entrada.equals("")) ? 1 : Integer.parseInt(entrada);
			
			ev.echo("Es cliente VIP? (si, no)");
			String vip = sc.nextLine().toLowerCase();
			boolean esVIP = ("si".equals(vip) || "s".equals(vip));
			
			ev.echo("Indique la temporada (1 = baja, 2 = media, 3 = alta)");
			String temp = sc.nextLine().trim();
			int temporada = (temp.equals("")) ? 2 : Integer.parseInt(temp);
			Cliente cli = (Cliente) clienteController.buscarPorId(e.getIdCliente());
			String fechaOriginal = e.getFechaCompra();
			int idOriginal = e.getId();
			e = ec.validador(cli, esVIP, tipoEntrada, temporada);
			e.setId(idOriginal);
			e.setFechaCompra(fechaOriginal);
			ec.editar(e);
			ev.echo("Entrada editada correctamente");
			ev.echo(ev.menuLista);
			ev.echo(e);
		}
		
		return e;
	}

}
