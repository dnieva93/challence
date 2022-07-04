package co.com.mercadolibre.mutants.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.mercadolibre.mutants.dto.AdnDTO;
import co.com.mercadolibre.mutants.exception.BusinessException;
import co.com.mercadolibre.mutants.model.Dna;
import co.com.mercadolibre.mutants.model.Mutant;
import co.com.mercadolibre.mutants.repository.DnaRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class MutantServiceTest {
	
	Mutant mutant = new Mutant();
	Dna dna = new Dna();
	List<Dna> listDna = new ArrayList<>();
	AdnDTO adnDto = new AdnDTO();
	AdnDTO adnDtoMenor = new AdnDTO();
	AdnDTO adnDtoVerticales = new AdnDTO();
	AdnDTO adnDtoHorizontales = new AdnDTO();
	AdnDTO adnDtoDiagonales = new AdnDTO();
	AdnDTO adnDtoLetraErronea = new AdnDTO();
	Boolean esMutante = true;
	Boolean esHumano = false;
	List<Dna> listDnaVacio = new ArrayList<>();
	
	@InjectMocks
	private MutantService mutantService = new MutantService();
	
	
	@Mock
	DnaRepository dnaRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		//String adnVacio[] = {};
		String adn[] = {"ATGCGA","CAGTAC","ATTCGA","CAGTGC","ATGCGA","CAGTGC"};
		String adnMenor[] = {"ATG","CAG","ATT"};
		String adnVertical[] = {"ATGCGA","AAAAGC","ATATGT","AGAAGG","CCCCTA","TCACTG"};
		String adnHorizontal[] = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		String adnDiagonal[] = {"ATGCGA","CAGTGC","TTATGT","AGAATG","CCCCTA","TCACTG"};
		String adnLetraErronea[] = {"ATGCFA","CAGTGC","TTATGT","AGAATG","CCCCTA","TCACTG"};
		mutant.setDna(adn);
		dna.setDna(adn);
		adnDto.setDna(adn);
		adnDtoMenor.setDna(adnMenor);
		adnDtoVerticales.setDna(adnVertical);
		adnDtoHorizontales.setDna(adnHorizontal);
		adnDtoDiagonales.setDna(adnDiagonal);
		adnDtoLetraErronea.setDna(adnLetraErronea);
		esMutante = true;
		esHumano = false;
		listDna.add(dna);
		
	}
	
	@Test
	public void validarEsMutante() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDnaVacio);
		Boolean resultado= mutantService.isMutant(adnDto,esHumano);
		Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
		assertAll("resultado",
				()-> assertEquals(false,resultado));
	}
	
	@Test
	public void validarEsMutanteBusinesYaAnalizado() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDna);
		//Boolean resultado= mutantService.saveAndReturn(adnDto,esHumano);
		assertThrows(BusinessException.class, 
				()->{ 
					mutantService.isMutant(adnDto, esMutante);
				});
		//Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
	}
	
	@Test
	public void validarEsMutanteBusinesMatrizDebeSer4x4() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDnaVacio);
		//Boolean resultado= mutantService.saveAndReturn(adnDto,esHumano);
		assertThrows(BusinessException.class, 
				()->{ 
					mutantService.isMutant(adnDtoMenor, esMutante);
				});
		//Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
	}
	
	@Test
	public void validarEsMutanteBusinesLetraErronea() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDnaVacio);
		//Boolean resultado= mutantService.saveAndReturn(adnDto,esHumano);
		assertThrows(BusinessException.class, 
				()->{ 
					mutantService.isMutant(adnDtoLetraErronea, esMutante);
				});
		//Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
	}
	
	@Test
	public void validarEsMutanteHorizontal() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDnaVacio);
		Boolean resultado= mutantService.isMutant(adnDtoHorizontales,esHumano);
		assertAll("resultado",
				()-> assertEquals(true,resultado));
		Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(adnDtoHorizontales.getDna());
	}
	
	@Test
	public void validarEsMutanteVerticales() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDnaVacio);
		Boolean resultado= mutantService.isMutant(adnDtoVerticales,esHumano);
		assertAll("resultado",
				()-> assertEquals(true,resultado));
		Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
	}
	
	@Test
	public void validarEsMutanteDiagonales() throws Exception {
		Mockito.when(dnaRepository.findByDna(Mockito.any())).thenReturn(listDnaVacio);
		Boolean resultado= mutantService.isMutant(adnDtoDiagonales,esHumano);
		assertAll("resultado",
				()-> assertEquals(true,resultado));
		//Mockito.verify(dnaRepository, Mockito.times(1)).findByDna(dna.getDna());
	}
}
