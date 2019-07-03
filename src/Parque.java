import java.io.IOException;

import vista.InicioVista;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class Parque {
	public Parque() {
	}

	/**
	 * Se encarga de iniciar la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InicioVista.main(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
