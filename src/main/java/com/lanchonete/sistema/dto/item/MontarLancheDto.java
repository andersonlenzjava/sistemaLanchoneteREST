package com.lanchonete.sistema.dto.item;

import org.springframework.data.domain.Page;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.model.item.Item;
import com.lanchonete.sistema.model.item.Lanche;

public class MontarLancheDto {

	Long id;
	Item item;
	LancheTipoPao lancheTipoPao;
	LancheRecheio lancheRecheio;
	LancheMolho lancheMolho;
	
	public MontarLancheDto(Lanche lanche) {
		this.id = lanche.getId();
		lanche.calculaPesoPrato();
		lanche.calculaPrecoPrato();
		this.item = lanche.getItem();
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
