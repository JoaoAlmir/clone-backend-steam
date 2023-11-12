package br.ufc.quixada.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Game;
// import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;

@Repository
public interface ProfileDAO extends JpaRepository<Profile, Integer> {
	// NamedQuery
	@Query(name="profileById")
	public Profile getProfileById(int id);

	// Named query
	@Query(name="profileByName")
	public Profile getProfileByName(String name);

	// Named query
	@Query(name="profileByEmail")
	public Profile getProfileByEmail(String email);

	// Native query
	// @Query(value="SELECT p FROM Profile p" +
  //           "where p.name=:name in " +
  //           "(SELECT p.friends FROM Profile p where p.id=:id)", nativeQuery=true)
	// public List<Profile> getAllfriendsByName(int id, String name);

	// Native query
	// @Query(value="SELECT g FROM Game g" +
  //           "where g.price=:price in " +
  //           "(SELECT p.wishlist FROM Profile p where p.id=:id)", nativeQuery=true)
	// public List<Game> getAllWishListGamesByPrice(int id, Double price);

	// JPQL query
	// @Query("SELECT g FROM Game g" +
  //       "where g.gender=:gender in " +
  //       "(SELECT p.wishlist FROM Profile p where p.id=:id)")
	// public List<Game> getAllWishListGamesByGender(int id, String gender);

	// JPQL query
	// @Query("SELECT p FROM Profile p" +
  //       "where p.nickname=:nickname in " +
  //       "(SELECT p.friends FROM Profile p where p.id=:id)")
	// public List<Profile> getAllFriendsNickName(int id, String nickname);

	// Query by method name
	// public Profile getAllProfilesByLocal();

	//Query by method name
	// public Profile getAllProfilesByLevel();
}