package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;

public class PizzaRecheioDto {

	Long id;
	String tipoRecheio;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public PizzaRecheioDto(PizzaRecheio pizzaRecheio) {
		this.id = pizzaRecheio.getId();
		this.tipoRecheio = pizzaRecheio.getTipoRecheio();
		this.precoVenda	= pizzaRecheio.getIngrediente().getPrecoVenda();
		this.dataValidade = pizzaRecheio.getIngrediente().getDataValidade();
		this.peso = pizzaRecheio.getIngrediente().getPeso();
	}

	public String getTipoMolho() {
		return tipoRecheio;
	}
	public void setTipoMolho(String tipoMolho) {
		this.tipoRecheio = tipoMolho;
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

	public static Page<PizzaRecheioDto> converter(Page<PizzaRecheio> pizzaRecheio) {
		return pizzaRecheio.map(PizzaRecheioDto::new);
	}
	public static PizzaRecheioDto converterUmLancheMolho(PizzaRecheio pizzaRecheio) {
		return new PizzaRecheioDto(pizzaRecheio);
	}
	
}
