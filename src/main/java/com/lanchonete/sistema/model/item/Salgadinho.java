package com.lanchonete.sistema.model.item;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;

@Entity
public class Salgadinho implements CalculoPrato  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Item item;
	@ManyToOne
	private SalgadinhoMassa salgadinhoMassa;
	@ManyToOne
	private SalgadinhoRecheio salgadinhoRecheio;
	@ManyToOne
	private SalgadinhoTipoPreparo salgadinhoTipoPreparo;
	
	public Salgadinho(SalgadinhoMassa salgadinhoMassa, SalgadinhoRecheio salgadinhoRecheio,
			SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
		this.salgadinhoMassa = salgadinhoMassa;
		this.salgadinhoRecheio = salgadinhoRecheio;
		this.salgadinhoTipoPreparo = salgadinhoTipoPreparo;
	}

	@Override
	public BigDecimal calculaPrecoPrato() {
		return salgadinhoMassa.getIngrediente().getPrecoVenda()
				.add(salgadinhoRecheio.getIngrediente().getPrecoVenda()
						.add(salgadinhoTipoPreparo.getIngrediente().getPrecoVenda()));
	}

	@Override
	public double calculaPesoPrato() {
		return salgadinhoMassa.getIngrediente().getPeso() +
			   salgadinhoRecheio.getIngrediente().getPeso() +
			   salgadinhoTipoPreparo.getIngrediente().getPeso();
	}

	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
}
