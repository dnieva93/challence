package co.com.mercadolibre.mutants.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;

import co.com.mercadolibre.mutants.dto.AdnDTO;
import co.com.mercadolibre.mutants.exception.AccesDeniedException;
import co.com.mercadolibre.mutants.exception.BusinessException;
import co.com.mercadolibre.mutants.service.MutantService;

@TestInstance(Lifecycle.PER_CLASS)
public class MutantControllerTest {
	
	private AdnDTO adnDto = new AdnDTO();
	private AdnDTO adnDtoErroneo = new AdnDTO();
	private Boolean esMutante = true;
	private Boolean esHumano = false;
	private JobExecution jobExecution = new JobExecution(1L);

	@InjectMocks
	private MutantController mutantController = new MutantController();
	
	@Mock
	MutantService mutantService;
	
	@Mock
	JobLauncher jobGrabarDnaLauncher;
	
	@Mock
	Job grabarBdJob;
	
	
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		String adn[] = {"ATGCGA","CAGTGC","ATGCGA","CAGTGC"};
		String adnLongitudErronea[] = {"ATG","CAG","ATG"};
		adnDto.setDna(adn);
		adnDtoErroneo.setDna(adnLongitudErronea);
		//JobExecution Job = new JobExecution);
		
	}
	
		
	@Test
	public void validarEsHumano() throws Exception {
		Mockito.when(mutantService.isMutant(adnDto,esMutante)).thenReturn(esHumano);
		Mockito.when(jobGrabarDnaLauncher.run(Mockito.any(), Mockito.any())).thenReturn(jobExecution);
		//String resultado= mutantController.create(adnDto);
		assertThrows(AccesDeniedException.class, 
				()->{ 
					mutantController.create(adnDto);
				});
	}
	
	@Test
	public void validarEsMutante() throws Exception {
		Mockito.when(mutantService.isMutant(Mockito.any(),Mockito.any())).thenReturn(esMutante);
		Mockito.when(jobGrabarDnaLauncher.run(Mockito.any(), Mockito.any())).thenReturn(jobExecution);
		String resultado= mutantController.create(adnDto);
		assertAll("Resultado", 
				()->{ 
					assertEquals(resultado, "");
				});
	}
	
	@Test
	public void validarEsMutanteBusinesLongitudErronea() throws Exception {
		//Boolean resultado= mutantService.saveAndReturn(adnDto,esHumano);
		assertThrows(BusinessException.class, 
				()->{ 
					mutantController.create(adnDtoErroneo);
				});
		//Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
	}

}
