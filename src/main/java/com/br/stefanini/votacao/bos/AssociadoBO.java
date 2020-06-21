package com.br.stefanini.votacao.bos;

import com.br.stefanini.votacao.daos.AssociadoDAO;
import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.exception.BDException;
import com.br.stefanini.votacao.util.GeraCpfCnpj;

public class AssociadoBO implements IAssociadoBO{
	
	private static AssociadoBO instance;
	
	public static AssociadoBO getInstance() {
		if (instance == null) {
			instance = new AssociadoBO();
		}
		return instance;
	}

	
	public static final String MN_ERRO_CPF ="Favor informar um CPF válido!!!";
	public static final String MN_ASSOCIADO_INEXISTENTE ="Este associado não foi cadastrado, favor cadastra-lo!!!";
	
	@Override
	public AssociadoDTO selectAssociado(String cpfAssoc) throws BDException {
		
		AssociadoDTO associadoDTO = new AssociadoDTO();
		GeraCpfCnpj gerador = new GeraCpfCnpj();
		try {
			if(gerador.isCPF(cpfAssoc)) {
				associadoDTO = AssociadoDAO.getInstance().selectAssociado(cpfAssoc);
				 if(associadoDTO.getIdAssociado()==null) {
					 throw new BDException(MN_ASSOCIADO_INEXISTENTE);
				 }
				 System.out.println("teste gggg");
				
			}else {
				throw new BDException(MN_ERRO_CPF);
			}
			
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}		
		return associadoDTO;		
	}

	@Override
	public AssociadoDTO selectAssociadoId(Integer idAssoc) throws BDException {
		
		AssociadoDTO associadoDTO = new AssociadoDTO();
		try {			
			associadoDTO = AssociadoDAO.getInstance().selectAssociadoId(idAssoc);
			 if(associadoDTO.getIdAssociado()==null) {
					 throw new BDException(MN_ASSOCIADO_INEXISTENTE);
			 }		
			
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}	
		
		return associadoDTO;
	}



}
