package co.com.mercadolibre.mutants.model;

import org.springframework.data.annotation.Id;

//@Document(collection = "dna")
public class Dna {

	@Id
	private String id;
	private String dna[];
	
	
	
	public Dna(String id, String[] dna) {
		super();
		this.id = id;
		this.dna = dna;
	}
	
	public Dna() {
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
