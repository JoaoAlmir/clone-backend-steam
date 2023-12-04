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
public interface ProfileJPADAO extends ProfileDAO, JpaRepository<Profile, String> {
	// NamedQuery
	@Query(name = "profileById")
	public Profile getProfileById(String id);

	// Named query
	@Query(name = "profileByName")
	public Profile findByNameContaining(String name);

	// Named query
	@Query(name = "profileByEmail")
	public Profile findByEmail(String email);

	// Native query
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM progress WHERE IdProfile = :IdProfile" +
			" ;DELETE FROM lib WHERE IdProfile = :IdProfile" +
			" ;DELETE FROM friends WHERE IdProfile = :IdProfile OR idFriend = :IdProfile" +
			" ;DELETE FROM profile WHERE id = :IdProfile", nativeQuery = true)
	public void deleteById(String IdProfile);

	// // Native query
	// @Query(value = "SELECT * FROM profile " +
	// 		"where upper(name) like upper(:name) and id in " +
	// 		"(SELECT idFriend FROM friends where IdProfile = :id)", nativeQuery = true)
	// public List<Profile> findFriendsByNameIgnoreCaseContaining(String id, String name);

	// // Native query
	// @Query(value = "SELECT * FROM profile " +
	// 		"where nickName like :nickname and id in " +
	// 		"(SELECT idFriend FROM friends where IdProfile=:id)", nativeQuery = true)
	// public List<Profile> findFriendsByNickNameIgnoreCaseContaining(String id, String nickname);

	// // Native query
	// @Query(value = "SELECT * FROM profile " +
	// 		"where upper(local) like upper(:local) and id in " +
	// 		"(SELECT idFriend FROM friends where IdProfile=:id)", nativeQuery = true)
	// public List<Profile> findFriendsByLocal(String id, String local);

	// // Native query
	// @Query(value = "SELECT * FROM profile " +
	// 		"where level >= :level and id in " +
	// 		"(SELECT idFriend FROM friends where IdProfile=:id)", nativeQuery = true)
	// public List<Profile> findFriendsByLevelGreaterThan(String id, int level);
}