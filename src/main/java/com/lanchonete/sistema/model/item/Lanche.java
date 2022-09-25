package com.lanchonete.sistema.model.item;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lanchonete.sistema.model.ingrediente.LancheMolho;
import com.lanchonete.sistema.model.ingrediente.LancheRecheio;
import com.lanchonete.sistema.model.ingrediente.LancheTipoPao;
import com.lanchonete.sistema.model.pedido.Pedido;

@Entity
public class Lanche {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Item item;
	@ManyToOne
	Pedido pedido;
	@ManyToOne
	private LancheTipoPao lancheTipoPao;
	@ManyToOne
	private LancheRecheio lancheRecheio;
	@ManyToOne
	private LancheMolho lancheMolho;

	public Lanche(Pedido pedido, LancheTipoPao lancheTipoPao, LancheRecheio lancheRecheio, LancheMolho lancheMolho) {
		this.pedido = pedido;
		this.lancheTipoPao = lancheTipoPao;
		this.lancheRecheio = lancheRecheio;
		this.lancheMolho = lancheMolho;
		this.item = new Item();
		this.CalculosPrato();
		
	}
	
	public void CalculosPrato() {
		this.item.setTotalItem(   // se refere a esta classe 
				lancheTipoPao.getIngrediente().getPrecoVenda()
				.add(lancheRecheio.getIngrediente().getPrecoVenda()
						.add(lancheMolho.getIngrediente().getPrecoVenda())));
		
		this.item.setPesoPrato(
				lancheTipoPao.getIngrediente().getPeso() +
				lancheRecheio.getIngrediente().getPeso() +
				lancheMolho.getIngrediente().getPeso()) ;
		
	}

	public Lanche() {
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
