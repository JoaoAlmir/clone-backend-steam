package br.ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import br.ufc.quixada.entity.Progress;

public interface ProgressDAO {
  public Optional<Progress> findById(String id);

  public List<Progress> findByProgressPercent(int percent);

  public List<Progress> findByTrophyQuantity(int quantity);

  // public List<Progress> findByProfile(String id);

  // public List<Progress> findByGame(String id);

  public List<Progress> findByMinutesPlayedGreaterThan(Integer minutes);
}
