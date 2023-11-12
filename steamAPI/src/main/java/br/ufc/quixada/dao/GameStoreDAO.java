// package br.ufc.quixada.dao;

// import java.util.List;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import br.ufc.quixada.entity.Game;
// import br.ufc.quixada.entity.GameStore;

// @Repository
// public interface GameStoreDAO extends JpaRepository<GameStore, Integer> {
//   public Boolean createGameStore();

//   public Boolean updateGameStore();

//   public Boolean removeGameStore();

//   @Query(name="gameByGender")
//   public List<Game> getGamesByGender();
  
//   // JPQL Query
//   @Query("select g.free_games, g.rpg_games, g.shooter_games, g.sports_games from GameStore g")
//   public List<Game> getAllGames();
// }