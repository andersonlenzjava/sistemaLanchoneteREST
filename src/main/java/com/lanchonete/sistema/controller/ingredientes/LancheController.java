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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.ingredientes.LancheMolhoDto;
import com.lanchonete.sistema.dto.ingredientes.LancheRecheioDto;
import com.lanchonete.sistema.dto.ingredientes.LancheTipoPaoDto;
import com.lanchonete.sistema.form.ingredientes.LancheMolhoForm;
import com.lanchonete.sistema.form.ingredientes.LancheRecheioForm;
import com.lanchonete.sistema.form.ingredientes.LancheTipoPaoForm;
import com.lanchonete.sistema.service.ingredientes.LancheService;

@RestController
@RequestMapping("/ingrediente/lanches")
public class LancheController {

	@Autowired
	private LancheService lancheService;
	
	@GetMapping("/molho")
	public Page<LancheMolhoDto> listarMolho(@RequestParam(required = false) String tipoMolho, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return lancheService.listarLancheMolho(tipoMolho, paginacao);
	}
	
	@GetMapping("/molho/{id}")
	public ResponseEntity<LancheMolhoDto> detalharMolho(@PathVariable Long id) {
		return lancheService.detalharLancheMolhoPorId(id);
	}

	@PostMapping("/molho")
	@Transactional
	public ResponseEntity<LancheMolhoDto> cadastrarMolho(@RequestBody @Valid LancheMolhoForm lancheMolhoForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return lancheService.cadastrarLancheMolho(lancheMolhoForm, uriBuilder);
	}
	
	@PutMapping("/molho/{id}")
	@Transactional
	public ResponseEntity<LancheMolhoDto> atualizarLancheMolho(@PathVariable Long id, @RequestBody @Valid LancheMolhoForm lancheMolhoForm) {
		return lancheService.atualizarLancheMolho(id, lancheMolhoForm);
	}
	

	@DeleteMapping("/molho/{id}")
	@Transactional
	public ResponseEntity<?> removerLancheMolho(@PathVariable Long id) {
		return lancheService.removerLancheMolho(id);
	}
	
	//================================================================================================================//
	
	@GetMapping("/recheio")
	public Page<LancheRecheioDto> listarLancheRecheio(@RequestParam(required = false) String tipoRecheio, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return lancheService.listarLancheRecheio(tipoRecheio, paginacao);
	}
	
	@GetMapping("/recheio/{id}")
	public ResponseEntity<LancheRecheioDto> detalharLancheRecheio(@PathVariable Long id) {
		return lancheService.detalharLancheRecheioPorId(id);
	}

	@PostMapping("/recheio")
	@Transactional
	public ResponseEntity<LancheRecheioDto> cadastraLancheRecheio(@RequestBody @Valid LancheRecheioForm lancheRecheioForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return lancheService.cadastrarLancheRecheio(lancheRecheioForm, uriBuilder);
	}
	
	@PutMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<LancheRecheioDto> atualizarLancheRecheio(@PathVariable Long id, @RequestBody @Valid LancheRecheioForm lancheRecheioForm) {
		return lancheService.atualizarLancheRecheio(id, lancheRecheioForm);
	}

	@DeleteMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<?> removerLancheRecheio(@PathVariable Long id) {
		return lancheService.removerLancheRecheio(id);
	}
	
	//================================================================================================================//
	
	@GetMapping("/tipoPao")
	public Page<LancheTipoPaoDto> listarLancheTipoPao(@RequestParam(required = false) String tipoPao, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return lancheService.listarLancheTipoPao(tipoPao, paginacao);
	}
	
	@GetMapping("/tipoPao/{id}")
	public ResponseEntity<LancheTipoPaoDto> detalharLancheTipoPao(@PathVariable Long id) {
		return lancheService.detalharLancheTipoPaoPorId(id);
	}

	@PostMapping("/tipoPao")
	@Transactional
	public ResponseEntity<LancheTipoPaoDto> cadastrarLancheTipoPao(@RequestBody @Valid LancheTipoPaoForm lancheTipoPaoForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return lancheService.cadastrarLancheTipoPao(lancheTipoPaoForm, uriBuilder);
	}
	
	@PutMapping("/tipoPao/{id}")
	@Transactional
	public ResponseEntity<LancheTipoPaoDto> atualizarLancheTipoPao(@PathVariable Long id, @RequestBody @Valid LancheTipoPaoForm lancheTipoPaoForm) {
		return lancheService.atualizarLancheTipoPao(id, lancheTipoPaoForm);
	}

	@DeleteMapping("/tipoPao/{id}")
	@Transactional
	public ResponseEntity<?> removerLancheTipoPao(@PathVariable Long id) {
		return lancheService.removerLancheTipoPao(id);
	}
	
}
