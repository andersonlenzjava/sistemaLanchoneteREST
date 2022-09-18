package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalgadinhoTipoPreparo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Ingrediente ingrediente;
	public String tipoPreparo;

	public SalgadinhoTipoPreparo(BigDecimal precoVenda, String dataValidade, double peso, String tipoPreparo) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoPreparo = tipoPreparo;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public String getTipoPreparo() {
		return tipoPreparo;
	}
	public void setTipoPreparo(String tipoPreparo) {
		this.tipoPreparo = tipoPreparo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
