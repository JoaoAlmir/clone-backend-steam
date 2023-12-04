package br.ufc.quixada.dao.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.entity.Game;
import jakarta.transaction.Transactional;

@Repository
public interface GameJPADAO extends GameDAO, JpaRepository<Game, String> {
  // NamedQuery
  @Query(name = "gameByName")
  public List<Game> findByNameIgnoreCaseContaining(String name);

  // NamedQuery
  @Query(name = "gameByPriceLessThanEqual")
  public List<Game> findByPriceLessThan(Double price);

  // NativeQuery
  @Query(value = "select * from game where publisher = :publisher", nativeQuery = true)
  public List<Game> findByPublisherIgnoreCaseContaining(String publisher);

  // Native query
  @Query(value = "select * from game where review >= 7", nativeQuery = true)
  public List<Game> findByReviewGreaterThan(int review);

  // // Native query
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM progress WHERE IdGame = :IdGame" +
      " ;DELETE FROM game WHERE id = :IdGame", nativeQuery = true)
  public void deleteById(String IdGame);

  // JPQL query
  @Query("select g from Game g where g.price >= :start and g.price <= :end")
  public List<Game> findByPriceBetween(Double start, Double end);

  // Query by method name
  public List<Game> findByDeveloperIgnoreCaseContaining(String developer);

  // Query by method name
  public List<Game> findByDescriptionContaining(String desc);
}