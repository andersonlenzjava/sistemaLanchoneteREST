package com.lanchonete.sistema.model.item;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;

@Entity
public class Lanche implements CalculoPrato {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Item item;
	@ManyToOne
	private LancheTipoPao lancheTipoPao;
	@ManyToOne
	private LancheRecheio lancheRecheio;
	@ManyToOne
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
