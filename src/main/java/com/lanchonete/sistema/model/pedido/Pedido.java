package com.lanchonete.sistema.model.pedido;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.item.Salgadinho;

@Entity
public class Pedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCliente;
	private BigDecimal valorTotalServico = BigDecimal.ZERO;
	private BigDecimal valorPago = BigDecimal.ZERO;
	private BigDecimal troco = BigDecimal.ZERO;
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.ABERTO;

	public Pedido() {
	}
	
	
	public Pedido(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public void adicionaPizza(Pizza pizza) {
		this.valorTotalServico = this.valorTotalServico.add(pizza.getItem().getTotalItem()); // adiciona no total do pedido 
	}

	public void adicionaSalgadinho(Salgadinho salgadinho) {
		this.valorTotalServico = this.valorTotalServico.add(salgadinho.getItem().getTotalItem());
	}

	public void adicionaLanche(Lanche lanche) {
		this.valorTotalServico = this.valorTotalServico.add(lanche.getItem().getTotalItem());
	}
	
	public void removerLanche(Lanche lanche) {
		this.valorTotalServico = this.valorTotalServico.subtract(lanche.getItem().getTotalItem());
	}

	public void removerPizza(Pizza pizza) {
		this.valorTotalServico = this.valorTotalServico.subtract(pizza.getItem().getTotalItem());
	}

	public void removerSalgadinho(Salgadinho salgadinho) {
		this.valorTotalServico = this.valorTotalServico.subtract(salgadinho.getItem().getTotalItem());
	}
	
	
	public BigDecimal calcularTroco(BigDecimal valorPago) throws Exception {
		if (valorPago.compareTo(this.valorTotalServico) == -1) {
			this.troco = BigDecimal.ZERO;
			throw new Exception("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.valorTotalServico);
		} else if (valorPago.compareTo(this.valorTotalServico) == 0) {
			this.troco = BigDecimal.ZERO;
			this.valorPago = valorPago;
			this.statusPedido = StatusPedido.PAGOFINALIZADO;
		} else if (valorPago.compareTo(this.valorTotalServico) == 1) {
			this.troco = valorPago.subtract(this.valorTotalServico);
			this.valorPago = valorPago;
			this.statusPedido = StatusPedido.PAGOFINALIZADO;
		}
		System.out.println(troco);
		return this.troco;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BigDecimal getTaxaServico() {
		return valorTotalServico;
	}
	public void setTaxaServico(BigDecimal taxaServico) {
		this.valorTotalServico = taxaServico;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public BigDecimal getTroco() {
		return troco;
	}
	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}
	public BigDecimal getValorTotalServico() {
		return valorTotalServico;
	}
	public void setValorTotalServico(BigDecimal valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}



}
