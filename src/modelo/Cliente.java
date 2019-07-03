
package modelo;

import interfaz.personaInterfaz;

/**
 * @author Rafael Perez Sanchez
 *
 */
public class Cliente extends Persona implements personaInterfaz {
	
    /**
     * tipos de visitantes
     */
    public static final int INDETERMINADO = 0, BEBE = 1, NINIO = 2, ADULTO = 3, SENIOR = 4;
    
    public Cliente(String nombreCompleto, int edad, String dni) {
        super(nombreCompleto, edad);
        this.setDni(dni);
        //this.setId(idSiguiente++);
    }
    
    public Cliente(int id, String nombreCompleto, int edad, String dni) {
        super(nombreCompleto, edad);
        this.setId(id);
        this.setDni(dni);
    }

    /**
     * retorna el tipo de visitante 
     * 0 = indeterminado
     * 1 = Bebe
     * 2 = Niño
     * 3 = Adulto
     * 4 = Senior
     */
    public int getTipoVisitante() {
        int tipo;
        if(this.edad < 0){
            tipo = INDETERMINADO;
        } else if(this.edad < 3) {
            tipo = BEBE;
        } else if(this.edad <= 12) {
            tipo = NINIO;
        } else if(this.edad <= 64) {
            tipo = ADULTO;
        } else {
            tipo = SENIOR;
        }
        return tipo;
    }
    
}


