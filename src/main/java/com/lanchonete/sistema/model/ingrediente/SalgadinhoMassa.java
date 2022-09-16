package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class SalgadinhoMassa {

	private Ingrediente ingrediente;
	public String tipoMassa;

	public SalgadinhoMassa(BigDecimal precoVenda, String dataValidade, double peso, String tipoMassa) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoMassa = tipoMassa;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public String getTipoMassa() {
		return tipoMassa;
	}
	public void setTipoMassa(String tipoMassa) {
		this.tipoMassa = tipoMassa;
	}
}
