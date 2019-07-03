package paraPuebeas;

import modelo.*;
import java.io.*;

public class Pruebas {

	public Pruebas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {


		File archivo = new File("src/archivos");
		System.out.println(archivo.getAbsolutePath());
		
		/*
		Cliente cli1 = new Cliente("antonio", 31, null);
		System.out.println("el tipo de visitante es "+cli1.getTipoVisitante());
		System.out.println("su id es "+cli1.getId());
		
		Cliente cli2 = new Cliente("mario", 31, "034885699v");
		System.out.println("el tipo de visitante es "+cli2.getTipoVisitante());
		System.out.println("su id es "+cli2.getId());
		
		Cliente cli3 = new Cliente("nefer", 25, "96365986a");
		System.out.println("el tipo de visitante es "+cli3.getTipoVisitante());
		System.out.println("su id es "+cli3.getId());
		*/
	}

}
