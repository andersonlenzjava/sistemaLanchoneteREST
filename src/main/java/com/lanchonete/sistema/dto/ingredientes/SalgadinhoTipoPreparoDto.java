package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;

public class SalgadinhoTipoPreparoDto {

	String tipoPreparo;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public SalgadinhoTipoPreparoDto(SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
		this.tipoPreparo = salgadinhoTipoPreparo.getTipoPreparo();
		this.precoVenda	= salgadinhoTipoPreparo.getIngrediente().getPrecoVenda();
		this.dataValidade = salgadinhoTipoPreparo.getIngrediente().getDataValidade();
		this.peso = salgadinhoTipoPreparo.getIngrediente().getPeso();
	}

	public String getTipoMolho() {
		return tipoPreparo;
	}
	public void setTipoMolho(String tipoMolho) {
		this.tipoPreparo = tipoMolho;
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

	public static Page<SalgadinhoTipoPreparoDto> converter(Page<SalgadinhoTipoPreparo> salgadinhoTipoPreparo) {
		return salgadinhoTipoPreparo.map(SalgadinhoTipoPreparoDto::new);
	}
	public static SalgadinhoTipoPreparoDto converterUmLancheMolho(SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
		return new SalgadinhoTipoPreparoDto(salgadinhoTipoPreparo);
	}
	
}
