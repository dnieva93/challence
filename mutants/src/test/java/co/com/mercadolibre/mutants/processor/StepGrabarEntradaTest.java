package co.com.mercadolibre.mutants.processor;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
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

@TestInstance(Lifecycle.PER_CLASS)
public class StepGrabarEntradaTest {
	
	
	private Boolean esMutante = true;
	private Stats stat = new Stats();
	private Dna dna = new Dna();
	private Human human = new Human();
	private Mutant mutant = new Mutant();
	StepContribution contribution;
	ChunkContext chunkContext;
	
	@InjectMocks
	private StepGrabarEntrada stepGrabarEntrada = new StepGrabarEntrada();
	
	@Mock
	DnaRepository dnaRepository;
	
	@Mock
	StatsRepository statRepository;
	
	@Mock
	HumanRepository humanRepository;
	
	@Mock
	MutantRepository mutantRepository;
	
	@BeforeAll
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		String adnRecibido[] = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		dna.setDna(adnRecibido);
		human.setDna(adnRecibido);
		mutant.setDna(adnRecibido);
		stat.setRatio(0.4);
		stat.setTotalHumanos(100L);
		stat.setTotalMutantes(40L);
		
		
	}
	
	@Test
	public void validarLlenarDatos() throws Exception {
		Mockito.when(mutantRepository.save(mutant)).thenReturn(mutant);
		Mockito.when(dnaRepository.save(dna)).thenReturn(dna);
		Mockito.when(humanRepository.save(human)).thenReturn(human);
		Mockito.when(mutantRepository.count()).thenReturn(40l);
		Mockito.when(humanRepository.count()).thenReturn(100l);
		MutantController.esMutante = esMutante;
		RepeatStatus resultado = stepGrabarEntrada.execute(contribution, chunkContext);
		assertAll("Resultado", 
				()->{ 
					assertEquals(resultado.FINISHED, RepeatStatus.FINISHED);
				});
	}
	
	@Test
	public void validarLlenarDatosHumano() throws Exception {
		Mockito.when(mutantRepository.save(mutant)).thenReturn(mutant);
		Mockito.when(dnaRepository.save(dna)).thenReturn(dna);
		Mockito.when(humanRepository.save(human)).thenReturn(human);
		Mockito.when(mutantRepository.count()).thenReturn(40l);
		Mockito.when(humanRepository.count()).thenReturn(100l);
		RepeatStatus resultado = stepGrabarEntrada.execute(contribution, chunkContext);
		assertAll("Resultado", 
				()->{ 
					assertEquals(resultado.FINISHED, RepeatStatus.FINISHED);
				});
	}

}
