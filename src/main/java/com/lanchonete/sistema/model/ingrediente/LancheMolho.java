package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class LancheMolho {

	private Ingrediente ingrediente;
	public String tipoMolho;

	public LancheMolho(BigDecimal precoVenda, String dataValidade, double peso, String tipoMolho) {
		this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
		this.tipoMolho = tipoMolho;
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
}
