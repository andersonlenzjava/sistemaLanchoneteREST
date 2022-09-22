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
	public Page<MontarSalgadinhoDto> listarSalgadinhos(Pageable paginacao) {
		Page<Salgadinho> salgadinhos = salgadinhoRepository.findAll(paginacao);
		return MontarSalgadinhoDto.converter(salgadinhos);
	}

	//get id
	public ResponseEntity<MontarSalgadinhoDto> listarSalgadinhoPorId(Long id) {
		Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findById(id);
		if (salgadinhoOptional.isPresent()) {
			return ResponseEntity.ok(MontarSalgadinhoDto.converterUmSalgadinho(salgadinhoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	// Post
	public ResponseEntity<MontarSalgadinhoDto> cadastrarSalgadinho(MontarSalgadinhoForm montarSalgadinhoForm,
			UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarSalgadinhoForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();

			if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {

				Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(montarSalgadinhoForm.getSalgadinhoMassaId());
				Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(montarSalgadinhoForm.getSalgadinhoRecheioId());
				Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(montarSalgadinhoForm.getSalgadinhoTipoPreparoId());

				if (salgadinhoMassaOptional.isPresent() && salgadinhoRecheioOptional.isPresent()
						&& salgadinhoTipoPreparoOptional.isPresent()) {
					
					SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
					SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
					SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();

					Salgadinho salgadinho = new Salgadinho(salgadinhoMassa, salgadinhoRecheio, salgadinhoTipoPreparo);
					salgadinhoRepository.save(salgadinho); // aqui o lanche passa a ter ID 

					pedido.adicionaSalgadinho(salgadinho);

					pedidoRepository.save(pedido);
					
					URI uri = uriBuilder.path("/montarSalgadinho/{id}").buildAndExpand(salgadinho.getId()).toUri();
					return ResponseEntity.created(uri).body(new MontarSalgadinhoDto(salgadinho));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarSalgadinhoDto> atualizarSalgadinho(Long id, MontarSalgadinhoForm montarSalgadinhoForm) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarSalgadinhoForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();

			if (((pedido.getListaSalgadinho().get(Math.toIntExact(id))) != null)
					&&(pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {

				Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(montarSalgadinhoForm.getSalgadinhoMassaId());
				Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(montarSalgadinhoForm.getSalgadinhoRecheioId());
				Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(montarSalgadinhoForm.getSalgadinhoTipoPreparoId());

				if (salgadinhoMassaOptional.isPresent() && salgadinhoRecheioOptional.isPresent()
						&& salgadinhoTipoPreparoOptional.isPresent()) {
					
					SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
					SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
					SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();

					Salgadinho salgadinho = pedido.getListaSalgadinho().get(Math.toIntExact(id));

					salgadinho.setSalgadinhoMassa(salgadinhoMassa);
					salgadinho.setSalgadinhoRecheio(salgadinhoRecheio);
					salgadinho.setSalgadinhoTipoPreparo(salgadinhoTipoPreparo);
					
					salgadinhoRepository.save(salgadinho);
					
					pedido.getListaSalgadinho().set(Math.toIntExact(salgadinho.getId()), salgadinho);
					
					pedidoRepository.save(pedido);

					return ResponseEntity.ok(new MontarSalgadinhoDto(salgadinho));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	// delete
	public ResponseEntity<?> removerLanche(Long id) {
		Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findById(id);
		if (salgadinhoOptional.isPresent()) {
			salgadinhoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}