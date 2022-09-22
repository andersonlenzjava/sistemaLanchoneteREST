package com.lanchonete.sistema.form.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MontarSalgadinhoForm {
	
	@NotNull
	@Min(value = 0)
	Long pedidoId;
	
	@NotNull
	@Min(value = 0)
	Long salgadinhoMassaId;
	
	@NotNull
	@Min(value = 0)
	Long salgadinhoRecheioId;
	
	@NotNull
	@Min(value = 0)
	Long salgadinhoTipoPreparoId;

	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Long getSalgadinhoMassaId() {
		return salgadinhoMassaId;
	}
	public void setSalgadinhoMassaId(Long salgadinhoMassaId) {
		this.salgadinhoMassaId = salgadinhoMassaId;
	}
	public Long getSalgadinhoRecheioId() {
		return salgadinhoRecheioId;
	}
	public void setSalgadinhoRecheioId(Long salgadinhoRecheioId) {
		this.salgadinhoRecheioId = salgadinhoRecheioId;
	}
	public Long getSalgadinhoTipoPreparoId() {
		return salgadinhoTipoPreparoId;
	}
	public void setSalgadinhoTipoPreparoId(Long salgadinhoTipoPreparoId) {
		this.salgadinhoTipoPreparoId = salgadinhoTipoPreparoId;
	}
}
