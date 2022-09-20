package com.lanchonete.sistema.model.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.item.Salgadinho;

public class Pedido {

	private List<Lanche> listaLanche = new ArrayList<>();
	private List<Pizza> listaPizza = new ArrayList<>();
	private List<Salgadinho> listaSalgadinho = new ArrayList<>();
	private String nomeCliente;
	private BigDecimal valorTotalServico;
	private BigDecimal valorPago;
	private BigDecimal troco;
	private StatusPedido statusPedido = StatusPedido.ABERTO;
	
	public Pedido(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public void adicionaPizza(Pizza pizza) {
		this.listaPizza.add(pizza);
	}

	public void adicionaSalgadinho(Salgadinho salgadinho) {
		this.listaSalgadinho.add(salgadinho);
	}

	public void adicionaLanche(Lanche lanche) {
		this.listaLanche.add(lanche);
	}
	
	public BigDecimal calculaTaxaServico() {
		if (!(this.listaLanche.isEmpty())) {
			this.listaLanche.forEach(lanche -> this.valorTotalServico = this.valorTotalServico.add(lanche.calculaPrecoPrato()));
		}
		if (!(this.listaPizza.isEmpty())) {
			this.listaPizza.forEach(pizza -> this.valorTotalServico = this.valorTotalServico.add(pizza.calculaPrecoPrato()));
		}
		if (!(this.listaSalgadinho.isEmpty())) {
			this.listaSalgadinho.forEach(salgadinho -> this.valorTotalServico.add(salgadinho.calculaPrecoPrato()));
		}
		System.out.println(this.valorTotalServico);
		return this.valorTotalServico;
	}
	
	public BigDecimal calcularTroco(BigDecimal valorPago) {
		if (valorPago.compareTo(this.valorTotalServico) == 1) {
			System.out.println("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.valorTotalServico);
			this.troco = BigDecimal.ZERO;
		} else if (valorPago.compareTo(this.valorTotalServico) == 0) {
			this.troco = BigDecimal.ZERO;
		} else if (valorPago.compareTo(this.valorTotalServico) == -1) {
			this.troco = valorPago.subtract(this.valorTotalServico);
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
