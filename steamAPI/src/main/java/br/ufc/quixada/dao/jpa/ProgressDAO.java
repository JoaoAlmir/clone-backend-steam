package br.ufc.quixada.dao.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Progress;

@Repository
public interface ProgressDAO extends JpaRepository<Progress, Integer> {

  // NamedQuery
  @Query(name = "getCompleteProgress")
  public List<Progress> getCompleteProgress();

  // NativeQuery
  @Query(value ="select * from progress where trophy_quantity = 0", nativeQuery = true)
  public List<Progress> getAllEmptyTrophy();

  // NativeQuery
  @Query(value = "select * from progress where id_profile = :id", nativeQuery = true)
  public List<Progress> getAllProgressByIdProfile(Integer id);
  
  // NativeQuery
  @Query(value = "select * from progress where id_game = :id", nativeQuery = true)
  public List<Progress> getAllProgressById_Game(Integer id);

  // NativeQuery
  @Query(value = "select * from progress where minutes_played >= :minutes", nativeQuery = true)
  public List<Progress> getAllProgressByTime(Integer minutes);

 



}