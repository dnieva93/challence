package co.com.mercadolibre.mutants.dto;

public class StatsDto {
	
	private long count_mutant_dna;
	private long count_human_dna;
	private double ratio;
	
	
	public StatsDto(long count_mutant_dna, long count_human_dna, double ratio) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = ratio;
	}


	public StatsDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}


	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}


	public long getCount_human_dna() {
		return count_human_dna;
	}


	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}


	public double getRatio() {
		return ratio;
	}


	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	
	
}
