package br.ufc.quixada.dao.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.dao.ProgressDAO;
import br.ufc.quixada.entity.Progress;

@Repository
public interface ProgressJPADAO extends ProgressDAO, JpaRepository<Progress, String> {

  // NamedQuery
  @Query(value = "select * from Progress where progress_percent = :percent", nativeQuery = true)
  public List<Progress> findByProgressPercent(int percent);

  // NativeQuery
  @Query(value = "select * from progress where trophy_quantity = 0", nativeQuery = true)
  public List<Progress> findByTrophyQuantity(int quantity);

  // NativeQuery
  @Query(value = "select * from progress where id_profile = :id", nativeQuery = true)
  public List<Progress> findByProfile(String id);

  // NativeQuery
  @Query(value = "select * from progress where id_game = :id", nativeQuery = true)
  public List<Progress> findByGame(String id);

  // NativeQuery
  @Query(value = "select * from progress where minute_played >= :minutes", nativeQuery = true)
  public List<Progress> findByMinutesPlayedGreaterThan(Integer minutes);
}