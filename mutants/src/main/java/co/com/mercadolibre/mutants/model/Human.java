package co.com.mercadolibre.mutants.model;

import org.springframework.data.annotation.Id;

//@Document(collection = "dna")
public class Human {

	@Id
	private String id;
	private String dna[];
	
	
	
	public Human(String id, String[] dna) {
		super();
		this.id = id;
		this.dna = dna;
	}
	
	public Human() {
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
