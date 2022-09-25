package com.lanchonete.sistema.dto.item;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.model.item.Item;
import com.lanchonete.sistema.model.item.Lanche;
import com.lanchonete.sistema.model.pedido.Pedido;

public class MontarLancheDto {

	Long id;
	Item item;
	Pedido pedido;
	LancheTipoPao lancheTipoPao;
	LancheRecheio lancheRecheio;
	LancheMolho lancheMolho;
	
	public MontarLancheDto(Lanche lanche) {
		this.id = lanche.getId();
		this.item = lanche.getItem();
		this.pedido = lanche.getPedido();
		this.lancheTipoPao = lanche.getLancheTipoPao();
		this.lancheRecheio = lanche.getLancheRecheio();
		this.lancheMolho = lanche.getLancheMolho();
		
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
	public LancheTipoPao getLancheTipoPao() {
		return lancheTipoPao;
	}
	public void setLancheTipoPao(LancheTipoPao lancheTipoPao) {
		this.lancheTipoPao = lancheTipoPao;
	}
	public LancheRecheio getLancheRecheio() {
		return lancheRecheio;
	}
	public void setLancheRecheio(LancheRecheio lancheRecheio) {
		this.lancheRecheio = lancheRecheio;
	}
	public LancheMolho getLancheMolho() {
		return lancheMolho;
	}
	public void setLancheMolho(LancheMolho lancheMolho) {
		this.lancheMolho = lancheMolho;
	}
	
	public static Page<MontarLancheDto> converter(Page<Lanche> lanches) {
		return lanches.map(MontarLancheDto::new);
	}

	public static MontarLancheDto converterUmLanche(Lanche lanche) {
		return new MontarLancheDto(lanche);
	}

}
