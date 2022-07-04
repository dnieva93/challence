package co.com.mercadolibre.mutants.model;

import org.springframework.data.annotation.Id;

//@Document(collection = "dna")
public class Mutant {

	@Id
	private String id;
	private String dna[];
	
	
	
	public Mutant(String id, String[] dna) {
		super();
		this.id = id;
		this.dna = dna;
	}
	
	public Mutant() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getDna() {
		return dna;
	}
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	
}
