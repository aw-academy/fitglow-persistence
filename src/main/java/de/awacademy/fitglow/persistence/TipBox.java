package de.awacademy.fitglow.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TipBox {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany
	@JoinColumn(name = "tip_box_id")
	private List<TipAmount> tipAmounts = new ArrayList<>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TipAmount> getTipAmounts() {
		return tipAmounts;
	}

	public void setTipAmounts(List<TipAmount> tipAmounts) {
		this.tipAmounts = tipAmounts;
	}

	
}
