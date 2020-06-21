package com.br.stefanini.votacao.dtos;

public class PautaDTO{
	
	private Integer idPauta;
	private String nmPauta;
	
	public Integer getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}
	public String getNmPauta() {
		return nmPauta;
	}
	public void setNmPauta(String nmPauta) {
		this.nmPauta = nmPauta;
	}
	
	@Override
	public String toString() {
		return "PautaDTO [idPauta=" + idPauta + ", nmPauta=" + nmPauta + "]";
	}	

}
