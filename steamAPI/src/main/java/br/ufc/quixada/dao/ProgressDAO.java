package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Progress;

public interface ProgressDAO {
  public List<Progress> getCompleteProgress();
  public List<Progress> getAllEmptyTrophy();
  public List<Progress> getAllProgressByIdProfile(Integer id);
  public List<Progress> getAllProgressById_Game(Integer id);
  public List<Progress> getAllProgressByTime(Integer minutes);
}
