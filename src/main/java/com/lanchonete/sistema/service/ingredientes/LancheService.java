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

import com.lanchonete.sistema.dto.ingredientes.LancheMolhoDto;
import com.lanchonete.sistema.form.ingredientes.LancheMolhoForm;
import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.repository.ingredientes.LancheMolhoRepository;

@Service
public class LancheService {

	//===================================================================================================================//
	//Molho
	
	@Autowired
	private LancheMolhoRepository lancheMolhoRepository;
	
	//Get
	public Page<LancheMolhoDto> listarLancheMolho(String tipoMolho, Pageable paginacao) {
		if(tipoMolho == null) {
			Page<LancheMolho> molhos = lancheMolhoRepository.findAll(paginacao);
			return LancheMolhoDto.converter(molhos);
		} else {
			Page<LancheMolhoDto> molhos = lancheMolhoRepository.findByTipoMolho(tipoMolho, paginacao);
			return LancheMolhoDto.converter(molhos);
		}
	}
	
	//Get id
	public ResponseEntity<LancheMolhoDto> detalharLancheMolhoPorId(Long id) {
		Optional<LancheMolho> lancheMolho = lancheMolhoRepository.findById(id);
		if (lancheMolho.isPresent()) {
			return ResponseEntity.ok(LancheMolhoDto.converterUmLancheMolho(lancheMolho.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	//cadastrar
		public ResponseEntity<LancheMolhoDto> cadastrarLancheMolho(@Valid LancheMolhoForm lancheMolhoForm,
				UriComponentsBuilder uriBuilder) throws Exception {
			LancheMolho lancheMolho = lancheMolhoForm.converter();
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findByTipoMolhoIgnoreCase(lancheMolho.getTipoMolho());
			if (lancheMolhoOptional.isEmpty()) {
				lancheMolhoRepository.save(lancheMolho);
				URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(lancheMolho.getId()).toUri();
				return ResponseEntity.created(uri).body(new LancheMolhoDto(lancheMolho));
			} else {
				throw new Exception("Agencia já existe");
			}
		}
	
	//deletar 
		public ResponseEntity<?> removerLancheMolho(Long id) {
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(id);
			if (lancheMolhoOptional.isPresent()) {
				lancheMolhoRepository.deleteById(id);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		}
		
		//===================================================================================================================//
		//Recheio
	
	
}
