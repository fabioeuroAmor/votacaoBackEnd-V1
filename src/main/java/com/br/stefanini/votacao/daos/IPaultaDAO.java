package com.br.stefanini.votacao.daos;


import com.br.stefanini.votacao.dtos.PautaDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IPaultaDAO {
	
	public static final String INSERT_PAUTA = "INSERT INTO TBL_PAUTA" +
												   " (nm_pauta) " +
												   " values (?)";
	
	public static final String SELECT_PAUTA_PELO_NOME = "SELECT * FROM TBL_PAUTA WHERE nm_pauta = ?";
	
	public static final String SELECT_PAUTA_PELO_ID = "SELECT * FROM TBL_PAUTA WHERE id_pauta = ?";
	
	public void insertPauta(PautaDTO pautaDTO) throws BDException;
	
	public PautaDTO selectPauta(String nmPauta) throws BDException;
	
	public PautaDTO selectPautaId(Integer idPauta) throws BDException;

}
