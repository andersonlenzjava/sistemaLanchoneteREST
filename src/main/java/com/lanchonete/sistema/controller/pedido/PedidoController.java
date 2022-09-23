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

import com.lanchonete.sistema.dto.item.MontarLancheDto;
import com.lanchonete.sistema.dto.item.MontarPizzaDto;
import com.lanchonete.sistema.dto.item.MontarSalgadinhoDto;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> listarPedidoPorId(@PathVariable Long id) {
		return pedidoService.listarPedidoPorId(id);
	}
	
	@GetMapping("/listLanche/{id}")
	public Page<MontarLancheDto> listarPedidoLanches(@PathVariable Long id, @PageableDefault(sort = "id", 
	direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) throws Exception {
		return pedidoService.listarPedidoLanches(id);
	}
	
	@GetMapping("/listPizza/{id}")
	public Page<MontarPizzaDto> listarPedidoPizzas(@PathVariable Long id, @PageableDefault(sort = "id", 
			direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) throws Exception {
		return pedidoService.listarPedidoPizzas(id);
	}
	
	@GetMapping("/listSalgadinho/{id}")
	public Page<MontarSalgadinhoDto> listarPedidoSalgadinhos(@PathVariable Long id, @PageableDefault(sort = "id", 
			direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) throws Exception {
		return pedidoService.listarPedidoSalgadinhos(id);
	}
	
	@GetMapping("/calculaTroco/{id}")
	@Transactional
	public ResponseEntity<BigDecimal> retornaCalculoTrocoPedido(@PathVariable Long id, @RequestParam(required = true) BigDecimal valorPago) throws Exception {
		return pedidoService.retornaCalculoTrocoPedido(id, valorPago);
	} // estado pago se aprovar 
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody @Valid PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.cadastrarPedido(pedidoForm, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PedidoDto> atualizarPedido(@PathVariable Long id, @RequestBody @Valid PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.atualizarPedido(id, pedidoForm, uriBuilder);
	}
	
	//deletar um pedido 
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerPedido(@PathVariable Long id) {
		return pedidoService.removerPedido(id);
	}
	
	//deletar um lanche
	@DeleteMapping("/removerLanchePedido")
	@Transactional
	public ResponseEntity<?> removerLanche(@RequestParam(required = true) Long pedidoId, @RequestParam(required = true) Long lancheId) {
		return pedidoService.removerLanche(pedidoId, lancheId);
	}
	
	//deletar uma pizza
	@DeleteMapping("/removerPizzaPedido")
	@Transactional
	public ResponseEntity<?> removerPizza(@RequestParam(required = true) Long pedidoId, @RequestParam(required = true) Long pizzaId) {
		return pedidoService.removerPizza(pedidoId, pizzaId);
	}
		
	//deletar um salgadinho
	@DeleteMapping("/removerSalgadinhoPedido")
	@Transactional
	public ResponseEntity<?> removerSalgadinho(@RequestParam(required = true) Long pedidoId, @RequestParam(required = true) Long salgadinhoId) {
		return pedidoService.removerSalgadinho(pedidoId, salgadinhoId);
	}	
	
}
