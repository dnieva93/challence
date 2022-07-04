package co.com.mercadolibre.mutants.util;

import java.util.ArrayList;

import co.com.mercadolibre.mutants.dto.PalabrasDTO;
import co.com.mercadolibre.mutants.exception.BusinessException;

public class Armador {
	

	public static PalabrasDTO palabras(String palabraHorizontal, String[] palabrasVerticales, ArrayList<String> palabrasDiagonal, int palabra) {
		//Este metodo Arma las palabras verticales con cada letra enviada desde las horizontales.
		//Tambien llama al metodo de armarDiagonales.
		String[] letras = palabraHorizontal.split("");
		PalabrasDTO palabras = new PalabrasDTO();
		for (int i = 0; i < letras.length; i++) {
			if(palabrasVerticales[i] == null) {
				validarLetra(letras[i]);
				palabrasVerticales[i] = letras[i];
				//palabrasDiagonal = mapearPalabraDiagonal(letras[i],palabrasDiagonal,palabra,i);
			}else {
				validarLetra(letras[i]);
				palabrasVerticales[i] = palabrasVerticales[i] + letras[i];
			}
		}
		palabrasDiagonal = mapearPalabrasDiagonales(letras, palabrasDiagonal, palabra);
		palabras.setDiagonales(palabrasDiagonal);
		palabras.setVerticales(palabrasVerticales);
		return palabras;
	}
	

	private static ArrayList<String> mapearPalabrasDiagonales(String[] letras, ArrayList<String> palabrasDiagonal,
			int posicion) {
		//Este metodo contiene la logica para armar las diagonales de una matriz N*N. Este es el ultimo metodo en ejecutarse
		//despues de analizar todas las horizontales.
		int tamanoMatriz = letras.length -1;
		int valorFilas = tamanoMatriz - 3;  
		int palabraNumero = posicion;
		int posicionAnterior = 0;
		int factorDiagonal = 2*(tamanoMatriz +1)-6;
		if (posicion == 0) {
			for (int i = 0; i < letras.length; i++) {
				if (i <= valorFilas ) {
					palabrasDiagonal.add(letras[i]);
					if(i != (tamanoMatriz - i) ) {
						palabrasDiagonal.add(letras[tamanoMatriz - i]);
					}else {
						break;
					}
				}else {
					break;
				}
			}
			
		}else if (posicion <= (valorFilas)) {
			for (int i = 0; i < palabrasDiagonal.size(); i +=2) {
				if (i > 5) {
					posicion = palabraNumero - 1;
				}
				palabrasDiagonal.add(i, (palabrasDiagonal.get(i) + letras[posicion]));
				palabrasDiagonal.remove(i+1);
				palabrasDiagonal.add(i + 1, (palabrasDiagonal.get(i+1) + letras[tamanoMatriz - posicion]));
				palabrasDiagonal.remove(i+2);
				posicion = posicion + 1;
			}
			palabrasDiagonal.add(letras[0]);
			palabrasDiagonal.add(letras[tamanoMatriz]);
		}else {
			for (int i = 0; i < palabrasDiagonal.size(); i +=2) {
				if (i == (factorDiagonal)) {
					posicion = palabraNumero - 1;
					posicionAnterior = posicion;
				}else if (i > factorDiagonal) {
					posicion = posicionAnterior - 1;
				}
				if (posicion <= tamanoMatriz) {
					palabrasDiagonal.add(i, (palabrasDiagonal.get(i) + letras[posicion]));
					palabrasDiagonal.remove(i+1);
					palabrasDiagonal.add(i + 1, (palabrasDiagonal.get(i+1) + letras[tamanoMatriz - posicion]));
					palabrasDiagonal.remove(i+2);
					posicion = posicion + 1;
				}
				
			}
		}
		
		return palabrasDiagonal;
		
	}
	
	
	private static void validarLetra(String letra) {
		//Metodo validador de letras permitidas en el adn.
		if (!letra.equals("A") && !letra.equals("G") && !letra.equals("T") && !letra.equals("C")) {
			throw new BusinessException("El adn solo puede contener las letras A,G,T,C...");
		}
		
	}

}
