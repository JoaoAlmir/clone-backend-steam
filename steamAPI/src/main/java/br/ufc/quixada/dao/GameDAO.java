package br.ufc.quixada.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.ufc.quixada.entity.Game;

public interface GameDAO {
  public Optional<Game> findById(String id);

  public List<Game> findByNameIgnoreCaseContaining(String name);

  public List<Game> findByPriceLessThan(Double price);

  public List<Game> findByPublisherIgnoreCaseContaining(String publisher);

  public List<Game> findByReviewGreaterThan(int review);

  public void deleteById(String idGame);

  public List<Game> findByPriceBetween(Double start, Double end);

  public List<Game> findByDeveloperIgnoreCaseContaining(String developer);

  public List<Game> findByDescriptionContaining(String desc);
}
