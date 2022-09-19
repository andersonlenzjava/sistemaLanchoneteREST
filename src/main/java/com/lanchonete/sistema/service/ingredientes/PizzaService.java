package com.lanchonete.sistema.service.ingredientes;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.ingredientes.PizzaBordaDto;
import com.lanchonete.sistema.dto.ingredientes.PizzaMolhoDto;
import com.lanchonete.sistema.dto.ingredientes.PizzaRecheioDto;
import com.lanchonete.sistema.form.ingredientes.PizzaBordaForm;
import com.lanchonete.sistema.form.ingredientes.PizzaMolhoForm;
import com.lanchonete.sistema.form.ingredientes.PizzaRecheioForm;
import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;
import com.lanchonete.sistema.repository.ingredientes.PizzaBordaRepository;
import com.lanchonete.sistema.repository.ingredientes.PizzaMolhoRepository;
import com.lanchonete.sistema.repository.ingredientes.PizzaRecheioRepository;
import com.lanchonete.sistema.validacao.ItemJaExisteException;

@Service
public class PizzaService {

//===================================================================================================================//
//PizzaBorda		
	
	@Autowired
	private PizzaBordaRepository pizzaBordaRepository;
	
	//Get
	public Page<PizzaBordaDto> listarPizzaBorda(String tipoBorda, Pageable paginacao) {
		if(tipoBorda == null) {
			Page<PizzaBorda> pizzaBorda = pizzaBordaRepository.findAll(paginacao);
			return PizzaBordaDto.converter(pizzaBorda);
		} else {
			Page<PizzaBorda> pizzaBorda = pizzaBordaRepository.findByTipoBordaIgnoreCase(tipoBorda, paginacao);
			return PizzaBordaDto.converter(pizzaBorda);
		}
	}

