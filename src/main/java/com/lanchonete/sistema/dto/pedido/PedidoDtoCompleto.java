package com.lanchonete.sistema.dto.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.item.Salgadinho;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;

public class PedidoDtoCompleto {

	Long pedidoId;
	private List<Lanche> listaLanche = new ArrayList<>();
	private List<Pizza> listaPizza = new ArrayList<>();
	private List<Salgadinho> listaSalgadinho = new ArrayList<>();
	private String nomeCliente;
	private BigDecimal valorTotalServico = BigDecimal.ZERO;
	private BigDecimal valorPago = BigDecimal.ZERO;
	private BigDecimal troco = BigDecimal.ZERO;
	private StatusPedido statusPedido = StatusPedido.ABERTO;
	
	public PedidoDtoCompleto(Pedido pedido, List<Lanche> lanches, List<Pizza> pizzas, List<Salgadinho> salgadinhos) {
		this.pedidoId = pedido.getId();
		this.listaLanche = lanches;
		this.listaPizza = pizzas;
		this.listaSalgadinho = salgadinhos;
		this.nomeCliente = pedido.getNomeCliente();
		this.valorTotalServico = pedido.getTaxaServico();
		this.valorPago = pedido.getValorPago();
		this.troco = pedido.getTroco();
		this.statusPedido = pedido.getStatusPedido();
	}

	public Long getPedidoId() {
		return pedidoId;
	}
	public void setId(Long pedidoId) {
		this.pedidoId = pedidoId;
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
	public BigDecimal getValorTotalServico() {
		return valorTotalServico;
	}
	public void setValorTotalServico(BigDecimal valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
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
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public static PedidoDtoCompleto converterUmPedido(Pedido pedido, List<Lanche> listaLanche, List<Pizza> listaPizza, List<Salgadinho> listaSalgadinho) {
		return new PedidoDtoCompleto(pedido, listaLanche, listaPizza, listaSalgadinho);
	}
}
