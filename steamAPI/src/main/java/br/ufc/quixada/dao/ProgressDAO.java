package br.ufc.quixada.dao;

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

  //JPQL Query
  // @Query(value ="select * from progress where profile.id = :id")
  // public List<Progress> getAllProfileProgresses(Integer id);




}