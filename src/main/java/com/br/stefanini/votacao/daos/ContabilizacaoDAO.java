package com.br.stefanini.votacao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.br.stefanini.votacao.dtos.ContabilizacaoDTO;
import com.br.stefanini.votacao.dtos.ResultadoVotacaoDTO;
import com.br.stefanini.votacao.exception.BDException;

public class ContabilizacaoDAO extends PersistenceManagerDAO implements IContabilizacaoDAO{
	
	  private static ContabilizacaoDAO instance;
		
		public static ContabilizacaoDAO getInstance() {
			if (instance == null) {
				instance = new ContabilizacaoDAO();
			}
			return instance;
		}
		
		 private static int quantContabilizacaoInseridos = 0;
	     private static int quantMaximaContabilizacaoCache = 100;

		@Override
		public void insertContabilizacao(ContabilizacaoDTO contabilizacaoDTO) throws BDException {
			
			 Connection conn = null;
			 PreparedStatement stmt = null;	
			 
			 try {
					conn = PersistenceManagerDAO.getConnection();
					stmt = conn.prepareStatement(INSERT_CONTABILIZACAO);
					stmt.setInt(1, contabilizacaoDTO.getAssociadoDTO().getIdAssociado());
					stmt.setInt(2, contabilizacaoDTO.getPautaDTO().getIdPauta());
					stmt.setString(3, contabilizacaoDTO.getDcVoto());
					
					stmt.executeUpdate();			
					
					
					//Gerenciamento de commit ao banco
//					quantContabilizacaoInseridos++;
//					if (quantContabilizacaoInseridos == quantMaximaContabilizacaoCache) {
//						conn.commit();
//						quantPautaInseridos = 0;
//						System.out.println("Bloco de ["+quantMaximaContabilizacaoCache+"] inseridas");
//					}
					
				} catch (SQLException e) {
					throw new BDException(e.getMessage());
				} catch (Exception e) {
					throw new BDException(e.getMessage());
				}finally {
					PersistenceManagerDAO.closeStatement(stmt);
					PersistenceManagerDAO.closeConnection(conn);
				}
		}

		@Override
		public Boolean validaContabilizacao(Integer idAssociado, Integer idPauta) throws BDException {
			 Connection conn = null;
			 PreparedStatement stmt = null;
			 ResultSet rs = null;
			 Boolean retorno = false; 
			 
			 try {
				 conn = PersistenceManagerDAO.getConnection();
				 stmt = conn.prepareStatement(SELECT_VALIDA_CONTABILIZACAO_DO_VOTO);
				 stmt.setInt(1, idAssociado);
				 stmt.setInt(2, idPauta);
				 rs = stmt.executeQuery(); 
				 
				 if(rs.next()){
					 retorno = true;				
			     }		 
				 
			 }catch (Exception e) {				
					throw new BDException(e.getMessage());
			  } finally {
					PersistenceManagerDAO.closeResultSet(rs);
					PersistenceManagerDAO.closeStatement(stmt);
					PersistenceManagerDAO.closeConnection(conn);
			 }	
			 
			return retorno;
		}

		@Override
		public ContabilizacaoDTO realizaContabilizacaoDosVotos() throws BDException {
			 Connection conn = null;
			 PreparedStatement stmt = null;
			 ResultSet rs = null;
			 ResultadoVotacaoDTO resultadoVotacaoDTO = null;
			 
			 ContabilizacaoDTO contabilizacaoDTO = new ContabilizacaoDTO();
			 try {
				 conn = PersistenceManagerDAO.getConnection();
				 stmt = conn.prepareStatement(SELECT_RESULTADO_DA_VOTACAO);
				
				 rs = stmt.executeQuery(); 
				 
				 while (rs.next()){
					 resultadoVotacaoDTO = new ResultadoVotacaoDTO();
					 resultadoVotacaoDTO.setIdPauta(rs.getInt("id_pauta"));
					 resultadoVotacaoDTO.setTotalDeVotos(rs.getInt("TOTAL_VOTOS_POR_PAUTA"));
					 resultadoVotacaoDTO.setDc_voto(rs.getString("TOTAL_VOTOS_POR_PAUTA"));
					 resultadoVotacaoDTO.setId_associado(rs.getInt("id_associado"));
					 contabilizacaoDTO.getArrayResultadoVotacao().add(resultadoVotacaoDTO);
					
			    }		 
				 
			 }catch (Exception e) {				
					throw new BDException(e.getMessage());
			  } finally {
					PersistenceManagerDAO.closeResultSet(rs);
					PersistenceManagerDAO.closeStatement(stmt);
					PersistenceManagerDAO.closeConnection(conn);
			 }	
			 
			return contabilizacaoDTO;
		}	     
		

}
