package co.com.mercadolibre.mutants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mercadolibre.mutants.model.Mutant;

public interface MutantRepository extends MongoRepository<Mutant, String> {



}
