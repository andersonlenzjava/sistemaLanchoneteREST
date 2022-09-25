package com.lanchonete.sistema.controller.item;

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

import com.lanchonete.sistema.dto.item.MontarPizzaDto;
import com.lanchonete.sistema.form.item.MontarPizzaForm;
import com.lanchonete.sistema.service.item.MontarPizzaService;

@RestController
@RequestMapping("/pedido/pizzas")
public class MontarPizzaController {
	
	@Autowired
	private MontarPizzaService montarPizzaService;
	
	@GetMapping
	public Page<MontarPizzaDto> listarPizzasPedido(@RequestParam(required = true) Long pedidoId, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return montarPizzaService.listarPizzasPedido(pedidoId, paginacao);
	}
	
	@GetMapping("/{pizzaId}")  
	public ResponseEntity<MontarPizzaDto> listarPizzaPedidoPorId(@PathVariable Long pizzaId,
			@RequestParam(required = true) Long pedidoId) {
		return montarPizzaService.listarPizzaPedidoPorId(pedidoId, pizzaId);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MontarPizzaDto> cadastrarPizza(@RequestBody @Valid MontarPizzaForm montarPizzaForm,
			UriComponentsBuilder uriBuilder) {
		return montarPizzaService.cadastrarPizza(montarPizzaForm, uriBuilder);
	}
	
	@PutMapping("/{pizzaId}")
	@Transactional
	public ResponseEntity<MontarPizzaDto> atualizarPizza(@PathVariable Long pizzaId, @RequestBody @Valid MontarPizzaForm montarPizzaForm) {
		return montarPizzaService.atualizarPizza(pizzaId, montarPizzaForm);
	}
	
	@DeleteMapping("/{pizzaId}")
	@Transactional
	public ResponseEntity<?> removerPizzaPedido(@RequestParam(required = true) Long pedidoId, @PathVariable Long pizzaId) {
		return montarPizzaService.removerPizzaPedido(pedidoId, pizzaId);
	}
	
}
