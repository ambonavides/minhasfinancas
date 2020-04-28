package com.reactjs.minhasfinancas.exeption;

public class ErroAutenticacao extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3137649107939214356L;
	
	
	public ErroAutenticacao(String mensagem) {
		super(mensagem);
	}
}
