package com.lanchonete.sistema.controller.pedido;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.pedido.PedidoDto;
import com.lanchonete.sistema.service.pedido.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public Page<PedidoDto> listarPedidos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return pedidoService.listarPedidos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	@GetMapping("/listLanche/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	@GetMapping("/listPizza/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	@GetMapping("/listSalgadinho/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	@GetMapping("/taxaServico/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	@GetMapping("/calculaTroco/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	//função de excluir itens da lista 
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody @Valid PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.cadastrarLanche(pedidoForm, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PedidoDto> atualizarPedido(@RequestBody @Valid PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.atualizarLanche(pedidoForm, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerPedido(@PathVariable Long id) {
		return pedidoService.removerLanche(id);
	}
	
}
