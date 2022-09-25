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

import com.lanchonete.sistema.dto.item.MontarSalgadinhoDto;
import com.lanchonete.sistema.form.item.MontarSalgadinhoForm;
import com.lanchonete.sistema.service.item.MontarSalgadinhoService;

@RestController
@RequestMapping("/pedido/salgadinhos")
public class MontarSalgadinhoController {
	
	@Autowired
	private MontarSalgadinhoService montarSalgadinhoService;
	
	@GetMapping
	public Page<MontarSalgadinhoDto> listarSalgadinhosPedido(@RequestParam(required = true) Long pedidoId, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return montarSalgadinhoService.listarSalgadinhosPedido(pedidoId, paginacao);
	}
	
	@GetMapping("/{salgadinhoId}") 
	public ResponseEntity<MontarSalgadinhoDto> listarSalgadinhoPedidoPorId(@PathVariable Long salgadinhoId, 
			@RequestParam(required = true) Long pedidoId) {
		return montarSalgadinhoService.listarSalgadinhoPedidoPorId(pedidoId, salgadinhoId);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MontarSalgadinhoDto> cadastrarSalgadinho(@RequestBody @Valid MontarSalgadinhoForm montarSalgadinhoForm,
			UriComponentsBuilder uriBuilder) {
		return montarSalgadinhoService.cadastrarSalgadinho(montarSalgadinhoForm, uriBuilder);
	}
	
	@PutMapping("/{salgadinhoId}")
	@Transactional
	public ResponseEntity<MontarSalgadinhoDto> atualizarSalgadinho(@PathVariable Long salgadinhoId, @RequestBody @Valid MontarSalgadinhoForm montarSalgadinhoForm) {
		return montarSalgadinhoService.atualizarSalgadinho(salgadinhoId, montarSalgadinhoForm);
	}
	
	@DeleteMapping("/{salgadinhoId}")
	@Transactional
	public ResponseEntity<?> removerSalgadinhoPedido(@RequestParam(required = true) Long pedidoId, @PathVariable Long salgadinhoId) {
		return montarSalgadinhoService.removerSalgadinhoPedido(pedidoId, salgadinhoId);
	}
	
}
