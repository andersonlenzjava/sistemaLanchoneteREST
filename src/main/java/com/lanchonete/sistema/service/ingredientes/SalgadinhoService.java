package com.lanchonete.sistema.service.ingredientes;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.ingredientes.SalgadinhoMassaDto;
import com.lanchonete.sistema.dto.ingredientes.SalgadinhoRecheioDto;
import com.lanchonete.sistema.dto.ingredientes.SalgadinhoTipoPreparoDto;
import com.lanchonete.sistema.form.ingredientes.SalgadinhoMassaForm;
import com.lanchonete.sistema.form.ingredientes.SalgadinhoRecheioForm;
import com.lanchonete.sistema.form.ingredientes.SalgadinhoTipoPreparoForm;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;
import com.lanchonete.sistema.repository.ingredientes.SalgadinhoMassaRepository;
import com.lanchonete.sistema.repository.ingredientes.SalgadinhoRecheioRepository;
import com.lanchonete.sistema.repository.ingredientes.SalgadinhoTipoPreparoRepository;
import com.lanchonete.sistema.validacao.ItemJaExisteException;

@Service
public class SalgadinhoService {

//===================================================================================================================//
//SalgadinhoMassa
	
	@Autowired
	private SalgadinhoMassaRepository salgadinhoMassaRepository;

	//Get
	public Page<SalgadinhoMassaDto> listarSalgadinhoMassa(String tipoMassa, Pageable paginacao) {
		if(tipoMassa == null) {
			Page<SalgadinhoMassa> salgadinhoTipoMassa = salgadinhoMassaRepository.findAll(paginacao);
			return SalgadinhoMassaDto.converter(salgadinhoTipoMassa);
		} else {
			Page<SalgadinhoMassa> salgadinhoTipoMassa = salgadinhoMassaRepository.findByTipoMassaIgnoreCase(tipoMassa, paginacao);
			return SalgadinhoMassaDto.converter(salgadinhoTipoMassa);
		}
	}

