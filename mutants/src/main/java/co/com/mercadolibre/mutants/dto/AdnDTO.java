package co.com.mercadolibre.mutants.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public class AdnDTO {

	private String dna[];
	
	

	public AdnDTO() {
		super();
	}



	public String[] getDna() {
		return dna;
	}



	public void setDna(String dna[]) {
		this.dna = dna;
	}
	
	
}

