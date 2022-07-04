package co.com.mercadolibre.mutants.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mercadolibre.mutants.model.Dna;

public interface DnaRepository extends MongoRepository<Dna, String> {

	List<Dna> findByDna(String[] dna);


}
