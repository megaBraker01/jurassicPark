package vista;

import java.text.DecimalFormat;


/**
 * @author Rafael Perez Sanchez
 *
 */
public abstract class Herramientas {
	public String menuLista = "[ID] [NOMBRE] [EDAD] [DNI]";
	
	public String crudMenu() {
		return "Listar (list) | Nuevo (new) | Editar (edit) | Buscar (find) | Atras (back)";
	}
	
	/**
	 * metodo corto para imprimir
	 * @param obj
	 */
	public void echo(Object obj) {
		System.out.println(obj);
	}
	
	/**
	 * valida si una cadena se puede convertir a entero
	 * @param cadena
	 * @return
	 */
	public boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	/**
	 * formatear un numero a dos decimales
	 * @param num
	 * @return
	 */
	public String numFormat(Object num) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(num);
	}
}