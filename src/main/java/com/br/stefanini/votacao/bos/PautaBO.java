package com.br.stefanini.votacao.bos;

import com.br.stefanini.votacao.daos.PautaDAO;
import com.br.stefanini.votacao.dtos.PautaDTO;
import com.br.stefanini.votacao.exception.BDException;

public class PautaBO implements IPautaBO{
	
	
    private static PautaBO instance;
	
	public static PautaBO getInstance() {
		if (instance == null) {
			instance = new PautaBO();
		}
		return instance;
	}
	
	public static final String MN_ERRO_PAUTA_NAO_CADASTRADA ="Esta pauta não esta cadastrada, favor realizar seu cadastro!!!";
	public static final String MN_ERRO_PAUTA_JA_CADASTRADA ="Esta pauta já esta cadastrada!!!";

	@Override
	public void insertPauta(PautaDTO pautaDTO) throws BDException {
		try {
			
			if(verificaExistenciaPauta(pautaDTO.getNmPauta()).getIdPauta()!=null) {
				throw new BDException(MN_ERRO_PAUTA_JA_CADASTRADA);
			}else {
				PautaDAO.getInstance().insertPauta(pautaDTO);	
			}
						
			
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}		
		
	}

	
	@Override
	public PautaDTO verificaExistenciaPauta(String nmPauta) throws BDException {
		PautaDTO pautaDTO = new PautaDTO();
		try {
			 pautaDTO = PautaDAO.getInstance().selectPauta(nmPauta);			 
			 
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
		
		return pautaDTO;
	}


	@Override
	public PautaDTO selectPautaId(Integer idPauta) throws BDException {
		PautaDTO pautaDTO = new PautaDTO();
		try {
			 pautaDTO = PautaDAO.getInstance().selectPautaId(idPauta);			 
			 
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
		
		return pautaDTO;
	}
	
	
}
