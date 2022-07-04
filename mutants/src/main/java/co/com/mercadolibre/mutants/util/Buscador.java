package co.com.mercadolibre.mutants.util;

public class Buscador {
	
	private Buscador(){		
	}
	
	
	public static Boolean buscarSecuencia(String palabra) {
		//Se construye este metodo el cual utiliza la funcion contains, la cual permite buscar un contenido dentro de un 
		//string, con este se busca las secuencias del adn
		String secuencia1 = "AAAA";
		String secuencia2 = "CCCC";
		String secuencia3 = "GGGG";
		String secuencia4 = "TTTT";
		Boolean encontrado = true;
		Boolean noEncontrado = false;
		
		if (palabra.contains(secuencia1) == encontrado || palabra.contains(secuencia2) == encontrado || 
				palabra.contains(secuencia3)== encontrado || palabra.contains(secuencia4) == encontrado) {
			return encontrado;
		}else {
			return noEncontrado;
		}
	}
		
	
}
