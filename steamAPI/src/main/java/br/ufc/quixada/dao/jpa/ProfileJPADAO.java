package br.ufc.quixada.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.dao.ProfileDAO;
import br.ufc.quixada.entity.Profile;
import jakarta.transaction.Transactional;

@Repository
public interface ProfileJPADAO extends ProfileDAO, JpaRepository<Profile, Integer> {
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
	@Modifying
	@Transactional
	@Query(value =  "DELETE FROM progress WHERE id_profile = :id_profile"+
			" ;DELETE FROM wishlist WHERE id_profile = :id_profile" +
			" ;DELETE FROM lib WHERE id_profile = :id_profile" +
			" ;DELETE FROM friends WHERE id_profile = :id_profile OR id_friend = :id_profile" +
			" ;DELETE FROM profile WHERE id = :id_profile", nativeQuery = true)
	public void removeProfileComplete(int id_profile);

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

	// Native query
	@Query(value = "SELECT * FROM profile " +
			"where upper(local) like upper(:local) and id in " +
			"(SELECT id_friend FROM friends where id_profile=:id)", nativeQuery = true)
	public List<Profile> getAllFriendsByLocal(int id, String local);

	// Native query
	@Query(value = "SELECT * FROM profile " +
			"where level >= :level and id in " +
			"(SELECT id_friend FROM friends where id_profile=:id)", nativeQuery = true)
	public List<Profile> getAllFriendsByLevelMore(int id, int level);
}