	//Get id
	public ResponseEntity<SalgadinhoMassaDto> detalharSalgadinhoMassa(Long id) {
		Optional<SalgadinhoMassa> salgadinhoTipoMassa = salgadinhoMassaRepository.findById(id);
		if (salgadinhoTipoMassa.isPresent()) {
			return ResponseEntity.ok(SalgadinhoMassaDto.converterUmLancheMolho(salgadinhoTipoMassa.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<SalgadinhoMassaDto> cadastrarSalgadinhoMassa(@Valid SalgadinhoMassaForm salgadinhoMassaForm,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		SalgadinhoMassa salgadinhoTipoMassa = salgadinhoMassaForm.converter();
		Optional<SalgadinhoMassa> salgadinhoTipoMassaOptional = salgadinhoMassaRepository.findByTipoMassaIgnoreCase(salgadinhoTipoMassa.getTipoMassa());
		if (salgadinhoTipoMassaOptional.isEmpty()) {
			salgadinhoMassaRepository.save(salgadinhoTipoMassa);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(salgadinhoTipoMassa.getId()).toUri();
			return ResponseEntity.created(uri).body(new SalgadinhoMassaDto(salgadinhoTipoMassa));
		} else {
			throw new ItemJaExisteException("Salgadinho Massa já existe");
		}
	}
	
	//atualizar
	public ResponseEntity<SalgadinhoMassaDto> atualizarPizzaRecheio(Long id,
			@Valid SalgadinhoMassaForm salgadinhoMassaForm) {
		Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(id);
		if (salgadinhoMassaOptional.isPresent()) {
			
			SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
			salgadinhoMassa.setTipoMassa(salgadinhoMassaForm.getTipoMassa());;
			salgadinhoMassa.getIngrediente().setPeso(salgadinhoMassaForm.getPeso());
			salgadinhoMassa.getIngrediente().setDataValidade(salgadinhoMassaForm.getDataValidade());
			salgadinhoMassa.getIngrediente().setPrecoVenda(salgadinhoMassaForm.getPrecoVenda());
			salgadinhoMassaRepository.save(salgadinhoMassa);
			
			return ResponseEntity.ok(new SalgadinhoMassaDto(salgadinhoMassa));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerSalgadinhoMassa(Long id) {
		Optional<SalgadinhoMassa> salgadinhoTipoMassaOptional = salgadinhoMassaRepository.findById(id);
		if (salgadinhoTipoMassaOptional.isPresent()) {
			salgadinhoMassaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}



//===================================================================================================================//
//SalgadinhoRecheiro
	
	@Autowired
	private SalgadinhoRecheioRepository salgadinhoRecheioRepository;
	
	//Get
	public Page<SalgadinhoRecheioDto> listarSalgadinhoRecheio(String tipoRecheio, Pageable paginacao) {
		if(tipoRecheio == null) {
			Page<SalgadinhoRecheio> salgadinhoRecheio = salgadinhoRecheioRepository.findAll(paginacao);
			return SalgadinhoRecheioDto.converter(salgadinhoRecheio);
		} else {
			Page<SalgadinhoRecheio> salgadinhoRecheio = salgadinhoRecheioRepository.findByTipoRecheioIgnoreCase(tipoRecheio, paginacao);
			return SalgadinhoRecheioDto.converter(salgadinhoRecheio);
		}
	}

	//Get id
	public ResponseEntity<SalgadinhoRecheioDto> detalharSalgadinhoRecheioPorId(Long id) {
		Optional<SalgadinhoRecheio> salgadinhoRecheio = salgadinhoRecheioRepository.findById(id);
		if (salgadinhoRecheio.isPresent()) {
			return ResponseEntity.ok(SalgadinhoRecheioDto.converterUmLancheMolho(salgadinhoRecheio.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<SalgadinhoRecheioDto> cadastrarSalgadinhoRecheio(SalgadinhoRecheioForm salgadinhoRecheioForm,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioForm.converter();
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findByTipoRecheioIgnoreCase(salgadinhoRecheio.getTipoRecheio());
		if (salgadinhoRecheioOptional.isEmpty()) {
			salgadinhoRecheioRepository.save(salgadinhoRecheio);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(salgadinhoRecheio.getId()).toUri();
			return ResponseEntity.created(uri).body(new SalgadinhoRecheioDto(salgadinhoRecheio));
		} else {
			throw new ItemJaExisteException("Recheio já existe");
		}
	}
	
	//atualizar
	public ResponseEntity<SalgadinhoRecheioDto> atualizarPizzaRecheio(Long id,
			@Valid SalgadinhoRecheioForm salgadinhoRecheioForm) {
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(id);
		if (salgadinhoRecheioOptional.isPresent()) {
			
			SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
			salgadinhoRecheio.setTipoRecheio(salgadinhoRecheioForm.getTipoRecheio());
			salgadinhoRecheio.getIngrediente().setPeso(salgadinhoRecheioForm.getPeso());
			salgadinhoRecheio.getIngrediente().setDataValidade(salgadinhoRecheioForm.getDataValidade());
			salgadinhoRecheio.getIngrediente().setPrecoVenda(salgadinhoRecheioForm.getPrecoVenda());
			salgadinhoRecheioRepository.save(salgadinhoRecheio);
			
			return ResponseEntity.ok(new SalgadinhoRecheioDto(salgadinhoRecheio));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerSalgadinhoRecheio(Long id) {
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(id);
		if (salgadinhoRecheioOptional.isPresent()) {
			salgadinhoRecheioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	
//===================================================================================================================//
//SalgadinhoTipoPreparo
	
	@Autowired
	private SalgadinhoTipoPreparoRepository salgadinhoTipoPreparoRepository;
	
	//Get
	public Page<SalgadinhoTipoPreparoDto> listarSalgadinhoTipoPreparo(String tipoPreparo, Pageable paginacao) {
		if(tipoPreparo == null) {
			Page<SalgadinhoTipoPreparo> salgadinhoTipoPreparo = salgadinhoTipoPreparoRepository.findAll(paginacao);
			return SalgadinhoTipoPreparoDto.converter(salgadinhoTipoPreparo);
		} else {
			Page<SalgadinhoTipoPreparo> salgadinhoTipoPreparo = salgadinhoTipoPreparoRepository.findByTipoPreparoIgnoreCase(tipoPreparo, paginacao);
			return SalgadinhoTipoPreparoDto.converter(salgadinhoTipoPreparo);
		}
	}

	//Get id
	public ResponseEntity<SalgadinhoTipoPreparoDto> detalharSalgadinhoTipoPreparoPorId(Long id) {
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparo = salgadinhoTipoPreparoRepository.findById(id);
		if (salgadinhoTipoPreparo.isPresent()) {
			return ResponseEntity.ok(SalgadinhoTipoPreparoDto.converterUmLancheMolho(salgadinhoTipoPreparo.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<SalgadinhoTipoPreparoDto> cadastrarSalgadinhoTipoPreparo(
			SalgadinhoTipoPreparoForm salgadinhoTipoPreparoForm, UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoForm.converter();
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findByTipoPreparoIgnoreCase(salgadinhoTipoPreparo.getTipoPreparo());
		if (salgadinhoTipoPreparoOptional.isEmpty()) {
			salgadinhoTipoPreparoRepository.save(salgadinhoTipoPreparo);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(salgadinhoTipoPreparo.getId()).toUri();
			return ResponseEntity.created(uri).body(new SalgadinhoTipoPreparoDto(salgadinhoTipoPreparo));
		} else {
			throw new ItemJaExisteException("TipoPreparo já existe");
		}
	}

	//atualizar
	public ResponseEntity<SalgadinhoTipoPreparoDto> atualizarPizzaRecheio(Long id,
			@Valid SalgadinhoTipoPreparoForm salgadinhoTipoPreparoForm) {
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(id);
		if (salgadinhoTipoPreparoOptional.isPresent()) {
			
			SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();
			salgadinhoTipoPreparo.setTipoPreparo(salgadinhoTipoPreparoForm.getTipoPreparo());
			salgadinhoTipoPreparo.getIngrediente().setPeso(salgadinhoTipoPreparoForm.getPeso());
			salgadinhoTipoPreparo.getIngrediente().setDataValidade(salgadinhoTipoPreparoForm.getDataValidade());
			salgadinhoTipoPreparo.getIngrediente().setPrecoVenda(salgadinhoTipoPreparoForm.getPrecoVenda());
			salgadinhoTipoPreparoRepository.save(salgadinhoTipoPreparo);
			
			return ResponseEntity.ok(new SalgadinhoTipoPreparoDto(salgadinhoTipoPreparo));
		}
		return ResponseEntity.notFound().build();
	}
	
	//deletar 
	public ResponseEntity<?> removerSalgadinhoTipoPreparo(Long id) {
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(id);
		if (salgadinhoTipoPreparoOptional.isPresent()) {
			salgadinhoTipoPreparoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
