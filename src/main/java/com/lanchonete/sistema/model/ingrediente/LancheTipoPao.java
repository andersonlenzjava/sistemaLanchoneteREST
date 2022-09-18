package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LancheTipoPao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Ingrediente ingrediente;
	public String tipoPao;

	public LancheTipoPao(BigDecimal precoVenda, String dataValidade, double peso, String tipoPao) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoPao = tipoPao;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public String getTipoPao() {
		return tipoPao;
	}
	public void setTipoPao(String tipoPao) {
		this.tipoPao = tipoPao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
