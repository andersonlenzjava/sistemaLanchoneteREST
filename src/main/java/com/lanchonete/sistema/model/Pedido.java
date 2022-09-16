package com.lanchonete.sistema.model;

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
	private BigDecimal taxaServico;
	private BigDecimal valorPago;
	private BigDecimal troco;
	
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
			this.listaLanche.forEach(lanche -> this.taxaServico = this.taxaServico.add(lanche.calculaPrecoPrato()));
		}
		if (!(this.listaPizza.isEmpty())) {
			this.listaPizza.forEach(pizza -> this.taxaServico = this.taxaServico.add(pizza.calculaPrecoPrato()));
		}
		if (!(this.listaSalgadinho.isEmpty())) {
			this.listaSalgadinho.forEach(salgadinho -> this.taxaServico.add(salgadinho.calculaPrecoPrato()));
		}
		System.out.println(this.taxaServico);
		return this.taxaServico;
	}
	
	public BigDecimal calcularTroco(BigDecimal valorPago) {
		if (valorPago.compareTo(this.taxaServico) == 1) {
			System.out.println("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.taxaServico);
			this.troco = BigDecimal.ZERO;
		} else if (valorPago.compareTo(this.taxaServico) == 0) {
			this.troco = BigDecimal.ZERO;
		} else if (valorPago.compareTo(this.taxaServico) == -1) {
			this.troco = valorPago.subtract(this.taxaServico);
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
		return taxaServico;
	}
	public void setTaxaServico(BigDecimal taxaServico) {
		this.taxaServico = taxaServico;
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
}
