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

import com.lanchonete.sistema.dto.ingredientes.PizzaBordaDto;
import com.lanchonete.sistema.dto.ingredientes.PizzaMolhoDto;
import com.lanchonete.sistema.dto.ingredientes.PizzaRecheioDto;
import com.lanchonete.sistema.form.ingredientes.PizzaBordaForm;
import com.lanchonete.sistema.form.ingredientes.PizzaMolhoForm;
import com.lanchonete.sistema.form.ingredientes.PizzaRecheioForm;
import com.lanchonete.sistema.service.ingredientes.PizzaService;

@RestController
@RequestMapping("/ingrediente/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/borda")
	public Page<PizzaBordaDto> listarPizzaBorda(@RequestParam(required = false) String tipoBorda, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return pizzaService.listarPizzaBorda(tipoBorda, paginacao);
	}
	
	@GetMapping("/borda/{id}")
	public ResponseEntity<PizzaBordaDto> detalharPizzaBorda(@PathVariable Long id) {
		return pizzaService.detalharPizzaBordaPorId(id);
	}

	@PostMapping("/borda")
	@Transactional
	public ResponseEntity<PizzaBordaDto> cadastrarPizzaBorda(@RequestBody @Valid PizzaBordaForm pizzaBordaForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return pizzaService.cadastrarPizzaBorda(pizzaBordaForm, uriBuilder);
	}
	
	@PutMapping("/borda/{id}")
	@Transactional
	public ResponseEntity<PizzaBordaDto> atualizarPizzaBorda(@PathVariable Long id, @RequestBody @Valid PizzaBordaForm pizzaBordaForm) {
		return pizzaService.atualizarLancheTipoPao(id, pizzaBordaForm);
	}

	@DeleteMapping("/borda/{id}")
	@Transactional
	public ResponseEntity<?> removerPizzaBorda(@PathVariable Long id) {
		return pizzaService.removerPizzaBorda(id);
	}
	
//================================================================================================================//
	
	@GetMapping("/molho")
	public Page<PizzaMolhoDto> listarPizzaMolho(@RequestParam(required = false) String tipoMolho, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return pizzaService.listarPizzaMolho(tipoMolho, paginacao);
	}
	
	@GetMapping("/molho/{id}")
	public ResponseEntity<PizzaMolhoDto> detalharPizzaMolho(@PathVariable Long id) {
		return pizzaService.detalharPizzaMolhoPorId(id);
	}

	@PostMapping("/molho")
	@Transactional
	public ResponseEntity<PizzaMolhoDto> cadastraPizzaMolho(@RequestBody @Valid PizzaMolhoForm pizzaMolhoForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return pizzaService.cadastrarPizzaMolho(pizzaMolhoForm, uriBuilder);
	}
	
	@PutMapping("/molho/{id}")
	@Transactional
	public ResponseEntity<PizzaMolhoDto> atualizarPizzaMolho(@PathVariable Long id, @RequestBody @Valid PizzaMolhoForm pizzaMolhoForm) {
		return pizzaService.atualizarLancheTipoPao(id, pizzaMolhoForm);
	}

	@DeleteMapping("/molho/{id}")
	@Transactional
	public ResponseEntity<?> removerPizzaMolho(@PathVariable Long id) {
		return pizzaService.removerPizzaMolho(id);
	}
	
//================================================================================================================//
	
	@GetMapping("/recheio")
	public Page<PizzaRecheioDto> listarPizzaRecheio(@RequestParam(required = false) String tipoRecheio, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return pizzaService.listarPizzaRecheio(tipoRecheio, paginacao);
	}
	
	@GetMapping("/recheio/{id}")
	public ResponseEntity<PizzaRecheioDto> detalharPizzaRecheio(@PathVariable Long id) {
		return pizzaService.detalharPizzaRecheioPorId(id);
	}

	@PostMapping("/recheio")
	@Transactional
	public ResponseEntity<PizzaRecheioDto> cadastrarPizzaRecheio(@RequestBody @Valid PizzaRecheioForm pizzaRecheioForm,
			UriComponentsBuilder uriBuilder) throws Exception {
		return pizzaService.cadastrarPizzaRecheio(pizzaRecheioForm, uriBuilder);
	}
	
	@PutMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<PizzaRecheioDto> atualizarPizzaRecheio(@PathVariable Long id, @RequestBody @Valid PizzaRecheioForm pizzaRecheioForm) {
		return pizzaService.atualizarPizzaRecheio(id, pizzaRecheioForm);
	}

	@DeleteMapping("/recheio/{id}")
	@Transactional
	public ResponseEntity<?> removerPizzaRecheio(@PathVariable Long id) {
		return pizzaService.removerPizzaRecheio(id);
	}
	
}
