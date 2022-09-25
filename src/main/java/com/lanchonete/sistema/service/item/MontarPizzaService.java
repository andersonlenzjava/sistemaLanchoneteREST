package com.lanchonete.sistema.service.item;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.lanchonete.sistema.dto.item.MontarPizzaDto;
import com.lanchonete.sistema.form.item.MontarPizzaForm;
import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.pedido.Pedido;
import com.lanchonete.sistema.model.pedido.StatusPedido;
import com.lanchonete.sistema.repository.ingredientes.PizzaBordaRepository;
import com.lanchonete.sistema.repository.ingredientes.PizzaMolhoRepository;
import com.lanchonete.sistema.repository.ingredientes.PizzaRecheioRepository;
import com.lanchonete.sistema.repository.item.PizzaRepository;
import com.lanchonete.sistema.repository.pedido.pedidoRepository;

@Service
public class MontarPizzaService {

	@Autowired
	pedidoRepository pedidoRepository;
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	PizzaBordaRepository pizzaBordaRepository;
	
	@Autowired
	PizzaMolhoRepository pizzaMolhoRepository;
	
	@Autowired
	PizzaRecheioRepository pizzaRecheioRepository;
	
	// get 
	public Page<MontarPizzaDto> listarPizzasPedido(Long pedidoId, Pageable paginacao) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Page<Pizza> pizzas = pizzaRepository.findPizzasByPedidoNumero(pedidoId, paginacao);
			return MontarPizzaDto.converter(pizzas);
		}
		return null;
	}
	

	//get id
	public ResponseEntity<MontarPizzaDto> listarPizzaPedidoPorId(Long pedidoId, Long pizzaId) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Optional<Pizza> pizzaOptional = pizzaRepository.findUmaPizzaPorIdEPedido(pedidoId, pizzaId);
			if (pizzaOptional.isPresent()) {
				return ResponseEntity.ok(MontarPizzaDto.converterUmaPizza(pizzaOptional.get()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// post
	public ResponseEntity<MontarPizzaDto> cadastrarPizza(MontarPizzaForm montarPizzaForm,
			UriComponentsBuilder uriBuilder) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarPizzaForm.getPedidoId());
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(montarPizzaForm.getPizzaBordaId());
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(montarPizzaForm.getPizzaMolhoId());
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(montarPizzaForm.getPizzaRecheioId());
		
		if (pedidoOptional.isPresent()
				&& pizzaBordaOptional.isPresent()
				&& pizzaMolhoOptional.isPresent()
				&& pizzaRecheioOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
	
				if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
						
						PizzaBorda pizzaBorda = pizzaBordaOptional.get();
						PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
						PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
	
						Pizza pizza = new Pizza(pedido, pizzaBorda, pizzaMolho, pizzaRecheio);
						
						pizzaRepository.save(pizza);
	
						pedido.adicionaPizza(pizza);  // operações com os saldos 
	
						pedidoRepository.save(pedido);
	
						URI uri = uriBuilder.path("/pedido/pizzas/{id}").buildAndExpand(pizza.getId()).toUri();
						return ResponseEntity.created(uri).body(new MontarPizzaDto(pizza));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarPizzaDto> atualizarPizza(Long pizzaId, MontarPizzaForm montarPizzaForm) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarPizzaForm.getPedidoId());
		Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(montarPizzaForm.getPizzaBordaId());
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(montarPizzaForm.getPizzaMolhoId());
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(montarPizzaForm.getPizzaRecheioId());
		
		if(pedidoOptional.isPresent()) {
			System.out.println("-==========================pedido Presente ================---");
		}
		
		if(pizzaOptional.isPresent()) {
			System.out.println("-==========================pizzaOptional Presente ================---");
		}
		
		if(pizzaBordaOptional.isPresent()) {
			System.out.println("-==========================pizzaBordaOptional Presente ================---");
		}
		
		if(pizzaMolhoOptional.isPresent()) {
			System.out.println("-==========================pizzaMolhoOptional Presente ================---");
		}
		
		if(pizzaRecheioOptional.isPresent()) {
			System.out.println("-==========================pizzaRecheioOptional Presente ================---");
		}
		
		if (pedidoOptional.isPresent() 
				&& pizzaOptional.isPresent()
//				&& pizzaBordaOptional.isPresent()
				&& pizzaMolhoOptional.isPresent()
				&& pizzaRecheioOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
				Pizza pizza = pizzaOptional.get();
	
				System.out.println("chegou aqui 123");
				
				if ((pizza.getPedido().getId() == pedido.getId()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
						
						pedido.removerPizza(pizza); // operações com os saldos 
					
						PizzaBorda pizzaBorda = pizzaBordaOptional.get();
						PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
						PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
						
						pizza.setPizzaBorda(pizzaBorda);
						pizza.setPizzaMolho(pizzaMolho);
						pizza.setPizzaRecheio(pizzaRecheio);
						pizza.CalculosPizza();
						
						pizzaRepository.save(pizza);
						
						pedido.adicionaPizza(pizza); // operações com os saldos 
	
						pedidoRepository.save(pedido);
	
						System.out.println("chegou aqui 456");
						return ResponseEntity.ok(new MontarPizzaDto(pizza));
					}
			}
		return ResponseEntity.notFound().build();
	}
	
	// deletar
	public ResponseEntity<?> removerPizzaPedido(Long pedidoId, Long pizzaId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {

			Pedido pedido = pedidoOptional.get();
			Optional <Pizza> pizzaOptional = pizzaRepository.findUmaPizzaPorIdEPedido(pedidoId, pizzaId);

			if ((pizzaOptional.isPresent()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
				pedido.removerPizza(pizzaOptional.get()); // operações com os saldos 
				pizzaRepository.delete(pizzaOptional.get());
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

}
