package co.com.mercadolibre.mutants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mercadolibre.mutants.model.Human;

public interface HumanRepository extends MongoRepository<Human, String> {

	

}
