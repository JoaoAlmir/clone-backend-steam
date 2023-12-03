package br.ufc.quixada.dao.mongo;

import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.entity.Game;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMongoDAO extends GameDAO, MongoRepository<Game, String> {
	
	
	public List<Game> getGameByName(String cpf);


	public List<Game> gameByPriceLessThanEqual(Double price);


	public List<Game> getAllGamesByPublisher(String publisher);


	public List<Game> getAllGamesByGoodReview();


	public List<Game> getAllWishListGamesByPriceLess(int id, Double price);


	public List<Game> getAllWishListGamesByGender(int id, String gender);


	public void removeGameComplete(int id_game);


	public List<Game> getAllGamesByPriceWithInterval(Double start, Double end);


	public List<Game> getAllGamesByStartingRealeaseDate(LocalDate release_date);


	public List<Game> getAllGamesByDeveloperStartingWithIgnoreCase(String developer);

  
	public List<Game> getAllGamesByDescriptionContaining(String desc);
}