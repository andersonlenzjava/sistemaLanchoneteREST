package com.lanchonete.sistema.model.item;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

	private BigDecimal totalItem = BigDecimal.ZERO;
	private LocalDate dataValidade;
	private double pesoItem;
	
	public BigDecimal getTotalItem() {
		return totalItem;
	}
	public BigDecimal setTotalItem(BigDecimal totalItem) {
		return this.totalItem = totalItem;
	}
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	public double getPesoPrato() {
		return pesoItem;
	}
	public double setPesoPrato(double pesoPrato) {
		return this.pesoItem = pesoPrato;
	}
}
