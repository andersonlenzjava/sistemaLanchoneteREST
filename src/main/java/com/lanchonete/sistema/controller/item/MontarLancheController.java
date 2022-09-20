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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.item.MontarLancheDto;
import com.lanchonete.sistema.form.item.MontarLancheForm;
import com.lanchonete.sistema.service.item.MontarLancheService;

@RestController
@RequestMapping("/montarLanche")
public class MontarLancheController {
	
	@Autowired
	private MontarLancheService montarLancheService;
	
	@GetMapping
	public Page<MontarLancheDto> listarLanches(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return montarLancheService.listarLanches();
	}
	
	@GetMapping("/{id}") //AQUI JÁ TRAZ TODAS AS INFO DO ITEM TAMBÉM 
	public ResponseEntity<MontarLancheDto> listarLanchePorId(@PathVariable Long id) {
		return montarLancheService.listarLanchePorId(id);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MontarLancheDto> cadastrarLanche(@RequestBody @Valid MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		return montarLancheService.cadastrarLanche(montarLancheForm, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MontarLancheDto> atualizarLanche(@RequestBody @Valid MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		return montarLancheService.atualizarLanche(montarLancheForm, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerLanche(@PathVariable Long id) {
		return montarLancheService.removerLanche(id);
	}
	

}
