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

public class PedidoDto {

	Long id;
	private List<Lanche> listaLanche = new ArrayList<>();
	private List<Pizza> listaPizza = new ArrayList<>();
	private List<Salgadinho> listaSalgadinho = new ArrayList<>();
	private String nomeCliente;
	private BigDecimal valorTotalServico;
	private BigDecimal valorPago;
	private BigDecimal troco;
	private StatusPedido statusPedido = StatusPedido.ABERTO;
	
	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		pedido.calculaTaxaServico();
		this.listaLanche = pedido.getListaLanche();
		this.listaPizza = pedido.getListaPizza();
		this.nomeCliente = pedido.getNomeCliente();
		this.valorTotalServico = pedido.getTaxaServico();
		this.valorPago = pedido.getValorPago();
		this.troco = pedido.getTroco();
		this.statusPedido = pedido.getStatusPedido();
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
	
	public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
		return pedidos.map(PedidoDto::new);
	}
	public static PedidoDto converterUmPedido(Pedido pedido) {
		return new PedidoDto(pedido);
	}
}
