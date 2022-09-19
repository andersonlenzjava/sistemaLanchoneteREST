package com.lanchonete.sistema.validacao;

public class ItemJaExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemJaExisteException(String msg) {
		super(msg);
	}
}
