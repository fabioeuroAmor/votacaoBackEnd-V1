package com.br.stefanini.votacao.bos;

import com.br.stefanini.votacao.dtos.ContabilizacaoDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IContabilizacaoBO {
	
	public void insertContabilizacao(ContabilizacaoDTO contabilizacaoDTO) throws BDException;
	
	public Boolean validaContabilizacao(Integer idAssociado, Integer idPauta) throws BDException;
	
	public ContabilizacaoDTO realizaContabilizacaoDosVotos() throws BDException;

}
