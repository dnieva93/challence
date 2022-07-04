package co.com.mercadolibre.mutants.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.model.Stats;
import co.com.mercadolibre.mutants.repository.StatsRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class StatServiceTest {
	
	StatsDto statDto = new StatsDto();
	private Stats stat = new Stats();
	private List<Stats> listStat = new ArrayList<>();
	
	@InjectMocks
	private StatService statService = new StatService();
	
	
	@Mock
	StatsRepository statRepository;

	
	@BeforeAll
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		statDto.setCount_human_dna(100);
		statDto.setCount_mutant_dna(40);
		statDto.setRatio(0.4);
		stat.setRatio(0.4);
		stat.setTotalHumanos(100L);
		stat.setTotalMutantes(40L);
		listStat.add(stat);
		
	}
	
	@Test
	public void getStat() throws Exception {
		Mockito.when(statRepository.findAll()).thenReturn(listStat);
		StatsDto resultado= statService.findAll();
		Mockito.verify(statRepository, Mockito.times(1)).findAll();
		assertAll("resultado",
				()-> assertEquals(100l,resultado.getCount_human_dna()));
	}
}
