package ar.com.gvallerino.xMenML.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "dna")
public class Dna {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String dnaData;
	
	private boolean isMutant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDnaData() {
		return dnaData;
	}

	public void setDnaData(String dnaData) {
		this.dnaData = dnaData;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
	
}
