package com.br.stefanini.votacao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.stefanini.votacao.bos.AssociadoBO;
import com.br.stefanini.votacao.bos.ContabilizacaoBO;
import com.br.stefanini.votacao.bos.PautaBO;
import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.dtos.ContabilizacaoDTO;
import com.br.stefanini.votacao.dtos.PautaDTO;
import com.br.stefanini.votacao.dtos.RetornoDTO;
import com.br.stefanini.votacao.exception.BDException;
import com.br.stefanini.votacao.exception.NegocioException;

@RestController
@RequestMapping("/api/votacao/v1")
public class GerenciaVotacaoController implements IGerenciaVotacaoController{

	@Override
	@RequestMapping(value = "/associado/{cpfAssoc}", method = RequestMethod.GET)
	public ResponseEntity<String> validaAssociadoPeloCpfString(@PathVariable(value = "cpfAssoc") String cpfAssoc) throws BDException {
		StringBuilder dadosDoBanco = new StringBuilder();
		
		try {
			AssociadoDTO associadoDTO = AssociadoBO.getInstance().selectAssociado(cpfAssoc);
			dadosDoBanco.append("ASSOCIADO DA BASE DE DADOS ===> ");
			dadosDoBanco.append("ID: " + associadoDTO.getIdAssociado().toString());
			dadosDoBanco.append(" CPF: " + associadoDTO.getCpf());
			dadosDoBanco.append(" NOME: " + associadoDTO.getNome());			
		} catch (NegocioException e) {			
			System.err.println("Erro ao consultar o serviço: api/votacao/associado/{cpfAssoc}");
		}
		return ResponseEntity.ok(" "+ dadosDoBanco );
	}

	@Override
	@RequestMapping(value = "/users/{cpfAssoc}", method = RequestMethod.GET)
	public ResponseEntity<RetornoDTO> validaAssociadoPeloCpf(String cpfAssoc) throws BDException {
		
		AssociadoDTO associadoDTO = new AssociadoDTO();
		RetornoDTO retornoDTO = new RetornoDTO();
		try {
			 associadoDTO = AssociadoBO.getInstance().selectAssociado(cpfAssoc);
			
		}catch (NegocioException e) {			
			System.err.println("Erro ao consultar o serviço: api/votacao/users/{cpfAssoc}");
		}
		
		if(associadoDTO.getIdAssociado()!=null) {
			 retornoDTO.setModeloRetorno(associadoDTO);
			 retornoDTO.setMensagensRetorno("ABLE_TO_VOT");
			 return ResponseEntity.ok(retornoDTO);
		}else {			
			retornoDTO.setMensagensRetorno("UNABLE_TO_VOTE");
			return ResponseEntity.ok(retornoDTO);
		}
		
		
	}

	@Override
	@RequestMapping(value = "/pauta/{nomePauta}", method =  RequestMethod.POST)
	public ResponseEntity<RetornoDTO> cadastraPauta(String nomePauta) throws BDException {
		
		PautaDTO pautaDTO =  new PautaDTO();
		RetornoDTO retornoDTO = new RetornoDTO();
		try {
			 pautaDTO.setNmPauta(nomePauta);
			 
			 PautaBO.getInstance().insertPauta(pautaDTO);
			 
			 retornoDTO.setMensagensRetorno("Inserção realizada com sucesso!!!");
		}catch (NegocioException e) {	
			retornoDTO.setMensagensRetorno(e.getMessage());
			System.err.println("Erro ao consultar o serviço: api/votacao/pauta/{nomePauta}");
		}
		
		return ResponseEntity.ok(retornoDTO);
	}

	@Override
	@RequestMapping(value = "/votar", method =  RequestMethod.POST)
	public ResponseEntity<RetornoDTO> realizarVoto(@RequestParam String cpfAssoc, @RequestParam String nomePauta,@RequestParam String voto) throws BDException {	
		
		ContabilizacaoDTO contabilizacaoDTO = new ContabilizacaoDTO();
		PautaDTO pautaDTO =  new PautaDTO();
		AssociadoDTO associadoDTO = new AssociadoDTO();
		RetornoDTO retornoDTO = new RetornoDTO();
		
		try {
			pautaDTO.setNmPauta(nomePauta);
			associadoDTO.setCpf(cpfAssoc);
			
			contabilizacaoDTO.setAssociadoDTO(associadoDTO);
			contabilizacaoDTO.setPautaDTO(pautaDTO);
			contabilizacaoDTO.setDcVoto(voto);
			
			 ContabilizacaoBO.getInstance().insertContabilizacao(contabilizacaoDTO);
			 
			 retornoDTO.setMensagensRetorno("Voto realizado com sucesso!!!");
			 retornoDTO.setModeloRetorno(contabilizacaoDTO);
		}catch (NegocioException e) {	
			
			retornoDTO.setMensagensRetorno(e.getMessage());
			System.err.println("Erro ao consultar o serviço: api/votacao/vatar");
		}	
		
		return ResponseEntity.ok(retornoDTO);
	}

	 @Override
	 @RequestMapping(value = "/resultado", method = RequestMethod.GET)
	 public ResponseEntity<RetornoDTO> realizarContabilizacaoDosVotos() throws BDException {		
		
		ContabilizacaoDTO contabilizacaoDTO = new ContabilizacaoDTO();
		RetornoDTO retornoDTO = new RetornoDTO();		
		
		try {
						
			contabilizacaoDTO = ContabilizacaoBO.getInstance().realizaContabilizacaoDosVotos();
			 
			 retornoDTO.setMensagensRetorno("Resultado da votacao contabilizado!!!");
			 retornoDTO.setModeloRetorno(contabilizacaoDTO);
		}catch (NegocioException e) {	
			
			retornoDTO.setMensagensRetorno(e.getMessage());
			System.err.println("Erro ao consultar o serviço::: api/votacao/resultado/votacao");
		}	
		
		return ResponseEntity.ok(retornoDTO);
	 }	

}
