package com.lanchonete.sistema.form.ingredientes;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;

public class SalgadinhoRecheioForm {

	@NotNull
	@NotEmpty
	@Length(min = 3, max = 80)
	String tipoRecheio;
	
	@DecimalMin(value = "0.1", inclusive = true)
    @Digits(integer=3, fraction=2)
	BigDecimal precoVenda;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	String dataValidade;
	
	@DecimalMin(value = "0.1", inclusive = true)
    @Digits(integer=4, fraction=2)
	double peso;
	
	public void setTipoMolho(String tipoMolho) {
		this.tipoRecheio = tipoMolho;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getTipoRecheio() {
		return tipoRecheio;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public double getPeso() {
		return peso;
	}
	public SalgadinhoRecheio converter() {
		return new SalgadinhoRecheio(precoVenda, dataValidade, peso, tipoRecheio);
	}
	
}
