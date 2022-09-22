package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;

public class SalgadinhoRecheioDto {

	Long id;
	String tipoRecheio;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public SalgadinhoRecheioDto(SalgadinhoRecheio salgadinhoRecheio) {
		this.id = salgadinhoRecheio.getId();
		this.tipoRecheio = salgadinhoRecheio.getTipoRecheio();
		this.precoVenda	= salgadinhoRecheio.getIngrediente().getPrecoVenda();
		this.dataValidade = salgadinhoRecheio.getIngrediente().getDataValidade();
		this.peso = salgadinhoRecheio.getIngrediente().getPeso();
	}

	public String getTipoRecheio() {
		return tipoRecheio;
	}
	public void setTipoRecheio(String tipoRecheio) {
		this.tipoRecheio = tipoRecheio;
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

	public static Page<SalgadinhoRecheioDto> converter(Page<SalgadinhoRecheio> salgadinhoRecheio) {
		return salgadinhoRecheio.map(SalgadinhoRecheioDto::new);
	}
	public static SalgadinhoRecheioDto converterUmLancheMolho(SalgadinhoRecheio salgadinhoRecheio) {
		return new SalgadinhoRecheioDto(salgadinhoRecheio);
	}
	
}
