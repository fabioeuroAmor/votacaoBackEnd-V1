package com.br.stefanini.votacao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.stefanini.votacao.dtos.RetornoDTO;
import com.br.stefanini.votacao.exception.BDException;

public interface IGerenciaVotacaoController {
	
	 public ResponseEntity<String> validaAssociadoPeloCpfString(String cpfAssoc) throws BDException;	 
	 
	 public ResponseEntity<RetornoDTO> validaAssociadoPeloCpf(@PathVariable(value = "cpfAssoc") String cpfAssoc) throws BDException;
	 
	 public ResponseEntity<RetornoDTO> cadastraPauta(@PathVariable(value = "nomePauta") String nomePauta) throws BDException;
	 
	 public ResponseEntity<RetornoDTO> realizarVoto(@RequestParam String cpfAssoc, @RequestParam String nomePauta, @RequestParam String voto) throws BDException;
	 
	 public ResponseEntity<RetornoDTO> realizarContabilizacaoDosVotos() throws BDException;	 

}
