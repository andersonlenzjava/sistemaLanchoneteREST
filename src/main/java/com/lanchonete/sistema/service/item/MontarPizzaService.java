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
	public Page<MontarPizzaDto> listarPizzas(Pageable paginacao) {
		Page<Pizza> pizzas = pizzaRepository.findAll(paginacao);
		return MontarPizzaDto.converter(pizzas);
	}

	//get id
	public ResponseEntity<MontarPizzaDto> listarPizzaPorId(Long id) {
		Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
		if (pizzaOptional.isPresent()) {
			return ResponseEntity.ok(MontarPizzaDto.converterUmaPizza(pizzaOptional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	// post
	public ResponseEntity<MontarPizzaDto> cadastrarPizza(MontarPizzaForm montarPizzaForm,
			UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarPizzaForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();

			if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {

				Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(montarPizzaForm.getPizzaBordaId());
				Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(montarPizzaForm.getPizzaMolhoId());
				Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(montarPizzaForm.getPizzaRecheioId());

				if (pizzaBordaOptional.isPresent() && pizzaMolhoOptional.isPresent()
						&& pizzaRecheioOptional.isPresent()) {
					
					PizzaBorda pizzaBorda = pizzaBordaOptional.get();
					PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
					PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();

					Pizza pizza = new Pizza(pizzaBorda, pizzaMolho, pizzaRecheio);
					
					pizzaRepository.save(pizza); // aqui o lanche passa a ter ID 

					pedido.adicionaPizza(pizza);

					pedidoRepository.save(pedido);

					URI uri = uriBuilder.path("/montarLanche/{id}").buildAndExpand(pizza.getId()).toUri();
					return ResponseEntity.created(uri).body(new MontarPizzaDto(pizza));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarPizzaDto> atualizarPizza(Long id, MontarPizzaForm montarPizzaForm) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarPizzaForm.getPedidoId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();

			if (((pedido.getListaPizza().get(Math.toIntExact(id))) != null)
					&& (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {

				Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(montarPizzaForm.getPizzaBordaId());
				Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(montarPizzaForm.getPizzaMolhoId());
				Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(montarPizzaForm.getPizzaRecheioId());

				if (pizzaBordaOptional.isPresent() && pizzaMolhoOptional.isPresent()
						&& pizzaRecheioOptional.isPresent()) {
					
					PizzaBorda pizzaBorda = pizzaBordaOptional.get();
					PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
					PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
					
					Pizza pizza = pedido.getListaPizza().get(Math.toIntExact(id));
					
					pizza.setPizzaBorda(pizzaBorda);
					pizza.setPizzaMolho(pizzaMolho);
					pizza.setPizzaRecheio(pizzaRecheio);
					
					pizzaRepository.save(pizza);
					
					pedido.getListaPizza().set(Math.toIntExact(pizza.getId()), pizza);

					pedidoRepository.save(pedido);

					return ResponseEntity.ok(new MontarPizzaDto(pizza));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	//delete
	public ResponseEntity<?> removerPizza(Long id) {
		Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
		if (pizzaOptional.isPresent()) {
			pizzaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
