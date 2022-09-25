package com.lanchonete.sistema.service.item;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.item.MontarLancheDto;
import com.lanchonete.sistema.form.item.MontarLancheForm;
import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;
import com.lanchonete.sistema.repository.ingredientes.LancheMolhoRepository;
import com.lanchonete.sistema.repository.ingredientes.LancheRecheioRepository;
import com.lanchonete.sistema.repository.ingredientes.LancheTipoPaoRepository;
import com.lanchonete.sistema.repository.item.LancheRepository;
import com.lanchonete.sistema.repository.pedido.pedidoRepository;

@Service
public class MontarLancheService {

	@Autowired
	pedidoRepository pedidoRepository;

	@Autowired
	LancheRepository lancheRepository;

	@Autowired
	LancheMolhoRepository lancheMolhoRepository;

	@Autowired
	LancheRecheioRepository lancheRecheioRepository;

	@Autowired
	LancheTipoPaoRepository lancheTipoPaoRepository;

	// Get todos
	public Page<MontarLancheDto> listarLanchesPedido(Long pedidoId, Pageable paginacao) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Page<Lanche> lanches = lancheRepository.findLanchesByPedidoNumero(pedidoId, paginacao);
			return MontarLancheDto.converter(lanches);
		}
		return null;
	}

	// Get por id 
	public ResponseEntity<MontarLancheDto> listarLanchePorId(Long pedidoId, Long lancheId) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Optional<Lanche> lancheOptional = lancheRepository.findUmLanchePorIdEPedido(pedidoId, lancheId);
			if (lancheOptional.isPresent()) {
				return ResponseEntity.ok(MontarLancheDto.converterUmLanche(lancheOptional.get()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// post
	public ResponseEntity<MontarLancheDto> cadastrarLanche(MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheForm.getPedidoId());
		Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheForm.getLancheMolhoId());
		Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheForm.getLancheRecheioId());
		Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheForm.getLancheTipoPaoId());
		
		if (pedidoOptional.isPresent()
				&& lancheMolhoOptional.isPresent()
				&& lancheRecheioOptional.isPresent()
				&& lancheTipoPaoOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
				
				if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
	
						LancheMolho lancheMolho = lancheMolhoOptional.get();
						LancheRecheio lancheRecheio = lancheRecheioOptional.get();
						LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
	
						Lanche lanche = new Lanche(pedido, lancheTipoPao, lancheRecheio, lancheMolho);
						
						lancheRepository.save(lanche); // aqui o lanche passa a ter ID 
	
						pedido.adicionaLanche(lanche);
						
						pedidoRepository.save(pedido);
						
						URI uri = uriBuilder.path("/pedido/lanches/{id}").buildAndExpand(lanche.getId()).toUri();
						return ResponseEntity.created(uri).body(new MontarLancheDto(lanche));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarLancheDto> atualizarLanche(Long lancheId, MontarLancheForm montarLancheForm) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheForm.getPedidoId());
		Optional<Lanche> lancheOptional = lancheRepository.findById(lancheId);
		Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheForm.getLancheMolhoId());
		Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheForm.getLancheRecheioId());
		Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheForm.getLancheTipoPaoId());
		
		if (pedidoOptional.isPresent()
				&& lancheOptional.isPresent()
				&& lancheMolhoOptional.isPresent()
				&& lancheRecheioOptional.isPresent()
				&& lancheTipoPaoOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
				Lanche lanche = lancheOptional.get();
			
				if ((lanche.getPedido().getId() == pedido.getId()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {

						pedido.removerLanche(lanche);
					
						LancheMolho lancheMolho = lancheMolhoOptional.get();
						LancheRecheio lancheRecheio = lancheRecheioOptional.get();
						LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
	
						lanche.setLancheMolho(lancheMolho);
						lanche.setLancheRecheio(lancheRecheio);
						lanche.setLancheTipoPao(lancheTipoPao);
						lanche.CalculosPrato();
						
						lancheRepository.save(lanche); 
						
						pedido.adicionaLanche(lanche);

						pedidoRepository.save(pedido);
	
						return ResponseEntity.ok(new MontarLancheDto(lanche));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// delete
	public ResponseEntity<?> removerLanchePedido(Long pedidoId, Long lancheId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {

			Pedido pedido = pedidoOptional.get();
			Optional<Lanche> lancheOptional = lancheRepository.findUmLanchePorIdEPedido(pedidoId, lancheId);

			if ((lancheOptional.isPresent()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
				pedido.removerLanche(lancheOptional.get()); // operações com os saldos 
				lancheRepository.delete(lancheOptional.get());
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

}
