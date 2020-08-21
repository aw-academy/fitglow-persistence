package de.awacademy.fitglow.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipType {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String type; // [ Cash | CreditCard ]

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
