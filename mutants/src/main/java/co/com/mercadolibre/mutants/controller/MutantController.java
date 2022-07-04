package co.com.mercadolibre.mutants.controller;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.mutants.dto.AdnDTO;
import co.com.mercadolibre.mutants.exception.AccesDeniedException;
import co.com.mercadolibre.mutants.exception.BusinessException;
import co.com.mercadolibre.mutants.service.MutantService;


//@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/mutants")
public class MutantController {
		//Se crean valiables Generales para ser usadas en el job de escribir bd
	    public static String adnEnviado[] = {};
		public static Boolean esMutante = false;
		
		@Autowired
		MutantService mutantService;
		
		@Autowired
		JobLauncher jobGrabarDnaLauncher;
		
		  
		@Autowired
		Job grabarBdJob;
		
	
		//Mapping mutant llama al service implementado para leer la matriz
		@PostMapping("/mutant")
		@ResponseStatus(HttpStatus.OK)
		public String create(@RequestBody AdnDTO adn)throws Exception {
			validarDnaValido(adn);
			esMutante = mutantService.isMutant(adn, esMutante);
			adnEnviado = adn.getDna();
			llamarJob();
			validarRespuesta();
			return "";
		}

		//Valida la respuesta del servicio, con esto sabemos si responder 200 0 401.
		private void validarRespuesta() {
			if (!esMutante) {
				throw new AccesDeniedException("No es Mutante.");
			}
		}

		//Metodo que llama y ejecuta el job llamado grabarbdjob
		private void llamarJob() throws Exception{
			jobGrabarDnaLauncher
	        .run(grabarBdJob, new JobParametersBuilder()
	            .addLong("idInicio", System.nanoTime())
	            .toJobParameters());
			
		}


		private void validarDnaValido(AdnDTO adn) {
			System.out.print(adn.getDna().length);
			if (adn.getDna().length < 4) {
				throw new BusinessException("La matriz debe ser minimo de 4*4");
			}
			
		}
		
}
