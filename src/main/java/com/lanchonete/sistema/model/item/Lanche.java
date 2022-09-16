package com.lanchonete.sistema.model.item;

import java.math.BigDecimal;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;

public class Lanche implements CalculoPrato  {

	private Item item;
	private LancheTipoPao lancheTipoPao;
	private LancheRecheio lancheRecheio;
	private LancheMolho lancheMolho;

	public Lanche(LancheTipoPao lancheTipoPao, LancheRecheio lancheRecheio, LancheMolho lancheMolho) {
		this.lancheTipoPao = lancheTipoPao;
		this.lancheRecheio = lancheRecheio;
		this.lancheMolho = lancheMolho;
	}

	@Override
	public BigDecimal calculaPrecoPrato() {
		return lancheTipoPao.getIngrediente().getPrecoVenda()
				.add(lancheRecheio.getIngrediente().getPrecoVenda()
						.add(lancheMolho.getIngrediente().getPrecoVenda()));
	}

	@Override
	public double calculaPesoPrato() {
		return lancheTipoPao.getIngrediente().getPeso() +
				lancheRecheio.getIngrediente().getPeso() +
				lancheMolho.getIngrediente().getPeso();
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
}
