package com.br.stefanini.votacao.bos;

import com.br.stefanini.votacao.daos.ContabilizacaoDAO;
import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.dtos.ContabilizacaoDTO;
import com.br.stefanini.votacao.dtos.PautaDTO;
import com.br.stefanini.votacao.dtos.ResultadoVotacaoDTO;
import com.br.stefanini.votacao.enums.EnumVoto;
import com.br.stefanini.votacao.exception.BDException;

public class ContabilizacaoBO implements IContabilizacaoBO{
	
	 private static ContabilizacaoBO instance;
		
		public static ContabilizacaoBO getInstance() {
			if (instance == null) {
				instance = new ContabilizacaoBO();
			}
			return instance;
		}
		
		public static final String MN_ERRO_VOTO ="A informação de voto passada ao sistema só pode ser SIM ou NÃO";
		
		public static final String MN_VALIDA_VOTO ="Este associado, já realizou um voto há esta pauta!!!";
	
	@Override
	public void insertContabilizacao(ContabilizacaoDTO contabilizacaoDTO) throws BDException {
		try {
			
			if(!contabilizacaoDTO.getDcVoto().equalsIgnoreCase(EnumVoto.SIM.getDcVoto()) && !contabilizacaoDTO.getDcVoto().equalsIgnoreCase(EnumVoto.NAO.getDcVoto())) {
				throw new Exception(MN_ERRO_VOTO);
			}
			
			AssociadoDTO associadoDTO = AssociadoBO.getInstance().selectAssociado(contabilizacaoDTO.getAssociadoDTO().getCpf());
			
			if(associadoDTO.getIdAssociado()!=null) {
				contabilizacaoDTO.setAssociadoDTO(associadoDTO);
			} 
			
			PautaDTO pautaDTO = PautaBO.getInstance().verificaExistenciaPauta(contabilizacaoDTO.getPautaDTO().getNmPauta());
			
			if(pautaDTO.getIdPauta()!=null) {
				contabilizacaoDTO.setPautaDTO(pautaDTO);
			}else {
				throw new Exception(PautaBO.MN_ERRO_PAUTA_NAO_CADASTRADA);	
			}
			
			//Verificar se ja exeiste este voto contabilizado
			if(associadoDTO.getIdAssociado()!=null && pautaDTO.getIdPauta()!=null) {
				
			}
		    
			//Validar se ja houve voto deste associado a esta pauta
			if(validaContabilizacao(contabilizacaoDTO.getAssociadoDTO().getIdAssociado(), contabilizacaoDTO.getPautaDTO().getIdPauta())) {
				throw new Exception(MN_VALIDA_VOTO);
			}else {
				ContabilizacaoDAO.getInstance().insertContabilizacao(contabilizacaoDTO);
			}
			
			
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
		
	}

	@Override
	public Boolean validaContabilizacao(Integer idAssociado, Integer idPauta) throws BDException {
		
		Boolean retorno = false;
		try {
			retorno = ContabilizacaoDAO.getInstance().validaContabilizacao(idAssociado, idPauta);
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
		
		
		return retorno;
	}

	@Override
	public ContabilizacaoDTO realizaContabilizacaoDosVotos() throws BDException {
		
		ContabilizacaoDTO contabilizacaoDTO = new ContabilizacaoDTO();
		
		try {
			contabilizacaoDTO = ContabilizacaoDAO.getInstance().realizaContabilizacaoDosVotos();
			
			for (ResultadoVotacaoDTO resultadoVotacaoDTO : contabilizacaoDTO.getArrayResultadoVotacao()) {
				resultadoVotacaoDTO.setNm_associado(AssociadoBO.getInstance().selectAssociadoId(resultadoVotacaoDTO.getId_associado()).getNome());
				resultadoVotacaoDTO.setDc_pauta(PautaBO.getInstance().selectPautaId(resultadoVotacaoDTO.getIdPauta()).getNmPauta());
			}		
			
			
		} catch (Exception e) {
			throw new BDException(e.getMessage());
		}
		
		return contabilizacaoDTO;
	}

}
