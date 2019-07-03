package controlador;

import modelo.Entrada;

import java.text.SimpleDateFormat;
import java.util.*;

public class ProbandoControlador extends EntradaControlador {
	
	//public static final String ARCHIVO = "clientes.csv";
	//public String ARCHIVO = "clientes.txt";

	/**
	 * @param string
	 */
	public ProbandoControlador(String nombreArchivo) {
		super(nombreArchivo);
	}

	public static void main(String[] args) {
		/*
		ProbandoControlador pc = new ProbandoControlador("entradas.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		String fecha = sdf.format(new Date());
		Entrada ent = new Entrada(5, fecha);
		//Entrada ent = new Entrada(3, 2, 80, fecha, 0, false);
		pc.nuevo(ent);
		
		for(Entrada entrada: pc.lista()) {
			System.out.println(entrada.toString());
			
		}
		*/
		/*
		String a = " String  ".trim();
		String b = " a";
		System.out.println(a+b);
		if("String" == a)
			System.out.println("igual");
		else
			System.out.println("no igual");
			
			*/
		
		
	}
	

}
