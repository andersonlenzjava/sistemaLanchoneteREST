package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PizzaBorda {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Ingrediente ingrediente;
	private String tipoBorda;

	public PizzaBorda(BigDecimal precoVenda, String dataValidade, double peso, String tipoBorda) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoBorda = tipoBorda;
	}
	
	public PizzaBorda() {
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoBorda() {
		return tipoBorda;
	}
	public void setTipoBorda(String tipoBorda) {
		this.tipoBorda = tipoBorda;
	}
	
}
