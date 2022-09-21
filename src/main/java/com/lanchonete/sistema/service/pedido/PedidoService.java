package com.lanchonete.sistema.service.pedido;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.item.MontarLancheDto;
import com.lanchonete.sistema.dto.item.MontarPizzaDto;
import com.lanchonete.sistema.dto.item.MontarSalgadinhoDto;
import com.lanchonete.sistema.dto.pedido.PedidoDto;
import com.lanchonete.sistema.form.pedido.PedidoForm;
import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.item.Salgadinho;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;
import com.lanchonete.sistema.repository.pedido.pedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private pedidoRepository pedidoRepository;

	// get pedidos
	public Page<PedidoDto> listarPedidos(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findAll(paginacao);
		return PedidoDto.converter(pedidos);
	}

	// get por id
	public ResponseEntity<PedidoDto> listarPedidoPorId(Long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isPresent()) {
			return ResponseEntity.ok(PedidoDto.converterUmPedido(pedidoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	} // CALCULAR OS TOTAIS DE VALOR 

	// cadastrar pedido
	public ResponseEntity<PedidoDto> cadastrarPedido(PedidoForm pedidoForm, UriComponentsBuilder uriBuilder) {
		Pedido pedido = pedidoForm.converter();
		pedidoRepository.save(pedido);
		URI uri = uriBuilder.path("/lanches/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}

	// atualizar pedido
	public ResponseEntity<PedidoDto> atualizarPedido(PedidoForm pedidoForm, UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoForm.getId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {

				pedido.setNomeCliente(pedidoForm.getNomeCliente());
				pedidoRepository.save(pedido);

				return ResponseEntity.ok(new PedidoDto(pedido));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// deletar pedido
	public ResponseEntity<?> removerPedido(Long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isPresent()) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	// Lista de lanches do pedido 
	public Page<MontarLancheDto> listarPedidoLanches(Long id) throws Exception {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			List<Lanche> lanches = pedido.getListaLanche();
			Page<Lanche> pageLanches = new PageImpl<Lanche>(lanches);// converte um list para um page
			return MontarLancheDto.converter(pageLanches);
			// agora converter um page de lanche, para um lancheDTO,
		} else {
			throw new Exception("Pedido Inesistente");
		}
	}

	//lista de Pizzas do pedido 
	public Page<MontarPizzaDto> listarPedidoPizzas(Long id) throws Exception {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			List<Pizza> pizzas = pedido.getListaPizza();
			Page<Pizza> pagePizzas = new PageImpl<Pizza>(pizzas);// converte um list para um page
			return MontarPizzaDto.converter(pagePizzas);
			// agora converter um page de lanche, para um lancheDTO,
		} else {
			throw new Exception("Pedido Inesistente");
		}
	}

	//lista de salgadinhos do pedido 
	public Page<MontarSalgadinhoDto> listarPedidoSalgadinhos(Long id) throws Exception {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			List<Salgadinho> salgadinhos = pedido.getListaSalgadinho();
			Page<Salgadinho> pageSalgadinhos = new PageImpl<Salgadinho>(salgadinhos);// converte um list para um page
			return MontarSalgadinhoDto.converter(pageSalgadinhos);
			// agora converter um page de lanche, para um lancheDTO,
		} else {
			throw new Exception("Pedido Inesistente");
		}
	}

	public ResponseEntity<BigDecimal> retornaCalculoTrocoPedido(BigDecimal valorPago, Long id) throws Exception {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			return ResponseEntity.ok(pedido.calcularTroco(valorPago));
		}
		return ResponseEntity.notFound().build();
	}

	//retorna troco 
	
	// CALCULAR OS TOTAIS, E TROCO 

}