	//Get id
	public ResponseEntity<PizzaBordaDto> detalharPizzaBordaPorId(Long id) {
		Optional<PizzaBorda> pizzaBorda = pizzaBordaRepository.findById(id);
		if (pizzaBorda.isPresent()) {
			return ResponseEntity.ok(PizzaBordaDto.converterUmLancheMolho(pizzaBorda.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<PizzaBordaDto> cadastrarPizzaBorda(PizzaBordaForm pizzaBordaForm,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		PizzaBorda pizzaBorda = pizzaBordaForm.converter();
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findByTipoBordaIgnoreCase(pizzaBorda.getTipoBorda());
		if (pizzaBordaOptional.isEmpty()) {
			pizzaBordaRepository.save(pizzaBorda);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(pizzaBorda.getId()).toUri();
			return ResponseEntity.created(uri).body(new PizzaBordaDto(pizzaBorda));
		} else {
			throw new ItemJaExisteException("Borda já existe");
		}
	}
	
	//atualizar
	public ResponseEntity<PizzaBordaDto> atualizarLancheTipoPao(Long id,PizzaBordaForm pizzaBordaForm) {
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(id);
		if (pizzaBordaOptional.isPresent()) {
			
			PizzaBorda pizzaBorda = pizzaBordaOptional.get();
			pizzaBorda.setTipoBorda(pizzaBordaForm.getTipoBorda());
			pizzaBorda.getIngrediente().setPeso(pizzaBordaForm.getPeso());
			pizzaBorda.getIngrediente().setDataValidade(pizzaBordaForm.getDataValidade());
			pizzaBorda.getIngrediente().setPrecoVenda(pizzaBordaForm.getPrecoVenda());
			pizzaBordaRepository.save(pizzaBorda);
			
			return ResponseEntity.ok(new PizzaBordaDto(pizzaBorda));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerPizzaBorda(Long id) {
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(id);
		if (pizzaBordaOptional.isPresent()) {
			pizzaBordaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	
//===================================================================================================================//
//PizzaMolho
	
	@Autowired
	private PizzaMolhoRepository pizzaMolhoRepository;
	
	//Get
	public Page<PizzaMolhoDto> listarPizzaMolho(String tipoMolho, Pageable paginacao) {
		if(tipoMolho == null) {
			Page<PizzaMolho> pizzaMolho = pizzaMolhoRepository.findAll(paginacao);
			return PizzaMolhoDto.converter(pizzaMolho);
		} else {
			Page<PizzaMolho> pizzaMolho = pizzaMolhoRepository.findByTipoMolhoIgnoreCase(tipoMolho, paginacao);
			return PizzaMolhoDto.converter(pizzaMolho);
		}
	}

	//Get id
	public ResponseEntity<PizzaMolhoDto> detalharPizzaMolhoPorId(Long id) {
		Optional<PizzaMolho> pizzaMolho = pizzaMolhoRepository.findById(id);
		if (pizzaMolho.isPresent()) {
			return ResponseEntity.ok(PizzaMolhoDto.converterUmLancheMolho(pizzaMolho.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<PizzaMolhoDto> cadastrarPizzaMolho(PizzaMolhoForm pizzaMolhoForm,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		PizzaMolho pizzaMolho = pizzaMolhoForm.converter();
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findByTipoMolhoIgnoreCase(pizzaMolho.getTipoMolho());
		if (pizzaMolhoOptional.isEmpty()) {
			pizzaMolhoRepository.save(pizzaMolho);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(pizzaMolho.getId()).toUri();
			return ResponseEntity.created(uri).body(new PizzaMolhoDto(pizzaMolho));
		} else {
			throw new ItemJaExisteException("Molho já existe");
		}
	}
	
	//atualizar
	public ResponseEntity<PizzaMolhoDto> atualizarLancheTipoPao(Long id, PizzaMolhoForm pizzaMolhoForm) {
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(id);
		if (pizzaMolhoOptional.isPresent()) {
			
			PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
			pizzaMolho.setTipoMolho(pizzaMolhoForm.getTipoMolho());
			pizzaMolho.getIngrediente().setPeso(pizzaMolhoForm.getPeso());
			pizzaMolho.getIngrediente().setDataValidade(pizzaMolhoForm.getDataValidade());
			pizzaMolho.getIngrediente().setPrecoVenda(pizzaMolhoForm.getPrecoVenda());
			pizzaMolhoRepository.save(pizzaMolho);
			
			return ResponseEntity.ok(new PizzaMolhoDto(pizzaMolho));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerPizzaMolho(Long id) {
		Optional<PizzaMolho> lancheMolhoOptional = pizzaMolhoRepository.findById(id);
		if (lancheMolhoOptional.isPresent()) {
			pizzaMolhoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	

//===================================================================================================================//
//PizzaRecheio
	
	@Autowired
	private PizzaRecheioRepository pizzaRecheioRepository;
	
	//Get
	public Page<PizzaRecheioDto> listarPizzaRecheio(String tipoRecheio, Pageable paginacao) {
		if(tipoRecheio == null) {
			Page<PizzaRecheio> pizzaRecheio = pizzaRecheioRepository.findAll(paginacao);
			return PizzaRecheioDto.converter(pizzaRecheio);
		} else {
			Page<PizzaRecheio> pizzaRecheio = pizzaRecheioRepository.findByTipoRecheioIgnoreCase(tipoRecheio, paginacao);
			return PizzaRecheioDto.converter(pizzaRecheio);
		}
	}

	//Get id
	public ResponseEntity<PizzaRecheioDto> detalharPizzaRecheioPorId(Long id) {
		Optional<PizzaRecheio> pizzaRecheio = pizzaRecheioRepository.findById(id);
		if (pizzaRecheio.isPresent()) {
			return ResponseEntity.ok(PizzaRecheioDto.converterUmLancheMolho(pizzaRecheio.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<PizzaRecheioDto> cadastrarPizzaRecheio(PizzaRecheioForm pizzaRecheioForm,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		PizzaRecheio pizzaRecheio = pizzaRecheioForm.converter();
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findByTipoRecheioIgnoreCase(pizzaRecheio.getTipoRecheio());
		if (pizzaRecheioOptional.isEmpty()) {
			pizzaRecheioRepository.save(pizzaRecheio);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(pizzaRecheio.getId()).toUri();
			return ResponseEntity.created(uri).body(new PizzaRecheioDto(pizzaRecheio));
		} else {
			throw new ItemJaExisteException("Recheio já existe");
		}
	}

	//atualizar
	public ResponseEntity<PizzaRecheioDto> atualizarPizzaRecheio(Long id,PizzaRecheioForm pizzaRecheioForm) {
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(id);
		if (pizzaRecheioOptional.isPresent()) {
			
			PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
			pizzaRecheio.setTipoRecheio(pizzaRecheioForm.getTipoRecheio());
			pizzaRecheio.getIngrediente().setPeso(pizzaRecheioForm.getPeso());
			pizzaRecheio.getIngrediente().setDataValidade(pizzaRecheioForm.getDataValidade());
			pizzaRecheio.getIngrediente().setPrecoVenda(pizzaRecheioForm.getPrecoVenda());
			pizzaRecheioRepository.save(pizzaRecheio);
			
			return ResponseEntity.ok(new PizzaRecheioDto(pizzaRecheio));
		}
		return ResponseEntity.notFound().build();
	}
	
	//deletar 
	public ResponseEntity<?> removerPizzaRecheio(Long id) {
		Optional<PizzaRecheio> lancheMolhoOptional = pizzaRecheioRepository.findById(id);
		if (lancheMolhoOptional.isPresent()) {
			pizzaRecheioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
