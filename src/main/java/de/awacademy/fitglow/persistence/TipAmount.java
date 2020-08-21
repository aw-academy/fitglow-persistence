package de.awacademy.fitglow.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TipAmount {

	@Id
	@GeneratedValue
	private Long id;
	
	private Integer value;
	
	@OneToMany
	@JoinColumn(name = "tip_amount_id")
	private List<TipType> tipTypes = new ArrayList<>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public List<TipType> getTipTypes() {
		return tipTypes;
	}

	public void setTipTypes(List<TipType> tipTypes) {
		this.tipTypes = tipTypes;
	}

	
}
