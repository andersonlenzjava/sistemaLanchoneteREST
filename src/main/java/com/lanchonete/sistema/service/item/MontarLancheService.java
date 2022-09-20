package com.lanchonete.sistema.service.item;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.item.MontarLancheDto;
import com.lanchonete.sistema.form.item.MontarLancheForm;
import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.repository.ingredientes.LancheMolhoRepository;
import com.lanchonete.sistema.repository.ingredientes.LancheRecheioRepository;
import com.lanchonete.sistema.repository.ingredientes.LancheTipoPaoRepository;
import com.lanchonete.sistema.repository.item.LancheRepository;
import com.lanchonete.sistema.repository.pedido.pedidoRepository;

public class MontarLancheService {

	
	@Autowired
	pedidoRepository pedidoRepository;
	
	@Autowired
	LancheMolhoRepository lancheMolhoRepository;
	
	@Autowired
	LancheRecheioRepository lancheRecheioRepository;
	
	@Autowired
	LancheTipoPaoRepository lancheTipoPaoRepository;
	
	@Autowired
	LancheRepository lancheRepository;
	
	//Get id
	public Page<MontarLancheDto> listarLanches() {
		// buscarTodos
		return null;
	}

	public ResponseEntity<MontarLancheDto> listarLanchePorId(Long id) {
		// buscar por id 
		return null;
	}
	
	//put
	public ResponseEntity<MontarLancheDto> cadastrarLanche(MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheForm.getPedidoId());
			Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheForm.getPedidoId());
			Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheForm.getPedidoId());
			
			if(lancheMolhoOptional.isPresent() && lancheRecheioOptional.isPresent() && lancheTipoPaoOptional.isPresent()) {
				LancheMolho lancheMolho = lancheMolhoOptional.get();
				LancheRecheio lancheRecheio = lancheRecheioOptional.get();
				LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
				
				Lanche lanche = new Lanche(lancheTipoPao, lancheRecheio, lancheMolho);
				lancheRepository.save(lanche);
				lanche.getId();
				
				Pedido pedido = pedidoOptional.get();
				pedido.adicionaLanche(lanche);
				
			}
		}
		//1° verificar se o pedido existe
		//2° verificar se o tipo de pao existe 
		//3° verificar se o tipo de recheio existe
		//4° verificar se o tipo de molho existe
		
		//5° salvar o lanche 
		
		//6° adicionar o lanche a lista do pedido. 
		return null;
	}

	public ResponseEntity<MontarLancheDto> atualizarLanche(@Valid MontarLancheForm montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		//buscar o item por id 
		//realizar o procedimento do método cadastrar 
		return null;
	}

	public ResponseEntity<?> removerLanche(Long id) {
		// //buscar o item por id 
		//deletalo 
		return null;
	}



	
}
