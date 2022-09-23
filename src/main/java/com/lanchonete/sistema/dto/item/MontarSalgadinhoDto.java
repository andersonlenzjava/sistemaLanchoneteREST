package com.lanchonete.sistema.dto.item;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;
import com.lanchonete.sistema.model.item.Item;
import com.lanchonete.sistema.model.item.Salgadinho;
import com.lanchonete.sistema.model.pedido.Pedido;

public class MontarSalgadinhoDto {

	Long id;
	Item item;
	Pedido pedido;
	SalgadinhoMassa salgadinhoMassa;
	SalgadinhoRecheio salgadinhoRecheio;
	SalgadinhoTipoPreparo salgadinhoTipoPreparo;
	
	public MontarSalgadinhoDto (Salgadinho salgadinho) {
		this.id = salgadinho.getId();
		this.item = salgadinho.getItem();
		this.pedido = salgadinho.getPedido();
		this.salgadinhoMassa = salgadinho.getSalgadinhoMassa();
		this.salgadinhoRecheio = salgadinho.getSalgadinhoRecheio();
		this.salgadinhoTipoPreparo = salgadinho.getSalgadinhoTipoPreparo();
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
	public SalgadinhoMassa getSalgadinhoMassa() {
		return salgadinhoMassa;
	}
	public void setSalgadinhoMassa(SalgadinhoMassa salgadinhoMassa) {
		this.salgadinhoMassa = salgadinhoMassa;
	}
	public SalgadinhoRecheio getSalgadinhoRecheio() {
		return salgadinhoRecheio;
	}
	public void setSalgadinhoRecheio(SalgadinhoRecheio salgadinhoRecheio) {
		this.salgadinhoRecheio = salgadinhoRecheio;
	}
	public SalgadinhoTipoPreparo getSalgadinhoTipoPreparo() {
		return salgadinhoTipoPreparo;
	}
	public void setSalgadinhoTipoPreparo(SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
		this.salgadinhoTipoPreparo = salgadinhoTipoPreparo;
	}
	
	public static Page<MontarSalgadinhoDto> converter(Page<Salgadinho> salgadinhos) {
		return salgadinhos.map(MontarSalgadinhoDto::new);
	}
	public static MontarSalgadinhoDto converterUmSalgadinho(Salgadinho salgadinho) {
		return new MontarSalgadinhoDto(salgadinho);
	}
	
}
