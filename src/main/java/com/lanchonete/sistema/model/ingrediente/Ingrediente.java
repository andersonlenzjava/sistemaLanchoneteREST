package com.lanchonete.sistema.model.ingrediente;

import java.math.BigDecimal;

public class Ingrediente {

	private Long id;
	private BigDecimal precoVenda;
	private String dataValidade;
	private double peso;
	
	public Ingrediente(BigDecimal precoVenda, String dataValidade, double peso) {
		this.precoVenda = precoVenda;
		this.dataValidade = dataValidade;
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
}
