package com.lanchonete.sistema.form.pedido;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.lanchonete.sistema.model.pedido.Pedido;

public class PedidoForm {

	
	private Long id;
	
	@NotNull
	@NotEmpty
	private String nomeCliente;

	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Pedido converter() {
		return new Pedido(nomeCliente);
	}
	
	
}
