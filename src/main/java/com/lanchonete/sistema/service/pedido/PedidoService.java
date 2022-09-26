package com.lanchonete.sistema.service.pedido;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.pedido.PedidoDto;
import com.lanchonete.sistema.dto.pedido.PedidoDtoCompleto;
import com.lanchonete.sistema.form.pedido.PedidoForm;
import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.item.Salgadinho;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;
import com.lanchonete.sistema.repository.item.LancheRepository;
import com.lanchonete.sistema.repository.item.PizzaRepository;
import com.lanchonete.sistema.repository.item.SalgadinhoRepository;
import com.lanchonete.sistema.repository.pedido.pedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private pedidoRepository pedidoRepository;
	
	@Autowired
	LancheRepository lancheRepository;
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	SalgadinhoRepository salgadinhoRepository;

	// get pedidos
	public Page<PedidoDto> listarPedidos(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findAll(paginacao);
		return PedidoDto.converter(pedidos);
	}

	// get por id
	public ResponseEntity<PedidoDto> listarPedidoPorId(Long pedidoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			return ResponseEntity.ok(PedidoDto.converterUmPedido(pedidoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	} 
	
	//get por id completo
	public ResponseEntity<PedidoDtoCompleto> listarPedidoCompletoPorId(Long pedidoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			List<Lanche> findListLanchesPedido = lancheRepository.findListLanchesPedido(pedidoId);
			List<Pizza> findListPizzasPedido = pizzaRepository.findListPizzasPedido(pedidoId);
			List<Salgadinho> findListSalgadinhosPedido = salgadinhoRepository.findListSalgadinhosPedido(pedidoId);
			
			return ResponseEntity.ok(PedidoDtoCompleto.converterUmPedido(pedidoOptional.get(), findListLanchesPedido, 
					findListPizzasPedido, findListSalgadinhosPedido));
		}
		return ResponseEntity.notFound().build();
	}
	
	// Get Aberto
	public Page<PedidoDto> listarPedidosAbertos(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.ABERTO, paginacao);
		if(pedidos != null) {
			return PedidoDto.converter(pedidos);
		}
		return null;
	} 

	//Get Processando
	public Page<PedidoDto> listarPedidosProcessando(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.PROCESSANDO, paginacao);
		if(pedidos != null) {
			return PedidoDto.converter(pedidos);
		}
		return null;
	} 

	//Get PagoFinalizado
	public Page<PedidoDto> listarPedidosPagoFinalizado(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.PAGOFINALIZADO, paginacao);
		if(pedidos != null) {
			return PedidoDto.converter(pedidos);
		}
		return null;
	} 

	// cadastrar pedido
	public ResponseEntity<PedidoDto> cadastrarPedido(PedidoForm pedidoForm, UriComponentsBuilder uriBuilder) {
		Pedido pedido = pedidoForm.converter();
		pedidoRepository.save(pedido);
		URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}

	// atualizar pedido
	public ResponseEntity<PedidoDto> atualizarPedido(Long pedidoId, PedidoForm pedidoForm, UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
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

	// retorna o troco no corpo da mensagem 
	public ResponseEntity<BigDecimal> retornaCalculoTrocoPedido(Long pedidoId, BigDecimal valorPago) throws Exception {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			
			if(pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
				return ResponseEntity.ok(pedido.calcularTroco(valorPago));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// deletar pedido
	public ResponseEntity<?> removerPedido(Long pedidoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			// primeiro tem de verificar se tem lanche para poder deletar 
			

			List<Lanche> lancheOptional = lancheRepository.findListLanchesPedido(pedidoId);
			List<Pizza> pizzaOptional = pizzaRepository.findListPizzasPedido(pedidoId);
			List<Salgadinho> salgadinhoOptional = salgadinhoRepository.findListSalgadinhosPedido(pedidoId);
			
			if (lancheOptional != null) {
				lancheRepository.deleteLancheByPedidoId(pedidoId);				
			}
			
			if (pizzaOptional != null) {
				pizzaRepository.deletePizzaByPedidoId(pedidoId);			
			}
			
			if (salgadinhoOptional != null) {
				salgadinhoRepository.deleteSalgadinhoByPedidoId(pedidoId);				
			}
			
			pedidoRepository.deleteById(pedidoId);

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	

}
