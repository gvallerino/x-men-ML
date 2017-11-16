package ar.com.gvallerino.xMenML.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.com.gvallerino.xMenML.entities.Dna;

/**
 * Capa DAO que relaciona los servicios de ADN con la base de datos.
 */
@Transactional
public interface DnaDAO extends CrudRepository<Dna, Long>{

	/** Consulta la cantidad de mutantes en la base de datos*/
	@Query("SELECT COUNT(*) FROM Dna u WHERE u.isMutant = TRUE")
    long countMuntant();
}
