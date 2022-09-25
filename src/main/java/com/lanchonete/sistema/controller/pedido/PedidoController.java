package com.lanchonete.sistema.controller.pedido;

import java.math.BigDecimal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.pedido.PedidoDto;
import com.lanchonete.sistema.form.pedido.PedidoForm;
import com.lanchonete.sistema.service.pedido.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public Page<PedidoDto> listarPedidos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return pedidoService.listarPedidos(paginacao);
	}
	
	@GetMapping("/porId")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@RequestParam(required = true) Long pedidoId) {
		return pedidoService.listarPedidoPorId(pedidoId);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody @Valid PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.cadastrarPedido(pedidoForm, uriBuilder);
	}
	
	@PutMapping
	@Transactional 
	public ResponseEntity<PedidoDto> atualizarPedido(@RequestParam(required = true) Long pedidoId, @RequestBody @Valid PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.atualizarPedido(pedidoId, pedidoForm, uriBuilder);
	}
	
	@GetMapping("/calculaTroco/{pedidoId}")
	@Transactional
	public ResponseEntity<BigDecimal> retornaCalculoTrocoPedido(@PathVariable Long pedidoId, @RequestParam(required = true) BigDecimal valorPago) throws Exception {
		return pedidoService.retornaCalculoTrocoPedido(pedidoId, valorPago);
	} // estado pago se aprovar 

	//deletar um pedido  // remover os itens deste pedido também idependente do seu status 
	@DeleteMapping("/{pedidoId}")
	@Transactional
	public ResponseEntity<?> removerPedido(@PathVariable Long pedidoId) {
		return pedidoService.removerPedido(pedidoId);
	}
	
}
