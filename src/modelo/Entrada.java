package modelo;


/**
 * @author Rafael Perez Sanchez
 *
 */
public class Entrada {
	
	private int id;
	
	/**
	 * indica el id del cliente al 
	 * que le pertenece la entra
	 */
	private int idCliente;
	
	/**
	 * indica el nombre completo del cliente
	 */
	private String nombreCliente;
	
	/**
	 * indica el tipo de entrada: (por defecto 1 -> general)
	 * 1 = general
	 * 2 = dia laborable (lunes a jueves)
	 * 3 = tarde (16h en adelante)
	 * 4 = familiar
	 * 5 = otras
	 */
	public static final int TIPO_GENERAL = 1, TIPO_LABORABLE = 2, TIPO_TARDE = 3, TIPO_FAMILIAR = 4, TIPO_OTRAS = 5;
	private int tipo = 1;
	
	/**
	 * indica la temporada de la entrada: (por defecto 2 -> tempodara media)
	 * 1 = temporada baja
	 * 2 = temporada media
	 * 3 = temporada alta
	 */
	public static final int TEMP_BAJA = 1, TEMP_MEDIA = 2, TEMP_ALTA = 3;
	private int temporada = 2;
	
	/**
	 * es el precio base de la entrada (en Euros)
	 */
	private double precio = 60;
	
	/**
	 * fecha en la que fue comprada
	 */
	private String fechaCompra;
	
	/**
	 * descuento que se le aplica
	 */
	private int descuento = 0;
		
	/**
	 * indica si tiene el suplemento VIP
	 */
	private boolean vip = false;
	
	public Entrada() {}
	
	
	public Entrada(int id, int idCliente, String nombreCliente, boolean isVIP, int tipo, int temporada, double precio, int descuento,  String fechaCompra) {
		setId(id);
		setIdCliente(idCliente);
		setNombreCliente(nombreCliente);
		setTipo(tipo);
		setPrecio(precio);
		setDescuento(descuento);
		setVip(isVIP);
		setFechaCompra(fechaCompra);
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getPrecioFianl() {
		return precio - ( (precio * descuento) /100);
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	
	public String toString() {
		return getId()+", "+getIdCliente()+", "+getNombreCliente()+", "+isVip()+", "+getTipo()+", "+getTemporada()+", "+getPrecio()+", "+getDescuento()+", "+getPrecioFianl()+", "+getFechaCompra();
	}

}
