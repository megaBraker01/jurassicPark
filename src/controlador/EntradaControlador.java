package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Entrada;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class EntradaControlador {

	public String ARCHIVO = this.getClass().getSimpleName()+".txt";
	
	public EntradaControlador(String nombreArchivo) {
		this.ARCHIVO = nombreArchivo;
	}
	
	/**
	 * lee el fichero especificado en la constante ARCHIVO
	 * y retorna una lista de objetos
	 * @return lista Entrada
	 */
	public List<Entrada> lista() {
		String archivo = ArchivoControlador.leer(ARCHIVO);
		String[] fila = archivo.split("\n");
		List<Entrada> lista = new ArrayList<>();
		if(fila.length > 0) {
			for(String registros : fila) {
				String[] campos = registros.split(", ");
				Entrada Entrada = new Entrada(
						Integer.parseInt(campos[0].trim()), 
						Integer.parseInt(campos[1].trim()),
						campos[2].trim(),
						Boolean.parseBoolean(campos[3]),
						Integer.parseInt(campos[4].trim()),
						Integer.parseInt(campos[5].trim()),
						Double.parseDouble(campos[6]),
						Integer.parseInt(campos[7].trim()),						
						campos[9]
								);
				lista.add(Entrada);
				
			}
		}		
		return lista;
	}
	
	
	/**
	 * inserta un nuevo Entrada al final fichero 
	 * especificado en la constante ARCHIVO
	 * @param Entrada
	 */
	public void nuevo(Entrada Entrada) {
		Entrada.setId(this.generarId());
		ArchivoControlador.editar(ARCHIVO, Entrada.toString());
	}
	
	
	/**
	 * lee el ultimo registro de fichero especificado en la constante ARCHIVO
	 * y retorna el ultimo id incrementandolo en uno (1)
	 * @return ultimoID
	 */
	private int generarId() {
		int ultimoID = 0;
		if(ArchivoControlador.existe(ARCHIVO)) {
			String archivo = ArchivoControlador.leer(ARCHIVO);
			String[] filas = archivo.split("\n");			
			if(filas.length > 0) {
				int ultimo = filas.length-1;
				String[] campoid = filas[ultimo].split(", ");
				ultimoID = (!campoid[0].isEmpty()) ? Integer.parseInt(campoid[0]) : 0 ;
			}
		}
		
    	return ++ultimoID;
    }
	
	/**
	 * se encarga de la logina de la entrada, valida los paramentros y calcula el precio, descuento y tipo de entrada
	 * @param cli
	 * @param esVIP
	 * @param tipoEntrada
	 * @return Entrada
	 */
	public Entrada validador(Cliente cli, boolean esVIP, int tipoEntrada, int temporada) {
		Entrada ent = new Entrada();
		double precioMinimo = ent.getPrecio();
		double precioEntrada =  ent.getPrecio(); //el precio por defecto de la entrada
		int descuento = 0;
		
		switch(temporada) {
			case Entrada.TEMP_ALTA:
				ent.setTemporada(Entrada.TEMP_ALTA);
				precioEntrada += ((precioEntrada * 15)/100);
				break;
			case Entrada.TEMP_BAJA:
				ent.setTemporada(Entrada.TEMP_BAJA);
				precioEntrada -= ((precioEntrada * 15)/100);
				break;
		}
		
		switch(cli.getTipoVisitante()) {
			case Cliente.NINIO:
				descuento += 50;
				break;
			case Cliente.SENIOR:
				descuento += 35;
				break;
		}
		
		precioMinimo = ((precioMinimo * descuento)/100);
		
		switch(tipoEntrada) {
			case Entrada.TIPO_LABORABLE:
				ent.setTipo(Entrada.TIPO_LABORABLE);
				/**
				 * este tipo de entrada es 10% menor que la entrada general
				 */
				precioEntrada = precioEntrada - ((precioEntrada * 10)/100);
				break;
			case Entrada.TIPO_TARDE:
				/**
				 * este tipo de entrada es 20% menor que la entrada general
				 */
				precioEntrada = precioEntrada - ((precioEntrada * 20)/100);
				ent.setTipo(Entrada.TIPO_TARDE);
				break;
			case Entrada.TIPO_FAMILIAR:
				/**
				 * este tipo de entrada es 8% menor que la entrada general
				 */
				precioEntrada = precioEntrada - ((precioEntrada * 8)/100);
				ent.setTipo(Entrada.TIPO_FAMILIAR);
				break;
			default:
				ent.setTipo(Entrada.TIPO_GENERAL);
				break;
		}
		
		precioEntrada = (esVIP) ?  precioEntrada + 50 : precioEntrada ;
		
		/**
		 * validamos que el precioFinal no sea inferior que el 10% del precioMinimo
		 */
		if(precioEntrada < ((precioMinimo * 10)/100)) {
			precioEntrada = precioMinimo;
		}
		
		ent.setDescuento(descuento);
		ent.setPrecio(precioEntrada);
		ent.setIdCliente(cli.getId());
		ent.setNombreCliente(cli.getNombreCompleto());
		ent.setVip(esVIP);		
		
		return ent;
	}


}
