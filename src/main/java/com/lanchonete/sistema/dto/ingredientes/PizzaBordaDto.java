package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.PizzaBorda;

public class PizzaBordaDto {

	Long id;
	String temBorda;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public PizzaBordaDto(PizzaBorda pizzaBorda) {
		this.id = pizzaBorda.getId();
		this.temBorda = pizzaBorda.getTipoBorda();
		this.precoVenda	= pizzaBorda.getIngrediente().getPrecoVenda();
		this.dataValidade = pizzaBorda.getIngrediente().getDataValidade();
		this.peso = pizzaBorda.getIngrediente().getPeso();
	}

	public String getTipoMolho() {
		return temBorda;
	}
	public void setTipoMolho(String temBorda) {
		this.temBorda = temBorda;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public static Page<PizzaBordaDto> converter(Page<PizzaBorda> pizzaBorda) {
		return pizzaBorda.map(PizzaBordaDto::new);
	}
	public static PizzaBordaDto converterUmLancheMolho(PizzaBorda pizzaBorda) {
		return new PizzaBordaDto(pizzaBorda);
	}
	
}
