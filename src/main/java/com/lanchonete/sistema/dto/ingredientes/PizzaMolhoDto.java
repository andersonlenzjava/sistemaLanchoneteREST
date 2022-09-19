package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.PizzaMolho;

public class PizzaMolhoDto {

	String tipoMolho;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public PizzaMolhoDto(PizzaMolho pizzaMolho) {
		this.tipoMolho = pizzaMolho.getTipoMolho();
		this.precoVenda	= pizzaMolho.getIngrediente().getPrecoVenda();
		this.dataValidade = pizzaMolho.getIngrediente().getDataValidade();
		this.peso = pizzaMolho.getIngrediente().getPeso();
	}

	public String getTipoMolho() {
		return tipoMolho;
	}
	public void setTipoMolho(String tipoMolho) {
		this.tipoMolho = tipoMolho;
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

	public static Page<PizzaMolhoDto> converter(Page<PizzaMolho> pizzaMolho) {
		return pizzaMolho.map(PizzaMolhoDto::new);
	}
	public static PizzaMolhoDto converterUmLancheMolho(PizzaMolho pizzaMolho) {
		return new PizzaMolhoDto(pizzaMolho);
	}
	
}
