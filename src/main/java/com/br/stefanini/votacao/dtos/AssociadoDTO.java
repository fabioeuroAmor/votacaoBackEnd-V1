package com.br.stefanini.votacao.dtos;

public class AssociadoDTO{
	
	private Integer idAssociado;
	private String cpf;
	private String nome;
	
	public Integer getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(Integer idAssociado) {
		this.idAssociado = idAssociado;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Associado [idAssociado=" + idAssociado + ", cpf=" + cpf + ", nome="+ nome+ "]";
	}

}
