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
	public Page<MontarLancheDto> listarLanches(Pageable paginacao) {
		Page<Lanche> lanches = lancheRepository.findAll(paginacao);
		return MontarLancheDto.converter(lanches);
	}

	// Get por id 
	public ResponseEntity<MontarLancheDto> listarLanchePorId(Long id) {
		Optional<Lanche> lancheOptional = lancheRepository.findById(id);
		if (lancheOptional.isPresent()) {
			return ResponseEntity.ok(MontarLancheDto.converterUmLanche(lancheOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	// post
	public ResponseEntity<MontarLancheDto> cadastrarLanche(MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			
			if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {

				Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheForm.getLancheMolhoId());
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheForm.getLancheRecheioId());
				Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheForm.getLancheTipoPaoId());

				if (lancheMolhoOptional.isPresent() && lancheRecheioOptional.isPresent()
						&& lancheTipoPaoOptional.isPresent()) {
					
					LancheMolho lancheMolho = lancheMolhoOptional.get();
					LancheRecheio lancheRecheio = lancheRecheioOptional.get();
					LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();

					Lanche lanche = new Lanche(lancheTipoPao, lancheRecheio, lancheMolho);
					lancheRepository.save(lanche); // aqui o lanche passa a ter ID 

					pedido.adicionaLanche(lanche);
					
					pedidoRepository.save(pedido);
					
					URI uri = uriBuilder.path("/montarLanche/{id}").buildAndExpand(lanche.getId()).toUri();
					return ResponseEntity.created(uri).body(new MontarLancheDto(lanche));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarLancheDto> atualizarLanche(Long id, MontarLancheForm montarLancheForm) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			
			if (((pedido.getListaLanche().get(Math.toIntExact(id))) != null)
					&& (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {

				Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheForm.getLancheMolhoId());
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheForm.getLancheRecheioId());
				Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheForm.getLancheTipoPaoId());

				if (lancheMolhoOptional.isPresent() && lancheRecheioOptional.isPresent()
						&& lancheTipoPaoOptional.isPresent()) {

					LancheMolho lancheMolho = lancheMolhoOptional.get();
					LancheRecheio lancheRecheio = lancheRecheioOptional.get();
					LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();

					Lanche lanche = pedido.atualizarLanche(id, lancheMolho, lancheRecheio, lancheTipoPao);
					
//					Lanche lanche = pedido.getListaLanche().get(Math.toIntExact(id));
//
//					lanche.setLancheMolho(lancheMolho);
//					lanche.setLancheRecheio(lancheRecheio);
//					lanche.setLancheTipoPao(lancheTipoPao);
//	
					lancheRepository.save(lanche); // será que sobreescreve certo ?
//
//					// buscar lanche dentro da lista de pedidos e seta 
//					pedido.getListaLanche().set(Math.toIntExact(lanche.getId()), lanche);
					
					pedidoRepository.save(pedido);

					return ResponseEntity.ok(new MontarLancheDto(lanche));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

}
