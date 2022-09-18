package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PizzaRecheio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Ingrediente ingrediente;
	public String tipoRecheio;

	public PizzaRecheio(BigDecimal precoVenda, String dataValidade, double peso, String tipoRecheio) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoRecheio = tipoRecheio;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public String getTipoRecheio() {
		return tipoRecheio;
	}
	public void setTipoRecheio(String tipoRecheio) {
		this.tipoRecheio = tipoRecheio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
