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

import com.lanchonete.sistema.dto.ingredientes.SalgadinhoMassaDto;
import com.lanchonete.sistema.dto.ingredientes.SalgadinhoRecheioDto;
import com.lanchonete.sistema.dto.ingredientes.SalgadinhoTipoPreparoDto;
import com.lanchonete.sistema.form.ingredientes.SalgadinhoMassaForm;
import com.lanchonete.sistema.form.ingredientes.SalgadinhoRecheioForm;
import com.lanchonete.sistema.form.ingredientes.SalgadinhoTipoPreparoForm;
import com.lanchonete.sistema.service.ingredientes.SalgadinhoService;

@RestController
@RequestMapping("/lanches")
public class SalgadinhoController {

	@Autowired
	private SalgadinhoService salgadinhoService;
	
	@GetMapping("/massa")
	public Page<SalgadinhoMassaDto> listarSalgadinhoMassa(@RequestParam(required = false) String tipoMassa, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return salgadinhoService.listarSalgadinhoMassa(tipoMassa, paginacao);
	}
	
	@GetMapping("/massa/{id}")
	public ResponseEntity<SalgadinhoMassaDto> detalharSalgadinhoMassa(@PathVariable Long id) {
		return salgadinhoService.detalharSalgadinhoMassa(id);
	}

	@PostMapping("/massa")
	@Transactional
	public ResponseEntity<SalgadinhoMassaDto> cadastrarSalgadinhoMassa(@RequestBody @Valid SalgadinhoMassaForm salgadinhoMassaForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return salgadinhoService.cadastrarSalgadinhoMassa(salgadinhoMassaForm, uriBuilder);
	}
	
	@PutMapping("/massa/{id}")
	@Transactional
	public ResponseEntity<SalgadinhoMassaDto> atualizarPizzaRecheio(@PathVariable Long id, @RequestBody @Valid SalgadinhoMassaForm salgadinhoMassaForm) {
		return salgadinhoService.atualizarPizzaRecheio(id, salgadinhoMassaForm);
	}

	@DeleteMapping("/massa/{id}")
	@Transactional
	public ResponseEntity<?> removerSalgadinhoMassa(@PathVariable Long id) {
		return salgadinhoService.removerSalgadinhoMassa(id);
	}
	
	//================================================================================================================//
	
	@GetMapping("/recheio")
	public Page<SalgadinhoRecheioDto> listarSalgadinhoRecheio(@RequestParam(required = false) String tipoRecheio, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return salgadinhoService.listarSalgadinhoRecheio(tipoRecheio, paginacao);
	}
	
	@GetMapping("/recheio/{id}")
	public ResponseEntity<SalgadinhoRecheioDto> detalharSalgadinhoRecheio(@PathVariable Long id) {
		return salgadinhoService.detalharSalgadinhoRecheioPorId(id);
	}

	@PostMapping("/recheio")
	@Transactional
	public ResponseEntity<SalgadinhoRecheioDto> cadastraSalgadinhoRecheio(@RequestBody @Valid SalgadinhoRecheioForm salgadinhoRecheioForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return salgadinhoService.cadastrarSalgadinhoRecheio(salgadinhoRecheioForm, uriBuilder);
	}
	
	@PutMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<SalgadinhoRecheioDto> atualizarPizzaRecheio(@PathVariable Long id, @RequestBody @Valid SalgadinhoRecheioForm salgadinhoRecheioForm) {
		return salgadinhoService.atualizarPizzaRecheio(id, salgadinhoRecheioForm);
	}

	@DeleteMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<?> removerSalgadinhoRecheio(@PathVariable Long id) {
		return salgadinhoService.removerSalgadinhoRecheio(id);
	}
	
	//================================================================================================================//
	
	@GetMapping("/tipoPreparo")
	public Page<SalgadinhoTipoPreparoDto> listarSalgadinhoTipoPreparo(@RequestParam(required = false) String tipoPreparo, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return salgadinhoService.listarSalgadinhoTipoPreparo(tipoPreparo, paginacao);
	}
	
	@GetMapping("/tipoPreparo/{id}")
	public ResponseEntity<SalgadinhoTipoPreparoDto> detalharSalgadinhoTipoPreparo(@PathVariable Long id) {
		return salgadinhoService.detalharSalgadinhoTipoPreparoPorId(id);
	}

	@PostMapping("/tipoPreparo")
	@Transactional
	public ResponseEntity<SalgadinhoTipoPreparoDto> cadastrarSalgadinhoTipoPreparo(@RequestBody @Valid SalgadinhoTipoPreparoForm salgadinhoTipoPreparoForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return salgadinhoService.cadastrarSalgadinhoTipoPreparo(salgadinhoTipoPreparoForm, uriBuilder);
	}
	
	@PutMapping("/tipoPreparo/{id}")
	@Transactional
	public ResponseEntity<SalgadinhoTipoPreparoDto> atualizarPizzaRecheio(@PathVariable Long id, @RequestBody @Valid SalgadinhoTipoPreparoForm salgadinhoTipoPreparoForm) {
		return salgadinhoService.atualizarPizzaRecheio(id, salgadinhoTipoPreparoForm);
	}

	@DeleteMapping("/tipoPreparo/{id}")
	@Transactional
	public ResponseEntity<?> removerSalgadinhoTipoPreparo(@PathVariable Long id) {
		return salgadinhoService.removerSalgadinhoTipoPreparo(id);
	}
	
}
