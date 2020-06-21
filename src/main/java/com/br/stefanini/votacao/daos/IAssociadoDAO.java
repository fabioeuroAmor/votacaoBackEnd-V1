package com.br.stefanini.votacao.daos;

import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IAssociadoDAO {
	
	 public static final String SELECT_ASSOCIADO = "SELECT * FROM TBL_ASSOCIADOS WHERE dc_cpf = ?";
	 
	 public static final String SELECT_ASSOCIADO_ID = "SELECT * FROM TBL_ASSOCIADOS WHERE id_associado = ?";
	 
	 public AssociadoDTO selectAssociado(String cpfAssoc) throws BDException;
	 
	 public AssociadoDTO selectAssociadoId(Integer idAssoc) throws BDException;

}
