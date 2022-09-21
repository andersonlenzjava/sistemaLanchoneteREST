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
import com.lanchonete.sistema.dto.ingredientes.LancheRecheioDto;
import com.lanchonete.sistema.dto.ingredientes.LancheTipoPaoDto;
import com.lanchonete.sistema.form.ingredientes.LancheMolhoForm;
import com.lanchonete.sistema.form.ingredientes.LancheRecheioForm;
import com.lanchonete.sistema.form.ingredientes.LancheTipoPaoForm;
import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.repository.ingredientes.LancheMolhoRepository;
import com.lanchonete.sistema.repository.ingredientes.LancheRecheioRepository;
import com.lanchonete.sistema.repository.ingredientes.LancheTipoPaoRepository;
import com.lanchonete.sistema.validacao.ItemJaExisteException;

@Service
public class LancheService {

//===================================================================================================================//
//LancheMolho
	
	@Autowired
	private LancheMolhoRepository lancheMolhoRepository;
	
	//Get
	public Page<LancheMolhoDto> listarLancheMolho(String tipoMolho, Pageable paginacao) {
		if(tipoMolho == null) {
			Page<LancheMolho> lancheMolho = lancheMolhoRepository.findAll(paginacao);
			return LancheMolhoDto.converter(lancheMolho);
		} else {
			Page<LancheMolho> lancheMolho = lancheMolhoRepository.findByTipoMolhoIgnoreCase(tipoMolho, paginacao);
			return LancheMolhoDto.converter(lancheMolho);
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
		public ResponseEntity<LancheMolhoDto> cadastrarLancheMolho(LancheMolhoForm lancheMolhoForm,
				UriComponentsBuilder uriBuilder) throws Exception {
			LancheMolho lancheMolho = lancheMolhoForm.converter();
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findByTipoMolhoIgnoreCase(lancheMolho.getTipoMolho());
			if (lancheMolhoOptional.isEmpty()) {
				lancheMolhoRepository.save(lancheMolho);
				URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(lancheMolho.getId()).toUri();
				return ResponseEntity.created(uri).body(new LancheMolhoDto(lancheMolho));
			} else {
				throw new ItemJaExisteException("Molho já existe");
			}
		}
		
		//atualizar
		public ResponseEntity<LancheMolhoDto> atualizarLancheMolho(Long id, LancheMolhoForm lancheMolhoForm) {
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(id);
			if (lancheMolhoOptional.isPresent()) {
				
				LancheMolho lancheMolho = lancheMolhoOptional.get();
				lancheMolho.setTipoMolho(lancheMolhoForm.getTipoMolho());
				lancheMolho.getIngrediente().setPeso(lancheMolhoForm.getPeso());
				lancheMolho.getIngrediente().setDataValidade(lancheMolhoForm.getDataValidade());
				lancheMolho.getIngrediente().setPrecoVenda(lancheMolhoForm.getPrecoVenda());
				lancheMolhoRepository.save(lancheMolho);
				
				return ResponseEntity.ok(new LancheMolhoDto(lancheMolho));
			}
			return ResponseEntity.notFound().build();
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
//LancheRecheio
	
		@Autowired
		private LancheRecheioRepository lancheRecheioRepository;
		
		//Get
		public Page<LancheRecheioDto> listarLancheRecheio(String tipoRecheio, Pageable paginacao) {
			if(tipoRecheio == null) {
				Page<LancheRecheio> lancheRecheios = lancheRecheioRepository.findAll(paginacao);
				return LancheRecheioDto.converter(lancheRecheios);
			} else {
				Page<LancheRecheio> lancheRecheio = lancheRecheioRepository.findByTipoRecheioIgnoreCase(tipoRecheio, paginacao);
				return LancheRecheioDto.converter(lancheRecheio);
			}
		}
		
		//Get id
		public ResponseEntity<LancheRecheioDto> detalharLancheRecheioPorId(Long id) {
			Optional<LancheRecheio> lancheRecheio = lancheRecheioRepository.findById(id);
			if (lancheRecheio.isPresent()) {
				return ResponseEntity.ok(LancheRecheioDto.converterUmLancheMolho(lancheRecheio.get()));
			}
			return ResponseEntity.notFound().build();
		}
		
		//cadastrar
			public ResponseEntity<LancheRecheioDto> cadastrarLancheRecheio(LancheRecheioForm lancheRecheioForm,
					UriComponentsBuilder uriBuilder) throws Exception {
				LancheRecheio lancheRecheio = lancheRecheioForm.converter();
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findByTipoRecheioIgnoreCase(lancheRecheio.getTipoRecheio());
				if (lancheRecheioOptional.isEmpty()) {
					lancheRecheioRepository.save(lancheRecheio);
					URI uri = uriBuilder.path("/lanches/{id}").buildAndExpand(lancheRecheio.getId()).toUri();
					return ResponseEntity.created(uri).body(new LancheRecheioDto(lancheRecheio));
				} else {
					throw new ItemJaExisteException("Recheio já existe");
				}
			}
			
			//atualizar
			public ResponseEntity<LancheRecheioDto> atualizarLancheRecheio(Long id,
					@Valid LancheRecheioForm lancheRecheioForm) {
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(id);
				if (lancheRecheioOptional.isPresent()) {
					
					LancheRecheio lancheRecheio = lancheRecheioOptional.get();
					lancheRecheio.setTipoRecheio(lancheRecheioForm.getTipoRecheio());
					lancheRecheio.getIngrediente().setPeso(lancheRecheioForm.getPeso());
					lancheRecheio.getIngrediente().setDataValidade(lancheRecheioForm.getDataValidade());
					lancheRecheio.getIngrediente().setPrecoVenda(lancheRecheioForm.getPrecoVenda());
					lancheRecheioRepository.save(lancheRecheio);
					
					return ResponseEntity.ok(new LancheRecheioDto(lancheRecheio));
				}
				return ResponseEntity.notFound().build();
			}
		
		//deletar 
			public ResponseEntity<?> removerLancheRecheio(Long id) {
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(id);
				if (lancheRecheioOptional.isPresent()) {
					lancheRecheioRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}
				return ResponseEntity.notFound().build();
			}

		
//===================================================================================================================//
//LancheTipoPao
		
			@Autowired
			private LancheTipoPaoRepository lancheTipoPaoRepository;
			
			//Get
			public Page<LancheTipoPaoDto> listarLancheTipoPao(String tipoPao, Pageable paginacao) {
				if(tipoPao == null) {
					Page<LancheTipoPao> lancheTipoPao = lancheTipoPaoRepository.findAll(paginacao);
					return LancheTipoPaoDto.converter(lancheTipoPao);
				} else {
					Page<LancheTipoPao> lancheTipoPao = lancheTipoPaoRepository.findByTipoPaoIgnoreCase(tipoPao, paginacao);
					return LancheTipoPaoDto.converter(lancheTipoPao);
				}
			}
			
			//Get id
			public ResponseEntity<LancheTipoPaoDto> detalharLancheTipoPaoPorId(Long id) {
				Optional<LancheTipoPao> lancheTipoPao = lancheTipoPaoRepository.findById(id);
				if (lancheTipoPao.isPresent()) {
					return ResponseEntity.ok(LancheTipoPaoDto.converterUmLancheMolho(lancheTipoPao.get()));
				}
				return ResponseEntity.notFound().build();
			}
			
			//cadastrar
				public ResponseEntity<LancheTipoPaoDto> cadastrarLancheTipoPao(LancheTipoPaoForm lancheTipoPaoForm,
						UriComponentsBuilder uriBuilder) throws Exception {
					LancheTipoPao lancheTipoPao = lancheTipoPaoForm.converter();
					Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findByTipoPaoIgnoreCase(lancheTipoPao.getTipoPao());
					if (lancheTipoPaoOptional.isEmpty()) {
						lancheTipoPaoRepository.save(lancheTipoPao);
						URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(lancheTipoPao.getId()).toUri();
						return ResponseEntity.created(uri).body(new LancheTipoPaoDto(lancheTipoPao));
					} else {
						throw new ItemJaExisteException("TipoPao já existe");
					}
				}
				
			//atualizar 	
				public ResponseEntity<LancheTipoPaoDto> atualizarLancheTipoPao(Long id,
						@Valid LancheTipoPaoForm lancheTipoPaoForm) {
					Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(id);
					if (lancheTipoPaoOptional.isPresent()) {
						
						LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
						lancheTipoPao.setTipoPao(lancheTipoPaoForm.getTipoPao());
						lancheTipoPao.getIngrediente().setPeso(lancheTipoPaoForm.getPeso());
						lancheTipoPao.getIngrediente().setDataValidade(lancheTipoPaoForm.getDataValidade());
						lancheTipoPao.getIngrediente().setPrecoVenda(lancheTipoPaoForm.getPrecoVenda());
						lancheTipoPaoRepository.save(lancheTipoPao);
						
						return ResponseEntity.ok(new LancheTipoPaoDto(lancheTipoPao));
					}
					return ResponseEntity.notFound().build();
				}
			
			//deletar 
				public ResponseEntity<?> removerLancheTipoPao(Long id) {
					Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(id);
					if (lancheTipoPaoOptional.isPresent()) {
						lancheTipoPaoRepository.deleteById(id);
						return ResponseEntity.ok().build();
					}
					return ResponseEntity.notFound().build();
				}

}
