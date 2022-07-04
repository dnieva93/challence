package co.com.mercadolibre.mutants.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.mutants.dto.AdnDTO;
import co.com.mercadolibre.mutants.dto.EsMutanteDto;
import co.com.mercadolibre.mutants.dto.PalabrasDTO;
import co.com.mercadolibre.mutants.exception.BusinessException;
import co.com.mercadolibre.mutants.model.Dna;
import co.com.mercadolibre.mutants.repository.DnaRepository;
import co.com.mercadolibre.mutants.util.Armador;
import co.com.mercadolibre.mutants.util.Buscador;



@Service
public class MutantService {

	@Autowired
	DnaRepository dnaRepository;
	
	
	public Boolean isMutant(AdnDTO adn, Boolean esMutante) {
		
		int secuenciasEncontradas = 0;
		PalabrasDTO palabras = new PalabrasDTO();
		EsMutanteDto esMutanteDto = new EsMutanteDto();
		validarProcesamientoDna(adn);
		esMutanteDto.setEsMutante(esMutante);
		esMutanteDto = validarSecienciaHorizontal(adn, palabras, esMutanteDto);
		esMutanteDto = validarSecuenciaVertical(palabras.getVerticales(), esMutanteDto);
		esMutanteDto = ValidarSecuenciaDiagonal(palabras.getDiagonales(), esMutanteDto);
		secuenciasEncontradas = esMutanteDto.getNumedorSecuenciasEncontradas();

		
		if (secuenciasEncontradas <= 1) {
			esMutante = false;
		}
		return esMutanteDto.getEsMutante();
		
	}
	
	private EsMutanteDto validarSecienciaHorizontal(AdnDTO adn, PalabrasDTO palabras, EsMutanteDto esMutanteDto) {
		// Este metodo es el encargado de validar la secuencia horizontal y de llamar el metodo que construye
		// las palabras verticales y diagonales.
		String adnRecibido = "";
		String adnRecibidoarray[] = adn.getDna();
		int secuenciasEncontradasHorizontal = 0;

		String[] palabrasVertical = new String[adnRecibidoarray.length];
		ArrayList<String> palabrasDiagonal = new ArrayList<String>();
		palabras.setDiagonales(palabrasDiagonal);
		palabras.setVerticales(palabrasVertical);
		
		for(int i = 0; i < adn.getDna().length; i++){
			if (secuenciasEncontradasHorizontal <= 1) {
				adnRecibido = adnRecibidoarray[i];
				if (adnRecibido.length() < 4) {
					throw new BusinessException("La matriz debe ser minimo de 4*4");
				}
				secuenciasEncontradasHorizontal = validarDna(adnRecibido,secuenciasEncontradasHorizontal);
				esMutanteDto.setNumedorSecuenciasEncontradas(secuenciasEncontradasHorizontal);
				palabras = Armador.palabras(adnRecibido, palabras.getVerticales(),palabras.getDiagonales(), i);
			}
			else{
				esMutanteDto.setEsMutante(true);
				break;
			}
		}
		return esMutanteDto;
	}

	private EsMutanteDto validarSecuenciaVertical(String[] verticales, EsMutanteDto esMutanteDto) {
		//Este metodo valida las palabras verticales para encontrar la secuenccia del adn.
		for (String dnaVertical : verticales) {
			if (esMutanteDto.getNumedorSecuenciasEncontradas() <= 1) {
				esMutanteDto.setNumedorSecuenciasEncontradas(validarDna(dnaVertical,esMutanteDto.getNumedorSecuenciasEncontradas()));
			}else{
				esMutanteDto.setEsMutante(true);
				break;
			}
		}
		return esMutanteDto;
	}
	
	private EsMutanteDto ValidarSecuenciaDiagonal(ArrayList<String> diagonales, EsMutanteDto esMutanteDto) {
		//Este metodo valida las palabras verticales para encontrar la secuenccia del adn.
		for (String dnaDiagonal : diagonales) {
			if (esMutanteDto.getNumedorSecuenciasEncontradas() <= 1) {
				esMutanteDto.setNumedorSecuenciasEncontradas(validarDna(dnaDiagonal,esMutanteDto.getNumedorSecuenciasEncontradas()));
			}else{
				esMutanteDto.setEsMutante(true);
				break;
			}
		}
		return esMutanteDto;
	}

	private void validarProcesamientoDna(AdnDTO adn) {
		//Este metodo valida si el adn enviado ya fue analizado.
		List<Dna> bdobjeto = dnaRepository.findByDna(adn.getDna());
		if (!bdobjeto.isEmpty()) {
			for (Dna dna : bdobjeto) {
				System.out.println(Arrays.toString(dna.getDna()));
			}
			throw new BusinessException("El Adn enviado ya fue analizado");
		}else {
			System.out.println("Es Null o Blanco");
		}
		
		
	}

	private int validarDna(String adnRecibido, int contadorSecuencia) {
		//Este metodo busca la secuencia, si la encuentra retorna un contador con el numero de secuencias.
		//El contador se incrmenta en 1 cada vez que encuentra una secuencia, despues con este se valida para,
		//en el caso de ya cumplir con la condicion de mutante, responder y no seguir analizando.
		Boolean encontrado = false;
		encontrado = Buscador.buscarSecuencia(adnRecibido);
		if (encontrado) {
			contadorSecuencia = contadorSecuencia + 1;
			System.out.print(adnRecibido); 
			}
		return contadorSecuencia;
		
	}

}



