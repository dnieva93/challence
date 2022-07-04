package co.com.mercadolibre.mutants.controller;


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

import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.service.StatService;


@TestInstance(Lifecycle.PER_CLASS)
public class StatControllerTest {
	
	private StatsDto statDto = new StatsDto();

	@InjectMocks
	private StatController statController = new StatController();
	
	@Mock
	StatService statService;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		statDto.setCount_human_dna(100);
		statDto.setCount_mutant_dna(40);
		statDto.setRatio(0.4);
		
	}
	
		
	@Test
	public void validarListadoTarjetasDetalladas() {
		Mockito.when(statService.findAll()).thenReturn(statDto);
		StatsDto resultado= statController.getStats();
		Mockito.verify(statService, Mockito.times(1)).findAll();
		assertAll("resultado",
				()-> assertEquals(0.4,resultado.getRatio()));
	}
}
