package com.lanchonete.sistema.model.item;

import java.math.BigDecimal;

import com.lanchonete.sistema.model.ingrediente.PizzaBorda;
import com.lanchonete.sistema.model.ingrediente.PizzaMolho;
import com.lanchonete.sistema.model.ingrediente.PizzaRecheio;

public class Pizza implements CalculoPrato  {

	private Item item;
	private PizzaBorda pizzaBorda;
	private PizzaMolho pizzaMolho;
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
