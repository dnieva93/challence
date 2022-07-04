package co.com.mercadolibre.mutants.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.mutants.dto.StatsDto;
import co.com.mercadolibre.mutants.service.StatService;


//@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/mutants")
public class StatController {

	    
		@Autowired
		StatService statService;
		
		//Llama el servicio implementado para retornar estadisticas.
		@GetMapping("/stats")
		@ResponseStatus(HttpStatus.OK) public StatsDto getStats() { 
			return statService.findAll(); 
		}
		 
		
		
		
}
