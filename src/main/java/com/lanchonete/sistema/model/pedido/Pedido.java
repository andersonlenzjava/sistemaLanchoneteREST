package com.lanchonete.sistema.model.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;
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
		this.listaPizza.add(pizza); // adiciona na lista 
		this.valorTotalServico = this.valorTotalServico.add(pizza.getItem().getTotalItem()); // adiciona no total do pedido 
		this.statusPedido = StatusPedido.PROCESSANDO;
	}

	public void adicionaSalgadinho(Salgadinho salgadinho) {
		this.valorTotalServico = this.valorTotalServico.add(salgadinho.getItem().getTotalItem());
	}

	public void adicionaLanche(Lanche lanche) {
		this.listaLanche.add(lanche);
		this.valorTotalServico = this.valorTotalServico.add(lanche.getItem().getTotalItem());
		this.statusPedido = StatusPedido.PROCESSANDO;
	}
	
	public Lanche atualizarLanche (Long id, LancheMolho lancheMolho, LancheRecheio lancheRecheio, LancheTipoPao lancheTipoPao) {
		Lanche lanche = this.listaLanche.get(Math.toIntExact(id));
		this.valorTotalServico = this.valorTotalServico.subtract(lanche.getItem().getTotalItem());
		lanche = new Lanche(lancheTipoPao, lancheRecheio, lancheMolho);
		this.valorTotalServico = this.valorTotalServico.add(lanche.getItem().getTotalItem());
		this.listaLanche.set(Math.toIntExact(id), lanche);
		return lanche;
	}
	
	public Pizza atualizarPizza(Long id, PizzaBorda pizzaBorda, PizzaMolho pizzaMolho, PizzaRecheio pizzaRecheio) {
		Pizza pizza = this.listaPizza.get(Math.toIntExact(id));
		this.valorTotalServico = this.valorTotalServico.subtract(pizza.getItem().getTotalItem());
		pizza = new Pizza(pizzaBorda, pizzaMolho, pizzaRecheio);
		this.valorTotalServico = this.valorTotalServico.add(pizza.getItem().getTotalItem());
		this.listaPizza.set(Math.toIntExact(id), pizza);
		return pizza;
	}
	
	public Salgadinho atualizarSalgadinho(Long id, SalgadinhoMassa salgadinhoMassa,
			SalgadinhoRecheio salgadinhoRecheio, SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
		Salgadinho salgadinho = this.listaSalgadinho.get(Math.toIntExact(id));
		this.valorTotalServico = this.valorTotalServico.subtract(salgadinho.getItem().getTotalItem());
		salgadinho = new Salgadinho(salgadinhoMassa, salgadinhoRecheio, salgadinhoTipoPreparo);
		this.valorTotalServico = this.valorTotalServico.add(salgadinho.getItem().getTotalItem());
		this.listaSalgadinho.set(Math.toIntExact(id), salgadinho);
		return salgadinho;
	}
	
	public void removerLanche(int id) {
		Lanche lanche = this.listaLanche.get(Math.toIntExact(id));
		this.valorTotalServico = this.valorTotalServico.subtract(lanche.getItem().getTotalItem());
		this.listaLanche.remove(id);
	}

	public void removerPizza(int id) {
		Pizza pizza = this.listaPizza.get(Math.toIntExact(id));
		this.valorTotalServico = this.valorTotalServico.subtract(pizza.getItem().getTotalItem());
		this.listaLanche.remove(id);
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
