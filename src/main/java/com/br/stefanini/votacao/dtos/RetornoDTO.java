package com.br.stefanini.votacao.dtos;

import java.io.Serializable;

/**
 * Interface de marca��o que representa o design pattern  Data Transfer Object.
 * 
 * @author Kamy Ponce
 *
 */
public class RetornoDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Object modeloRetorno;
	private String mensagensRetorno;
	public Object getModeloRetorno() {
		return modeloRetorno;
	}
	public void setModeloRetorno(Object modeloRetorno) {
		this.modeloRetorno = modeloRetorno;
	}
	public String getMensagensRetorno() {
		return mensagensRetorno;
	}
	public void setMensagensRetorno(String mensagensRetorno) {
		this.mensagensRetorno = mensagensRetorno;
	}

}
