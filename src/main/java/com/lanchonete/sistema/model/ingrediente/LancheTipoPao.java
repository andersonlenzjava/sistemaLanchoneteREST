package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class LancheTipoPao {

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
}
