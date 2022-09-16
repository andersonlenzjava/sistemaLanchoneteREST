package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class PizzaBorda {

	private Ingrediente ingrediente;
	public boolean temBorda;

	public PizzaBorda(BigDecimal precoVenda, String dataValidade, double peso, boolean temBorda) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.temBorda = temBorda;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public boolean isTemBorda() {
		return temBorda;
	}
	public void setTemBorda(boolean temBorda) {
		this.temBorda = temBorda;
	}
	public String getNome() {
		String nomeBorda;
		if (temBorda) {
			nomeBorda = "Tem borda";
		} else {
			nomeBorda = "Não tem borda";
		}
		return nomeBorda;
	}
}
