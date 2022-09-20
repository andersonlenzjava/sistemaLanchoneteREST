package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;

public class LancheMolhoDto {

	Long id;
	String tipoMolho;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public LancheMolhoDto(LancheMolho lancheMolho) {
		this.id = lancheMolho.getId();
		this.tipoMolho = lancheMolho.getTipoMolho();
		this.precoVenda	= lancheMolho.getIngrediente().getPrecoVenda();
		this.dataValidade = lancheMolho.getIngrediente().getDataValidade();
		this.peso = lancheMolho.getIngrediente().getPeso();
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public static Page<LancheMolhoDto> converter(Page<LancheMolho> molhos) {
		return molhos.map(LancheMolhoDto::new);
	}
	public static LancheMolhoDto converterUmLancheMolho(LancheMolho lancheMolho) {
		return new LancheMolhoDto(lancheMolho);
	}
}
