package co.com.mercadolibre.mutants.model;

import org.springframework.data.annotation.Id;


public class Stats {

	@Id
	private String id;
	private Long totalHumanos;
	private Long totalMutantes;
	private Double ratio;
	
	public Stats(String id, Long totalHumanos, Long totalMutantes, Double ratio) {
		super();
		this.id = id;
		this.totalHumanos = totalHumanos;
		this.totalMutantes = totalMutantes;
		this.ratio = ratio;
	}

	public Stats() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTotalHumanos() {
		return totalHumanos;
	}

	public void setTotalHumanos(Long totalHumanos) {
		this.totalHumanos = totalHumanos;
	}

	public Long getTotalMutantes() {
		return totalMutantes;
	}

	public void setTotalMutantes(Long totalMutantes) {
		this.totalMutantes = totalMutantes;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
	
	
	
	
	
	
	
}
