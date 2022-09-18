package com.lanchonete.sistema.controller.ingredientes;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.service.ingredientes.LancheService;

@RestController
@RequestMapping("/lanches")
public class LancheController {

	@Autowired
	private LancheService lancheService;
	
	@GetMapping("/molho")
	public Page<IngredienteDto> listarMolho(@RequestParam(required = false) String tipoMolho, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return lancheService.listarMolho(tipoMolho, paginacao);
	}
	
	@GetMapping("/molho/{id}")
	public ResponseEntity<IngredienteDto> detalharMolho(@PathVariable Long id) {
		return lancheService.detalharMolhoPorId(id);
	}

	@PostMapping("/molho")
	@Transactional
	public ResponseEntity<IngredienteDto> cadastrarMolho(@RequestBody @Valid IngredienteForm ingredienteForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return lancheService.cadastrarMolho(ingredienteForm, uriBuilder);
	}

	@DeleteMapping("/molho/{id}")
	@Transactional
	public ResponseEntity<?> removerMolho(@PathVariable Long id) {
		return lancheService.removerMolho(id);
	}
	
	//================================================================================================================//
	
	@GetMapping("/recheio")
	public Page<IngredienteDto> listarRecheio(@RequestParam(required = false) String tipoRecheio, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return lancheService.listarRecheio(tipoRecheio, paginacao);
	}
	
	@GetMapping("/recheio/{id}")
	public ResponseEntity<IngredienteDto> detalharRecheio(@PathVariable Long id) {
		return lancheService.detalharRecheioPorId(id);
	}

	@PostMapping("/recheio")
	@Transactional
	public ResponseEntity<IngredienteDto> cadastrarRecheio(@RequestBody @Valid IngredienteForm ingredienteForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return lancheService.cadastrarRecheio(ingredienteForm, uriBuilder);
	}

	@DeleteMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<?> removerRecheio(@PathVariable Long id) {
		return lancheService.removerRecheio(id);
	}
	
	//================================================================================================================//
	
	@GetMapping("/tipoPao")
	public Page<IngredienteDto> listarTipoPao(@RequestParam(required = false) String tipoPao, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return lancheService.listarTipoPao(tipoRecheio, paginacao);
	}
	
	@GetMapping("/tipoPao/{id}")
	public ResponseEntity<IngredienteDto> detalharTipoPao(@PathVariable Long id) {
		return lancheService.detalharTipoPaoPorId(id);
	}

	@PostMapping("/tipoPao")
	@Transactional
	public ResponseEntity<IngredienteDto> cadastrarTipoPao(@RequestBody @Valid IngredienteForm ingredienteForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return lancheService.cadastrarTipoPao(ingredienteForm, uriBuilder);
	}

	@DeleteMapping("/tipoPao/{id}")
	@Transactional
	public ResponseEntity<?> removerTipoPao(@PathVariable Long id) {
		return lancheService.removerTipoPao(id);
	}
	
}
