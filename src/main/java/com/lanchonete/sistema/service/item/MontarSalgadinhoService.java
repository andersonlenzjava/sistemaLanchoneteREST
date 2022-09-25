package com.lanchonete.sistema.service.item;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.item.MontarSalgadinhoDto;
import com.lanchonete.sistema.form.item.MontarSalgadinhoForm;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;
import com.lanchonete.sistema.model.item.Salgadinho;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;
import com.lanchonete.sistema.repository.ingredientes.SalgadinhoMassaRepository;
import com.lanchonete.sistema.repository.ingredientes.SalgadinhoRecheioRepository;
import com.lanchonete.sistema.repository.ingredientes.SalgadinhoTipoPreparoRepository;
import com.lanchonete.sistema.repository.item.SalgadinhoRepository;
import com.lanchonete.sistema.repository.pedido.pedidoRepository;

@Service
public class MontarSalgadinhoService {

	@Autowired
	pedidoRepository pedidoRepository;
	
	@Autowired
	SalgadinhoRepository salgadinhoRepository;
	
	@Autowired
	SalgadinhoMassaRepository salgadinhoMassaRepository;
	
	@Autowired
	SalgadinhoRecheioRepository salgadinhoRecheioRepository;
	
	@Autowired
	SalgadinhoTipoPreparoRepository salgadinhoTipoPreparoRepository;
	
	// get
	public Page<MontarSalgadinhoDto> listarSalgadinhosPedido(Long pedidoId, Pageable paginacao) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Page<Salgadinho> salgadinhos = salgadinhoRepository.findSalgadinhosByPedidoNumero(pedidoId, paginacao);
			return MontarSalgadinhoDto.converter(salgadinhos);
		}
		return null;
	}

	//get id
	public ResponseEntity<MontarSalgadinhoDto> listarSalgadinhoPedidoPorId(Long pedidoId, Long salgadinhoId) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findUmSalgadinhoPorIdEPedido(pedidoId, salgadinhoId);
			if (salgadinhoOptional.isPresent()) {
				return ResponseEntity.ok(MontarSalgadinhoDto.converterUmSalgadinho(salgadinhoOptional.get()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// Post
	public ResponseEntity<MontarSalgadinhoDto> cadastrarSalgadinho(MontarSalgadinhoForm montarSalgadinhoForm,
			UriComponentsBuilder uriBuilder) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarSalgadinhoForm.getPedidoId());
		Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(montarSalgadinhoForm.getSalgadinhoMassaId());
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(montarSalgadinhoForm.getSalgadinhoRecheioId());
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(montarSalgadinhoForm.getSalgadinhoTipoPreparoId());

		if (pedidoOptional.isPresent()
				&& salgadinhoMassaOptional.isPresent() 
				&& salgadinhoRecheioOptional.isPresent() 
				&& salgadinhoTipoPreparoOptional.isPresent()) {
				
				Pedido pedido = pedidoOptional.get();

				if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
					
						SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
						SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
						SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();
	
						Salgadinho salgadinho = new Salgadinho(pedido, salgadinhoMassa, salgadinhoRecheio, salgadinhoTipoPreparo);
						
						salgadinhoRepository.save(salgadinho); 
						
						pedido.adicionaSalgadinho(salgadinho); // operações com os saldos 
						
						pedidoRepository.save(pedido); 
	
						URI uri = uriBuilder.path("/pedido/salgadinhos/{id}").buildAndExpand(salgadinho.getId()).toUri();
						return ResponseEntity.created(uri).body(new MontarSalgadinhoDto(salgadinho));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarSalgadinhoDto> atualizarSalgadinho(Long salgadinhoId, MontarSalgadinhoForm montarSalgadinhoForm) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarSalgadinhoForm.getPedidoId());
		Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findById(salgadinhoId);
		Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(montarSalgadinhoForm.getSalgadinhoMassaId());
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(montarSalgadinhoForm.getSalgadinhoRecheioId());
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(montarSalgadinhoForm.getSalgadinhoTipoPreparoId());

		if (pedidoOptional.isPresent()
				&& salgadinhoOptional.isPresent()
				&& salgadinhoMassaOptional.isPresent() 
				&& salgadinhoRecheioOptional.isPresent() 
				&& salgadinhoTipoPreparoOptional.isPresent()) {
			
			Pedido pedido = pedidoOptional.get();
			Salgadinho salgadinho = salgadinhoOptional.get();
			
			if ((salgadinho.getPedido().getId() == pedido.getId()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
					pedido.removerSalgadinho(salgadinho); // operações com os saldos 
				
					SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
					SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
					SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();
					
					salgadinho.setSalgadinhoMassa(salgadinhoMassa);
					salgadinho.setSalgadinhoRecheio(salgadinhoRecheio);
					salgadinho.setSalgadinhoTipoPreparo(salgadinhoTipoPreparo);
					salgadinho.CalculosSalgadinho();
					
					salgadinhoRepository.save(salgadinho);
					
					pedido.adicionaSalgadinho(salgadinho); // operações com os saldos 
					
					pedidoRepository.save(pedido); 
					
					return ResponseEntity.ok(new MontarSalgadinhoDto(salgadinho));
					}
			}
		return ResponseEntity.notFound().build();
	}

	//delete
	public ResponseEntity<?> removerSalgadinhoPedido(Long pedidoId, Long salgadinhoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {

			Pedido pedido = pedidoOptional.get();
			Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findUmSalgadinhoPorIdEPedido(pedidoId, salgadinhoId);

			if ((salgadinhoOptional.isPresent()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
				pedido.removerSalgadinho(salgadinhoOptional.get()); // operações com os saldos 
				salgadinhoRepository.delete(salgadinhoOptional.get());
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}
}
