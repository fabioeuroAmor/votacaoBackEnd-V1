package com.br.stefanini.votacao.enums;

public enum EnumVoto {

	SIM(1, "SIM"), 
	NAO(2, "NÃO");

	private Integer idVoto;
	private String	dcVoto;
	
	private EnumVoto(Integer idVoto, String dcVoto) {

		this.idVoto = idVoto; 
		this.dcVoto = dcVoto; 
	}

	public Integer getIdVoto() {
		return idVoto;
	}

	public void setIdVoto(Integer idVoto) {
		this.idVoto = idVoto;
	}

	public String getDcVoto() {
		return dcVoto;
	}

	public void setDcVoto(String dcVoto) {
		this.dcVoto = dcVoto;
	}	
	
}
