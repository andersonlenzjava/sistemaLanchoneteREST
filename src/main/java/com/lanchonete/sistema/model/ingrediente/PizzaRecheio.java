package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class PizzaRecheio {

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
}
