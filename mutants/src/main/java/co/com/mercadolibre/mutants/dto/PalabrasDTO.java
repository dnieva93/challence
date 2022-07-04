package co.com.mercadolibre.mutants.dto;

import java.util.ArrayList;

public class PalabrasDTO {
	
	String[] verticales;
	ArrayList<String> diagonales;
	
	public String[] getVerticales() {
		return verticales;
	}
	public void setVerticales(String[] verticales) {
		this.verticales = verticales;
	}
	public ArrayList<String> getDiagonales() {
		return diagonales;
	}
	public void setDiagonales(ArrayList<String> diagonales) {
		this.diagonales = diagonales;
	}
	
	

}
