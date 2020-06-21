package com.br.stefanini.votacao.dtos;

import java.util.ArrayList;

public class ContabilizacaoDTO{
	
	private Integer idContabilizacao;
	private AssociadoDTO associadoDTO;
	private PautaDTO pautaDTO;
	private String dcVoto;
	private ArrayList<ResultadoVotacaoDTO> arrayResultadoVotacao =  new ArrayList<ResultadoVotacaoDTO>();
	
	public Integer getIdContabilizacao() {
		return idContabilizacao;
	}
	public void setIdContabilizacao(Integer idContabilizacao) {
		this.idContabilizacao = idContabilizacao;
	}
	public AssociadoDTO getAssociadoDTO() {
		return associadoDTO;
	}
	public void setAssociadoDTO(AssociadoDTO associadoDTO) {
		this.associadoDTO = associadoDTO;
	}
	public PautaDTO getPautaDTO() {
		return pautaDTO;
	}
	public void setPautaDTO(PautaDTO pautaDTO) {
		this.pautaDTO = pautaDTO;
	}
	
	public String getDcVoto() {
		return dcVoto;
	}
	public void setDcVoto(String dcVoto) {
		this.dcVoto = dcVoto;
	}
	public ArrayList<ResultadoVotacaoDTO> getArrayResultadoVotacao() {
		return arrayResultadoVotacao;
	}
	public void setArrayResultadoVotacao(ArrayList<ResultadoVotacaoDTO> arrayResultadoVotacao) {
		this.arrayResultadoVotacao = arrayResultadoVotacao;
	}
	
	@Override
	public String toString() {
		return "ContabilizacaoDTO [idContabilizacao=" + idContabilizacao + ", associadoDTO=" + associadoDTO
				+ ", pautaDTO=" + pautaDTO + ", dcVoto=" + dcVoto + ", arrayResultadoVotacao=" + arrayResultadoVotacao
				+ "]";
	}

}
