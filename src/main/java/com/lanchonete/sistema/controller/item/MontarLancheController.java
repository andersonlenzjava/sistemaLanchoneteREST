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

import com.lanchonete.sistema.dto.item.MontarLancheDto;
import com.lanchonete.sistema.form.item.MontarLancheForm;
import com.lanchonete.sistema.service.item.MontarLancheService;

@RestController
@RequestMapping("/pedido/lanches")
public class MontarLancheController {
	
	@Autowired
	private MontarLancheService montarLancheService;
	
	@GetMapping
	public Page<MontarLancheDto> listarLanchesPedido(@RequestParam(required = true) Long pedidoId, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return montarLancheService.listarLanchesPedido(pedidoId, paginacao);
	}
	
	@GetMapping("/{lancheId}") 
	public ResponseEntity<MontarLancheDto> listarLanchePedidoPorId(@PathVariable Long lancheId, 
			@RequestParam(required = true) Long pedidoId) {
		return montarLancheService.listarLanchePorId(pedidoId, lancheId);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MontarLancheDto> cadastrarLanche(@RequestBody @Valid MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		return montarLancheService.cadastrarLanche(montarLancheForm, uriBuilder);
	}
	
	@PutMapping("/{lancheId}")
	@Transactional
	public ResponseEntity<MontarLancheDto> atualizarLanche(@PathVariable Long lancheId, @RequestBody @Valid MontarLancheForm montarLancheForm) {
		return montarLancheService.atualizarLanche(lancheId, montarLancheForm);
	}
	
	@DeleteMapping("/{lancheId}")
	@Transactional
	public ResponseEntity<?> removerLanchePedido(@RequestParam(required = true) Long pedidoId, @PathVariable Long lancheId) {
		return montarLancheService.removerLanchePedido(pedidoId, lancheId);
	}

}
