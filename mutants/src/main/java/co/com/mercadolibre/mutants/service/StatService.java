package co.com.mercadolibre.mutants.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.model.Stats;
import co.com.mercadolibre.mutants.repository.StatsRepository;



@Service
public class StatService {
	
	@Autowired
	StatsRepository statRepository;
	
	
	public StatsDto findAll() {
		StatsDto stat = new StatsDto();
		List<Stats> statDb = statRepository.findAll();
		for (Stats stats : statDb) {
			stat.setCount_human_dna(stats.getTotalHumanos());
			stat.setCount_mutant_dna(stats.getTotalMutantes());
			stat.setRatio(stats.getRatio());
		}
		System.out.println(stat.getRatio());
		return stat;
	
	}

	

}



