package co.com.mercadolibre.mutants.processor;

import java.util.Arrays;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import co.com.mercadolibre.mutants.controller.MutantController;
import co.com.mercadolibre.mutants.model.Dna;
import co.com.mercadolibre.mutants.model.Human;
import co.com.mercadolibre.mutants.model.Mutant;
import co.com.mercadolibre.mutants.model.Stats;
import co.com.mercadolibre.mutants.repository.DnaRepository;
import co.com.mercadolibre.mutants.repository.HumanRepository;
import co.com.mercadolibre.mutants.repository.MutantRepository;
import co.com.mercadolibre.mutants.repository.StatsRepository;


public class StepGrabarEntrada implements Tasklet {
	//Clase de tipo tarea que contiene la logica necesaria para grabar la base de datos mongo.
	
	private DnaRepository dnaRepository;
	private MutantRepository mutantRepository;
	private HumanRepository humanRepository;
	private StatsRepository statsRepository;
	
	public StepGrabarEntrada(DnaRepository dnaRepository, MutantRepository mutantRepository, HumanRepository humanRepository, StatsRepository statsRepository) {
		//Se instancian los repository para que el job pueda usarlos.
		this.dnaRepository = dnaRepository;
		this.mutantRepository = mutantRepository;
		this.humanRepository = humanRepository;
		this.statsRepository = statsRepository;
	}

	public StepGrabarEntrada() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		//Metodo que ejecuta el job cuando inicia.
		String[] adnEnviado = MutantController.adnEnviado;
		Boolean esMutante = MutantController.esMutante;
		Mutant mutantBd = new Mutant();
		mutantBd.setDna(adnEnviado);
		Dna dnaBd = new Dna();
		dnaBd.setDna(adnEnviado);
		Human humanBd = new Human();
		humanBd.setDna(adnEnviado);
		System.out.println(esMutante);
		System.out.println(Arrays.toString(adnEnviado));
		dnaRepository.save(dnaBd);
		if (esMutante) {
			mutantRepository.save(mutantBd);
		}else {
			humanRepository.save(humanBd);
		}
		LlenarStats();
		return RepeatStatus.FINISHED;
	}

	private void LlenarStats() {
		//Metodo que llena la tabla de estadisticas.
		Stats stat = new Stats();
		Double ratio =0.0;
		Long totalMutantes = mutantRepository.count();
		Long totalHumanos = humanRepository.count();
		statsRepository.deleteAll();
		if(totalHumanos != 0) {		
			ratio = totalMutantes.doubleValue()/totalHumanos.doubleValue();
			System.out.println("Ratio: " + ratio);
		}
		stat.setTotalMutantes(totalMutantes);
		stat.setTotalHumanos(totalHumanos);
		stat.setRatio(ratio);
		statsRepository.save(stat);
		
		
	}
	

}