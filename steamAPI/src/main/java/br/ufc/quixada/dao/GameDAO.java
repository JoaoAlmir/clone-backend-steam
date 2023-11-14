package br.ufc.quixada.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Game;

@Repository
public interface GameDAO extends JpaRepository<Game, Integer> {

  // NamedQuery
  @Query(name = "gameByName")
  public List<Game> getGameByName(String name);

  // NamedQuery
  @Query(name = "gameByPriceLessThanEqual")
  public List<Game> gameByPriceLessThanEqual(Double price);

  // NativeQuery
  @Query(value = "select * from game where publisher = :publisher", nativeQuery = true)
  public List<Game> getAllGamesByPublisher(String publisher);

  // Native query
  @Query(value = "select * from game where review >= 7", nativeQuery = true)
  public List<Game> getAllGamesByGoodReview();

  // Native query
  @Query(value = "SELECT * FROM game " +
      "where price<=:price and id in " +
      "(SELECT id_game FROM wishlist where id_profile=:id)", nativeQuery = true)
  public List<Game> getAllWishListGamesByPriceLess(int id, Double price);

  // Native query
  @Query(value = "SELECT * FROM game " +
      "where upper(gender)=upper(:gender) and id in " +
      "(SELECT id_game FROM wishlist where id_profile=:id)", nativeQuery = true)
  public List<Game> getAllWishListGamesByGender(int id, String gender);

  // JPQL query
  @Query("select g from Game g where g.price >= :start and g.price <= :end")
  public List<Game> getAllGamesByPriceWithInterval(Double start, Double end);

  // JPQL Query
  @Query("select g from Game g where g.release_date >= :release_date")
  public List<Game> getAllGamesByStartingRealeaseDate(LocalDate release_date);

  // Query by method name
  public List<Game> getAllGamesByDeveloperStartingWithIgnoreCase(String developer);

  // Query by method name
  public List<Game> getAllGamesByDescriptionContaining(String desc);
}