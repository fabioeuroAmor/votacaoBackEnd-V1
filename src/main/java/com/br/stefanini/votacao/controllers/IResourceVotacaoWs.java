package com.br.stefanini.votacao.controllers;


public interface IResourceVotacaoWs {
	
	public static final String DESCRICAO_ERRO_500 = "Erro inesperado ao consultar Servi�o. Tente novamente mais tarde.";	
	public static final String DESCRICAO_ERRO_LEGADO_400 = "O dado informado est� mal formado. A estrutura deve corresponder a um dado válido conforme o tipo de conte�do informado.";
	public static final String DESCRICAO_SUCESSO_LEGADO_200 = "Sucesso ao processar a requisição. Deve ser avaliado se houve erro ou sucesso a partir da análise do campo mensagens";

	public static final String DESCRICAO_ERRO_400 = "O dado informado está mal formado. A estrutura deve corresponder a um dado v�lido conforme o tipo de conte�do informado. "
			+ "Os dados informados não atendem as regra de negócio por parte do serviço. Consultar o código e descrição das mensagens de retorno.";
	public static final String DESCRICAO_SUCESSO_200 = "Sucesso ao processar a requisção.";
	public static final String DESCRICAO_SUCESSO_BACK_END_200 = "===> Sucesso ao processar a requisção no BACK-END!!!";
}
