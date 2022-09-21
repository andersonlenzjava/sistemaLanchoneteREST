package com.lanchonete.sistema.model.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.item.Salgadinho;

public class Pedido {

	private Long id;
	private List<Lanche> listaLanche = new ArrayList<>();
	private List<Pizza> listaPizza = new ArrayList<>();
	private List<Salgadinho> listaSalgadinho = new ArrayList<>();
	private String nomeCliente;
	private BigDecimal valorTotalServico;
	private BigDecimal valorPago;
	private BigDecimal troco;
	private StatusPedido statusPedido = StatusPedido.ABERTO;

	public Pedido() {
	}
	
	public Pedido(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public void adicionaPizza(Pizza pizza) {
		this.listaPizza.add(pizza);
		this.statusPedido = StatusPedido.PROCESSANDO;
	}

	public void adicionaSalgadinho(Salgadinho salgadinho) {
		this.listaSalgadinho.add(salgadinho);
		this.statusPedido = StatusPedido.PROCESSANDO;
	}

	public void adicionaLanche(Lanche lanche) {
		this.listaLanche.add(lanche);
		this.statusPedido = StatusPedido.PROCESSANDO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void calculaTaxaServico() {
		if (!(this.listaLanche.isEmpty())) {
			this.listaLanche.forEach(lanche -> this.valorTotalServico = this.valorTotalServico.add(lanche.getItem().getTotalItem()));
		}
		if (!(this.listaPizza.isEmpty())) {
			this.listaPizza.forEach(pizza -> this.valorTotalServico = this.valorTotalServico.add(pizza.getItem().getTotalItem()));
		}
		if (!(this.listaSalgadinho.isEmpty())) {
			this.listaSalgadinho.forEach(salgadinho -> this.valorTotalServico.add(salgadinho.getItem().getTotalItem()));
		}
		System.out.println(this.valorTotalServico);
	}
	
	public BigDecimal calcularTroco(BigDecimal valorPago) throws Exception {
		if (valorPago.compareTo(this.valorTotalServico) == 1) {
			this.troco = BigDecimal.ZERO;
			throw new Exception("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.valorTotalServico);
		} else if (valorPago.compareTo(this.valorTotalServico) == 0) {
			this.troco = BigDecimal.ZERO;
			this.statusPedido = StatusPedido.PAGOFINALIZADO;
		} else if (valorPago.compareTo(this.valorTotalServico) == -1) {
			this.troco = valorPago.subtract(this.valorTotalServico);
			this.statusPedido = StatusPedido.PAGOFINALIZADO;
		}
		System.out.println(troco);
		return this.troco;
	}

	public List<Lanche> getListaLanche() {
		return listaLanche;
	}
	public void setListaLanche(List<Lanche> listaLanche) {
		this.listaLanche = listaLanche;
	}
	public List<Pizza> getListaPizza() {
		return listaPizza;
	}
	public void setListaPizza(List<Pizza> listaPizza) {
		this.listaPizza = listaPizza;
	}
	public List<Salgadinho> getListaSalgadinho() {
		return listaSalgadinho;
	}
	public void setListaSalgadinho(List<Salgadinho> listaSalgadinho) {
		this.listaSalgadinho = listaSalgadinho;
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
