package br.ufc.quixada.dao.mongo;

import br.ufc.quixada.dao.ProfileDAO;
import br.ufc.quixada.entity.Profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMongoDAO extends ProfileDAO, MongoRepository<Profile, String> {
    public Optional<Profile> findById(String id);

    public Profile findByNameContaining(String name);

    public Profile findByEmail(String email);

    public void deleteById(String id);

    // public List<Profile> findFriendsByNameIgnoreCaseContaining(String id, String name);

    // public List<Profile> findFriendsByNickNameIgnoreCaseContaining(String id, String nickname);

    // public List<Profile> findFriendsByLocal(String id, String local);

    // public List<Profile> findFriendsByLevelGreaterThan(String id, int level);
}