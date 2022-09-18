package com.lanchonete.sistema.model.item;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;

@Entity
public class Pizza implements CalculoPrato  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Item item;
	@ManyToOne
	private PizzaBorda pizzaBorda;
	@ManyToOne
	private PizzaMolho pizzaMolho;
	@ManyToOne
	private PizzaRecheio pizzaRecheio;
	
	public Pizza(PizzaBorda pizzaBorda, PizzaMolho pizzaMolho, PizzaRecheio pizzaRecheio) {
		this.pizzaBorda = pizzaBorda;
		this.pizzaMolho = pizzaMolho;
		this.pizzaRecheio = pizzaRecheio;
	}

	@Override
	public BigDecimal calculaPrecoPrato() {
		return pizzaBorda.getIngrediente().getPrecoVenda()
				.add(pizzaMolho.getIngrediente().getPrecoVenda()
						.add(pizzaRecheio.getIngrediente().getPrecoVenda()));
	}

	@Override
	public double calculaPesoPrato() {
		return pizzaBorda.getIngrediente().getPeso() +
			   pizzaMolho.getIngrediente().getPeso() +
			   pizzaRecheio.getIngrediente().getPeso();
	}

	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
