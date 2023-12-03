package br.ufc.quixada.dao;

import java.util.List;

import br.ufc.quixada.entity.Profile;

public interface ProfileDAO {
  public Profile getProfileById(int id);
  public Profile getProfileByName(String name);
  public Profile getProfileByEmail(String email);
  public void removeProfileComplete(int id_profile);
  public List<Profile> getAllFriendsByName(int id, String name);
  public List<Profile> getAllFriendsNickName(int id, String nickname);
  public List<Profile> getAllFriendsByLocal(int id, String local);
  public List<Profile> getAllFriendsByLevelMore(int id, int level);
}
