package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.LancheRecheio;

public class LancheRecheioDto {

	Long id;
	String tipoRecheio;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public LancheRecheioDto(LancheRecheio lancheRecheio) {
		this.id = lancheRecheio.getId();
		this.tipoRecheio = lancheRecheio.getTipoRecheio();
		this.precoVenda	= lancheRecheio.getIngrediente().getPrecoVenda();
		this.dataValidade = lancheRecheio.getIngrediente().getDataValidade();
		this.peso = lancheRecheio.getIngrediente().getPeso();
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

	public static Page<LancheRecheioDto> converter(Page<LancheRecheio> lancheRecheios) {
		return lancheRecheios.map(LancheRecheioDto::new);
	}
	public static LancheRecheioDto converterUmLancheMolho(LancheRecheio lancheRecheios) {
		return new LancheRecheioDto(lancheRecheios);
	}
	
}
