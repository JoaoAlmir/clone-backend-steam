package br.ufc.quixada.dao.mongo;

import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.entity.Game;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMongoDAO extends GameDAO, MongoRepository<Game, String> {

	public Optional<Game> findById(String id);

	public List<Game> findByNameIgnoreCaseContaining(String name);

	public void deleteById(String id);

	public List<Game> findByPriceLessThan(Double price);

	public List<Game> findByPublisherIgnoreCaseContaining(String publisher);

	public List<Game> findByDeveloperIgnoreCaseContaining(String developer);

	public List<Game> findByReviewGreaterThan(Double review);

	public List<Game> findByPriceBetween(Double start, Double end);

	public List<Game> findByDescriptionContaining(String desc);
}