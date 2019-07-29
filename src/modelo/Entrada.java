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
	 * indica el tipo de entrada: (por defecto 1 > general)
	 * 1 = general
	 * 2 = dia laborable (lunes a jueves)
	 * 3 = tarde (16h en adelante)
	 * 4 = familiar
	 * 5 = otras
	 */
	public static final int TIPO_GENERAL = 1, TIPO_LABORABLE = 2, TIPO_TARDE = 3, TIPO_FAMILIAR = 4, TIPO_OTRAS = 5;
	private int tipo;
	
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
	
	public Entrada(int idCliente, String fechaCompra) {
		setIdCliente(idCliente);
		setFechaCompra(fechaCompra);
	}
	
	public Entrada(int idCliente, int tipo, double precio, String fechaCompra, int descuento, boolean isVIP) {
		setIdCliente(idCliente);
		setTipo(tipo);
		setPrecio(precio);
		setFechaCompra(fechaCompra);
		setDescuento(descuento);
		setVip(isVIP);		
	}
	
	public Entrada(int id, int idCliente, int tipo, double precio, String fechaCompra, int descuento, boolean isVIP) {
		setId(id);
		setIdCliente(idCliente);
		setTipo(tipo);
		setPrecio(precio);
		setFechaCompra(fechaCompra);
		setDescuento(descuento);
		setVip(isVIP);		
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
	
	public String toString() {
		return getId()+", "+getIdCliente()+", "+getTipo()+", "+getPrecio()+", "+getFechaCompra()+", "+getDescuento()+", "+isVip();
	}

}
