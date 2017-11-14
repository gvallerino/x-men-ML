package ar.com.gvallerino.xMenML.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import ar.com.gvallerino.xMenML.entities.Dna;

@Transactional
public interface DnaDAO extends CrudRepository<Dna, Long>{

//	public Dna findById(long id);
	
}
