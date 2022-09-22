package com.lanchonete.sistema.form.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MontarLancheForm {

	@NotNull
	@Min(value = 0)
	Long pedidoId;
	
	@NotNull
	@Min(value = 0)
	Long lancheTipoPaoId;
	
	@NotNull
	@Min(value = 0)
	Long lancheRecheioId;
	
	@NotNull
	@Min(value = 0)
	Long lancheMolhoId;

	
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Long getLancheTipoPaoId() {
		return lancheTipoPaoId;
	}
	public void setLancheTipoPaoId(Long lancheTipoPaoId) {
		this.lancheTipoPaoId = lancheTipoPaoId;
	}
	public Long getLancheRecheioId() {
		return lancheRecheioId;
	}
	public void setLancheRecheioId(Long lancheRecheioId) {
		this.lancheRecheioId = lancheRecheioId;
	}
	public Long getLancheMolhoId() {
		return lancheMolhoId;
	}
	public void setLancheMolhoId(Long lancheMolhoId) {
		this.lancheMolhoId = lancheMolhoId;
	}
	
}
