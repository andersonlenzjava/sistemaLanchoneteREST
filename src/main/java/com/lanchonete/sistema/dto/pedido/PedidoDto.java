package com.lanchonete.sistema.dto.pedido;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.pedido.Pedido;

public class PedidoDto {

	
	
	public PedidoDto(Pedido pedido) {
		// TODO Auto-generated constructor stub
	}

	public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
		// TODO Auto-generated method stub
		return null;
	}

	public static PedidoDto converterUmPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

}
