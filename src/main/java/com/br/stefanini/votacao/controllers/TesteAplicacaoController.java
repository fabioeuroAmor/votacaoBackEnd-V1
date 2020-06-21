package com.br.stefanini.votacao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.stefanini.votacao.bos.AssociadoBO;
import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.exception.NegocioException;

/**
 * ExampleController
 * 
 * @author danielpadua
 *
 */
@RestController
@RequestMapping("/api/teste")
public class TesteAplicacaoController implements IResourceVotacaoWs{
	
	@GetMapping("/back-end")
	public ResponseEntity<String> get() {
		String cpfAssoc = "89676718149";
		StringBuilder dadosDoBanco = new StringBuilder();
		try {
			AssociadoDTO associadoDTO = AssociadoBO.getInstance().selectAssociado(cpfAssoc);
			dadosDoBanco.append("ASSOCIADO DA BASE DE DADOS ===> ");
			dadosDoBanco.append("ID: " + associadoDTO.getIdAssociado().toString());
			dadosDoBanco.append(" CPF: " + associadoDTO.getCpf());
			dadosDoBanco.append(" NOME: " + associadoDTO.getNome());			
		} catch (NegocioException e) {			
			e.printStackTrace();
		}
		return ResponseEntity.ok(" "+ dadosDoBanco + DESCRICAO_SUCESSO_BACK_END_200);
	}
}