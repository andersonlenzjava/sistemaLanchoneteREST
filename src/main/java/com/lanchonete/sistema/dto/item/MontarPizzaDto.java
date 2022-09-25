package com.lanchonete.sistema.dto.item;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;
import com.lanchonete.sistema.model.item.Item;
import com.lanchonete.sistema.model.item.Pizza;
import com.lanchonete.sistema.model.pedido.Pedido;

public class MontarPizzaDto {

	Long id;
	Item item;
	Pedido pedido;
	PizzaBorda pizzaBorda;
	PizzaMolho pizzaMolho;
	PizzaRecheio pizzaRecheio;
	
	public MontarPizzaDto(Pizza pizza) {
		this.id = pizza.getId();
		this.item = pizza.getItem();
		this.pedido = pizza.getPedido();
		this.pizzaBorda = pizza.getPizzaBorda();
		this.pizzaMolho = pizza.getPizzaMolho();
		this.pizzaRecheio = pizza.getPizzaRecheio();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Long getPedido() {
		return pedido.getId();
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public PizzaBorda getPizzaBorda() {
		return pizzaBorda;
	}
	public void setPizzaBorda(PizzaBorda pizzaBorda) {
		this.pizzaBorda = pizzaBorda;
	}
	public PizzaMolho getPizzaMolho() {
		return pizzaMolho;
	}
	public void setPizzaMolho(PizzaMolho pizzaMolho) {
		this.pizzaMolho = pizzaMolho;
	}
	public PizzaRecheio getPizzaRecheio() {
		return pizzaRecheio;
	}
	public void setPizzaRecheio(PizzaRecheio pizzaRecheio) {
		this.pizzaRecheio = pizzaRecheio;
	}
	
	public static Page<MontarPizzaDto> converter(Page<Pizza> pizzas) {
		return pizzas.map(MontarPizzaDto::new);
	}
	public static MontarPizzaDto converterUmaPizza(Pizza pizza) {
		return new MontarPizzaDto(pizza);
	}
	
}
