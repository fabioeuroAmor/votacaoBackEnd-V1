package com.br.stefanini.votacao.dtos;

public class ResultadoVotacaoDTO {
	
	private Integer idPauta;
	private Integer totalDeVotos;	
	private String dc_voto;
	private Integer id_associado;
	private String dc_pauta;
	private String nm_associado;
	
	public String getDc_pauta() {
		return dc_pauta;
	}
	public void setDc_pauta(String dc_pauta) {
		this.dc_pauta = dc_pauta;
	}
	public String getNm_associado() {
		return nm_associado;
	}
	public void setNm_associado(String nm_associado) {
		this.nm_associado = nm_associado;
	}
	public Integer getIdPauta() {
		return idPauta;
	}
	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}
	
	public String getDc_voto() {
		return dc_voto;
	}
	public void setDc_voto(String dc_voto) {
		this.dc_voto = dc_voto;
	}
	public Integer getId_associado() {
		return id_associado;
	}
	public void setId_associado(Integer id_associado) {
		this.id_associado = id_associado;
	}
	
	public Integer getTotalDeVotos() {
		return totalDeVotos;
	}
	public void setTotalDeVotos(Integer totalDeVotos) {
		this.totalDeVotos = totalDeVotos;
	}	

	@Override
	public String toString() {
		return "ResultadoVotacaoDTO [idPauta=" + idPauta + ", totalDeVotos=" + totalDeVotos + ", dc_voto=" + dc_voto
				+ ", id_associado=" + id_associado + ", dc_pauta=" + dc_pauta + ", nm_associado=" + nm_associado + "]";
	}	

}
