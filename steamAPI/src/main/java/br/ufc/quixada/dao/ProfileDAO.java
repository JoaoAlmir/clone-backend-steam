package br.ufc.quixada.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;

@Repository
public interface ProfileDAO extends JpaRepository<Profile, Integer> {

	// NamedQuery
	@Query(name = "profileById")
	public Profile getProfileById(int id);

	// Named query
	@Query(name = "profileByName")
	public Profile getProfileByName(String name);

	// Named query
	@Query(name = "profileByEmail")
	public Profile getProfileByEmail(String email);

	// Native query
	@Query(value = "SELECT * FROM profile " +
			"where upper(name) like upper(:name) and id in " +
			"(SELECT id_friend FROM friends where id_profile = :id)", nativeQuery = true)
	public List<Profile> getAllFriendsByName(int id, String name);

	// Native query
	@Query(value = "SELECT * FROM profile " +
			"where nick_name like :nickname and id in " +
			"(SELECT id_friend FROM friends where id_profile=:id)", nativeQuery = true)
	public List<Profile> getAllFriendsNickName(int id, String nickname);

	//Native query
	@Query(value = "SELECT * FROM profile "+
			"where upper(local) like upper(:local) and id in " +
			"(SELECT id_friend FROM friends where id_profile=:id)", nativeQuery = true)
	public List<Profile> getAllFriendsByLocal(int id, String local);

	// Query by method name
	@Query(value = "SELECT * FROM profile "+
			"where level >= :level and id in " +
			"(SELECT id_friend FROM friends where id_profile=:id)", nativeQuery = true)
	public List<Profile> getAllFriendsByLevelMore(int id, int level);
}