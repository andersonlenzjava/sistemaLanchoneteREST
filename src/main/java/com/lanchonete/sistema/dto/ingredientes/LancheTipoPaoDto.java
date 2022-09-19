package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;

public class LancheTipoPaoDto {

	String tipoPao;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public LancheTipoPaoDto(LancheTipoPao lancheTipoPao) {
		this.tipoPao = lancheTipoPao.getTipoPao();
		this.precoVenda	= lancheTipoPao.getIngrediente().getPrecoVenda();
		this.dataValidade = lancheTipoPao.getIngrediente().getDataValidade();
		this.peso = lancheTipoPao.getIngrediente().getPeso();
	}

	public String getTipoMolho() {
		return tipoPao;
	}
	public void setTipoMolho(String tipoMolho) {
		this.tipoPao = tipoMolho;
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

	public static Page<LancheTipoPaoDto> converter(Page<LancheTipoPao> lancheTipoPao) {
		return lancheTipoPao.map(LancheTipoPaoDto::new);
	}
	public static LancheTipoPaoDto converterUmLancheMolho(LancheTipoPao lancheTipoPao) {
		return new LancheTipoPaoDto(lancheTipoPao);
	}
	
}
