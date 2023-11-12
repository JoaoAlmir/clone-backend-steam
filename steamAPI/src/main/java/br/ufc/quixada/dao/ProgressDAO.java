package br.ufc.quixada.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Progress;

@Repository
public interface ProgressDAO extends JpaRepository<Progress, Integer> {

    

}