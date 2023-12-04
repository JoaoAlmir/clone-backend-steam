package br.ufc.quixada.dao;

import java.util.List;
import java.util.Optional;

import br.ufc.quixada.entity.Profile;

public interface ProfileDAO {
  public Optional<Profile> findById(String id);

  public Profile findByNameContaining(String name);

  public Profile findByEmail(String email);

  public void deleteById(String idProfile);

  // public List<Profile> findByIdByFriendsByNameIgnoreCaseContaining(String id, String name);

  // public List<Profile> findFriendsByNickNameIgnoreCaseContaining(String id, String nickname);

  // public List<Profile> findFriendsByLocal(String id, String local);

  // public List<Profile> findFriendsByLevelGreaterThan(String id, int level);
}
