package com.br.stefanini.votacao.bos;

import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IAssociadoBO {
	public AssociadoDTO selectAssociado(String cpfAssoc) throws BDException;
	
	public AssociadoDTO selectAssociadoId(Integer idAssoc) throws BDException;

}
