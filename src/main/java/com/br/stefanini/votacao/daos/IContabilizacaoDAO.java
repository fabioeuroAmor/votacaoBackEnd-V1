package com.br.stefanini.votacao.daos;

import com.br.stefanini.votacao.dtos.ContabilizacaoDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IContabilizacaoDAO {
	
	public static final String INSERT_CONTABILIZACAO = "INSERT INTO TBL_CONTABILIZACAO" +
			   " (id_associado, id_pauta,dc_voto) " +
			   " values (?,?,?)";
	
	public static final String SELECT_VALIDA_CONTABILIZACAO_DO_VOTO = "SELECT * FROM TBL_CONTABILIZACAO WHERE id_associado = ? AND id_pauta = ?";
	
	
	public static final String SELECT_RESULTADO_DA_VOTACAO = "SELECT id_pauta, count(id_pauta) As TOTAL_VOTOS_POR_PAUTA,dc_voto,id_associado"
	                                                        +" FROM TBL_CONTABILIZACAO"	
	                                                        +" GROUP BY id_pauta";

	
	public void insertContabilizacao(ContabilizacaoDTO contabilizacaoDTO) throws BDException;
	
	public Boolean validaContabilizacao(Integer idAssociado, Integer idPauta) throws BDException;	
	
	public ContabilizacaoDTO realizaContabilizacaoDosVotos () throws BDException;

}
