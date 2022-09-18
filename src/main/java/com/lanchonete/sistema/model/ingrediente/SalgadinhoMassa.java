package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalgadinhoMassa {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
