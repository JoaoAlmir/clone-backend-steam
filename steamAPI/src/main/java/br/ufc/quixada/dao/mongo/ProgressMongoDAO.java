package br.ufc.quixada.dao.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.dao.ProgressDAO;
import br.ufc.quixada.entity.Progress;

@Repository
public interface ProgressMongoDAO extends ProgressDAO, MongoRepository<Progress, String> {
  public Optional<Progress> findById(String id);

  public void deleteById(String id);

  public List<Progress> findByProgressPercent(int progressPercent);

  public List<Progress> findByTrophyQuantity(int trophyQuantity);

  public List<Progress> findByProfile_Id(String id);

  public List<Progress> findByGame_Id(String id);

  public List<Progress> findByMinutesPlayedGreaterThan(Integer minutes);
}
