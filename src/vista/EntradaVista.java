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
		EntradaVista ev = new EntradaVista();
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
	
	/**
	 * Lista todas las entradas generadas
	 */
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
	 * te permite crear una nueva entrada
	 * @return int
	 */
	public static int nuevo() {	
		
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		ClienteControlador clienteController = new ClienteControlador("clientes.txt");
		EntradaVista ev = new EntradaVista();
		int ret = 0;
		@SuppressWarnings("resource")
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
	
	/**
	 * busca una entrada por su ID o por el ID del cliente
	 * @return Entrada
	 */
	public static Entrada buscar() {
		Entrada Entrada = null;
		@SuppressWarnings("resource")
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
	
	/**
	 * te permite buscar entradas por a�o, mes , dia 
	 * y te genera un informe con la informacion obtenida
	 */
	public static void buscarPorFecha() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Date fecha = new Date();
		String tipoFiltro = "";		
		ev.echo("Indique el a\u00f1o (yyyy):");
		String anio = sc.nextLine().toLowerCase();
		DateFormat fechaActual = new SimpleDateFormat("dd-MM-yyyy");
		String[] datosFechaActual = fechaActual.format(fecha).split("-");
		anio = (anio.equals("")) ? datosFechaActual[2] : anio;
		List<Entrada> entradasAnuales = ec.buscarPorAnio(anio);
		int totalAnual = entradasAnuales.size();
		ev.echo("Indique el mes (mm):");
		String mes = sc.nextLine().toLowerCase();
		ev.echo("Indique el dia (dd):");
		String dia = sc.nextLine().toLowerCase();
		List<Entrada> lista = null;
		if(!anio.equals("") && !mes.equals("") && !dia.equals("")) {
			lista = ec.buscarPorAnioMesDia(anio, mes, dia);
			tipoFiltro = "a\u00f1o "+anio+"/ mes "+mes+"/ dia"+dia;
		} else if (!anio.equals("") && !mes.equals("")){
			lista = ec.buscarPorAnioMes(anio, mes);
			tipoFiltro = "a\u00f1o "+anio+"/ mes "+mes;
		} else if (!anio.equals("")) {
			lista = ec.buscarPorAnio(anio);
			tipoFiltro = "a\u00f1o "+anio;
		}
		
		String mostrarRegistros = "";
		String info = "";
		double TotalRecaudado = 0;
		double precioMedio = 0;
		Iterator<Entrada> iterador = lista.iterator();
		Entrada entrada;
		while(iterador.hasNext()) {
			entrada = iterador.next();
			mostrarRegistros += entrada.toString()+"\n";
			TotalRecaudado += entrada.getPrecioFianl();
		}
		
		
		int totalFiltro = lista.size();
		precioMedio = TotalRecaudado / totalFiltro;
		float porcentaje = (((float)totalFiltro/totalAnual) * 100);
		info += "Total de "+SECCION+" anual: "+totalAnual+"\n";
		info +=("Total de "+SECCION+" por "+tipoFiltro+": "+totalFiltro+"\n");
		info +=("Total recaudado: "+TotalRecaudado+"\u20AC"+"\n");
		info +=("El precio medio de una entrada en ese periodo es de: "+ev.numFormat(precioMedio)+"\u20AC"+"\n");
		info +=("El total de entradas por "+tipoFiltro+" es un " +ev.numFormat(porcentaje)+ "% del total anual"+"\n");
		
		ev.echo(info);
		ev.echo(ev.menuLista);
		ev.echo(mostrarRegistros);
		ev.echo(ev.menuLista);
		ev.echo(info);
	}
	
	/**
	 * busca las entradas y las filtra por a�o indicado
	 */
	public static void buscarPorAnio() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		@SuppressWarnings("resource")
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
	
	/**
	 * busca las entradas y las filtra por a�o y mes indicado
	 */
	public static void buscarPorAnioMes() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		@SuppressWarnings("resource")
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
	
	/**
	 * busca las entradas y las filtra por a�o, mes y d�a indicado
	 */
	public static void buscarPorAnioMesDia() {
		EntradaControlador ec = new EntradaControlador(SECCION+".txt");
		EntradaVista ev = new EntradaVista();
		@SuppressWarnings("resource")
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
	
	/**
	 * edita los datos de una entrada y lo hace persistente
	 * @return Entrada
	 */
	public static Entrada edit() {
		Entrada e = EntradaVista.buscar();
		if(null != e) {
			EntradaControlador ec = new EntradaControlador(SECCION+".txt");
			ClienteControlador clienteController = new ClienteControlador("clientes.txt");
			EntradaVista ev = new EntradaVista();
			@SuppressWarnings("resource")
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
