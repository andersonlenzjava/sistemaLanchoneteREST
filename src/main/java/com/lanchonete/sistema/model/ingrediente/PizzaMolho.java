package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PizzaMolho {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Ingrediente ingrediente;
	public String tipoMolho;

	public PizzaMolho(BigDecimal precoVenda, String dataValidade, double peso, String tipoMolho) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoMolho = tipoMolho;
	}
	
	public PizzaMolho() {
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public String getTipoMolho() {
		return tipoMolho;
	}
	public void setTipoMolho(String tipoMolho) {
		this.tipoMolho = tipoMolho;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
