package ar.com.gvallerino.xMenML.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.com.gvallerino.xMenML.entities.Dna;

@Transactional
public interface DnaDAO extends CrudRepository<Dna, Long>{

	@Query("SELECT COUNT(*) FROM Dna u WHERE u.isMutant = 1")
    long countMuntant();
}
