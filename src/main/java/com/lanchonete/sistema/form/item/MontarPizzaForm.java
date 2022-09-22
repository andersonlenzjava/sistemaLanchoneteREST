package com.lanchonete.sistema.form.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MontarPizzaForm {

	@NotNull
	@Min(value = 0)
	Long pedidoId;
	
	@NotNull
	@Min(value = 0)
	Long pizzaBordaId;
	
	@NotNull
	@Min(value = 0)
	Long pizzaMolhoId;
	
	@NotNull
	@Min(value = 0)
	Long pizzaRecheioId;

	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Long getPizzaBordaId() {
		return pizzaBordaId;
	}
	public void setPizzaBordaId(Long pizzaBordaId) {
		this.pizzaBordaId = pizzaBordaId;
	}
	public Long getPizzaMolhoId() {
		return pizzaMolhoId;
	}
	public void setPizzaMolhoId(Long pizzaMolhoId) {
		this.pizzaMolhoId = pizzaMolhoId;
	}
	public Long getPizzaRecheioId() {
		return pizzaRecheioId;
	}
	public void setPizzaRecheioId(Long pizzaRecheioId) {
		this.pizzaRecheioId = pizzaRecheioId;
	}
}
