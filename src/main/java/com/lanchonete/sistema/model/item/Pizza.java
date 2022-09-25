package com.lanchonete.sistema.model.item;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;
import com.lanchonete.sistema.model.pedido.Pedido;

@Entity
public class Pizza {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Item item;
	@ManyToOne
	Pedido pedido;
	@ManyToOne
	private PizzaBorda pizzaBorda;
	@ManyToOne
	private PizzaMolho pizzaMolho;
	@ManyToOne
	private PizzaRecheio pizzaRecheio;
	
	public Pizza(Pedido pedido, PizzaBorda pizzaBorda, PizzaMolho pizzaMolho, PizzaRecheio pizzaRecheio) {
		this.pedido = pedido;
		this.pizzaBorda = pizzaBorda;
		this.pizzaMolho = pizzaMolho;
		this.pizzaRecheio = pizzaRecheio;
		this.item = new Item();
		this.CalculosPizza();
	}

	public Pizza() {
	}
	
	public void CalculosPizza() {
		this.item.setTotalItem(pizzaBorda.getIngrediente().getPrecoVenda()
				.add(pizzaMolho.getIngrediente().getPrecoVenda()
						.add(pizzaRecheio.getIngrediente().getPrecoVenda()))); 
		
		this.item.setPesoPrato(pizzaBorda.getIngrediente().getPeso() +
				pizzaMolho.getIngrediente().getPeso() +
				pizzaRecheio.getIngrediente().getPeso()); 
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
	public Pedido getPedido() {
		return pedido;
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
}
