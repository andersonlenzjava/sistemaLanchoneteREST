package com.lanchonete.sistema.dto.ingredientes;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;

public class SalgadinhoMassaDto {

	Long id;
	String tipoMassa;
	BigDecimal precoVenda;
	String dataValidade;
	double peso;
	
	public SalgadinhoMassaDto(SalgadinhoMassa salgadinhoMassa) {
		this.id = salgadinhoMassa.getId();
		this.tipoMassa = salgadinhoMassa.getTipoMassa();
		this.precoVenda	= salgadinhoMassa.getIngrediente().getPrecoVenda();
		this.dataValidade = salgadinhoMassa.getIngrediente().getDataValidade();
		this.peso = salgadinhoMassa.getIngrediente().getPeso();
	}

	public String getTipoMassa() {
		return tipoMassa;
	}
	public void setTipoMassa(String tipoMassa) {
		this.tipoMassa = tipoMassa;
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

	public static Page<SalgadinhoMassaDto> converter(Page<SalgadinhoMassa> salgadinhoMassa) {
		return salgadinhoMassa.map(SalgadinhoMassaDto::new);
	}
	public static SalgadinhoMassaDto converterUmLancheMolho(SalgadinhoMassa salgadinhoMassa) {
		return new SalgadinhoMassaDto(salgadinhoMassa);
	}
	
}
