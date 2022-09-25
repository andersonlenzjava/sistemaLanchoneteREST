package com.lanchonete.sistema.model.item;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lanchonete.sistema.model.ingrediente.SalgadinhoMassa;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoRecheio;
import com.lanchonete.sistema.model.ingrediente.SalgadinhoTipoPreparo;
import com.lanchonete.sistema.model.pedido.Pedido;

@Entity
public class Salgadinho  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Item item;
	@ManyToOne
	Pedido pedido;
	@ManyToOne
	private SalgadinhoMassa salgadinhoMassa;
	@ManyToOne
	private SalgadinhoRecheio salgadinhoRecheio;
	@ManyToOne
	private SalgadinhoTipoPreparo salgadinhoTipoPreparo;
	
	public Salgadinho(Pedido pedido, SalgadinhoMassa salgadinhoMassa, SalgadinhoRecheio salgadinhoRecheio,
			SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
		this.pedido = pedido;
		this.salgadinhoMassa = salgadinhoMassa;
		this.salgadinhoRecheio = salgadinhoRecheio;
		this.salgadinhoTipoPreparo = salgadinhoTipoPreparo;
		this.item = new Item();
		this.CalculosSalgadinho();
	}

	public Salgadinho() {
	}
	
	public void CalculosSalgadinho() {
		this.item.setTotalItem(salgadinhoMassa.getIngrediente().getPrecoVenda()
				.add(salgadinhoRecheio.getIngrediente().getPrecoVenda()
						.add(salgadinhoTipoPreparo.getIngrediente().getPrecoVenda())));
		
		this.item.setPesoPrato(salgadinhoMassa.getIngrediente().getPeso() +
				salgadinhoRecheio.getIngrediente().getPeso() +
				salgadinhoTipoPreparo.getIngrediente().getPeso()); 
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
}
