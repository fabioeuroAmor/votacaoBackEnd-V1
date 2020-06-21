package com.br.stefanini.votacao.bos;

import com.br.stefanini.votacao.dtos.PautaDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IPautaBO {
	
	public void insertPauta(PautaDTO pautaDTO) throws BDException;
	
	public PautaDTO verificaExistenciaPauta(String nmPauta) throws BDException;
	
	public PautaDTO selectPautaId(Integer idPauta) throws BDException;

}
