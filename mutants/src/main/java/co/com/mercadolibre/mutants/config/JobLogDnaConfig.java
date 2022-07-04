package co.com.mercadolibre.mutants.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.mercadolibre.mutants.processor.StepGrabarEntrada;
import co.com.mercadolibre.mutants.repository.DnaRepository;
import co.com.mercadolibre.mutants.repository.HumanRepository;
import co.com.mercadolibre.mutants.repository.MutantRepository;
import co.com.mercadolibre.mutants.repository.StatsRepository;

@Configuration
@EnableBatchProcessing
public class JobLogDnaConfig {
	//Clase donde se configura el job a ejecutar.
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	  
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	DnaRepository dnaRepository;
	
	@Autowired
	MutantRepository mutantRepository;
	
	@Autowired
	StatsRepository statsRepository;
	
	@Autowired
	HumanRepository humanRepository;
	
	
	
	//Este Bean contiene el paso y la tarea que debe ejecutar el job.
	@Bean
	  public Step step1() {
	    
	     return stepBuilderFactory.get("paso1")
	    		 .tasklet(stepGrabarEntrada())
	    		 .build();
	  }
	
	//Este Bean crea el job.
	@Bean 
	Job GrabarBd() {
	    return jobBuilderFactory.get("GrabarBd")
	    	.start(step1())
	        .build();
	}
	
	//Este bean llama e instancia los repository que se encuentran en la tarea.
	@Bean
	public StepGrabarEntrada stepGrabarEntrada() {
		return new StepGrabarEntrada(dnaRepository, mutantRepository,humanRepository,statsRepository);
	}
	

}
