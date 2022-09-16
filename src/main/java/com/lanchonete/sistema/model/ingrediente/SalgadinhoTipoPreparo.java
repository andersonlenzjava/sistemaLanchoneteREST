package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class SalgadinhoTipoPreparo {

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
}
