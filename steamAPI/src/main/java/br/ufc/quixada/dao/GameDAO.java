package br.ufc.quixada.dao;

import java.time.LocalDate;
import java.util.List;

import br.ufc.quixada.entity.Game;

public interface GameDAO {
  public List<Game> getGameByName(String name);
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
