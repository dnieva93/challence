package co.com.mercadolibre.mutants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mercadolibre.mutants.model.Stats;

public interface StatsRepository extends MongoRepository<Stats, String> {



}
