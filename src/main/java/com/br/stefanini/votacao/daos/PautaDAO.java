package com.br.stefanini.votacao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.br.stefanini.votacao.dtos.PautaDTO;
import com.br.stefanini.votacao.exception.BDException;

public class PautaDAO extends PersistenceManagerDAO implements IPaultaDAO{
	
    private static PautaDAO instance;
	
	public static PautaDAO getInstance() {
		if (instance == null) {
			instance = new PautaDAO();
		}
		return instance;
	}
	
	 private static int quantPautaInseridos = 0;
     private static int quantMaximaPautaCache = 100;
	
	public void insertPauta(PautaDTO pautaDTO) throws BDException {
		 Connection conn = null;
		 PreparedStatement stmt = null;	
		 
		try {
			conn = PersistenceManagerDAO.getConnection();
			stmt = conn.prepareStatement(INSERT_PAUTA);
			stmt.setString(1, pautaDTO.getNmPauta());
			
			stmt.executeUpdate();			
			//conn.commit();
			
			//Gerenciamento de commit ao banco
//			quantPautaInseridos++;
//			if (quantPautaInseridos == quantMaximaPautaCache) {
//				conn.commit();
//				quantPautaInseridos = 0;
//				System.out.println("Bloco de ["+quantMaximaPautaCache+"] inseridas");
//			}
			
		} catch (SQLException e) {
			throw new BDException(e.getMessage());
		} catch (Exception e) {			
			e.printStackTrace();
		}finally {
			PersistenceManagerDAO.closeStatement(stmt);
			PersistenceManagerDAO.closeConnection(conn);
		}	
	}

	@Override
	public PautaDTO selectPauta(String nmPauta) throws BDException {
		
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 
		 PautaDTO pautaDTO= new PautaDTO();
		 
		 try {
			 conn = PersistenceManagerDAO.getConnection();
			 stmt = conn.prepareStatement(SELECT_PAUTA_PELO_NOME);
			 stmt.setString(1, nmPauta);
			 
			 rs = stmt.executeQuery(); 
			 
			 while (rs.next()){
				 pautaDTO.setIdPauta(rs.getInt("id_pauta"));
				 pautaDTO.setNmPauta(rs.getString("nm_pauta"));
		    }		 
			 
		 }catch (Exception e) {				
				throw new BDException(e.getMessage());
		  } finally {
				PersistenceManagerDAO.closeResultSet(rs);
				PersistenceManagerDAO.closeStatement(stmt);
				PersistenceManagerDAO.closeConnection(conn);
		 }	 
		 
		return pautaDTO;	
		
	}

	@Override
	public PautaDTO selectPautaId(Integer idPauta) throws BDException {
		Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 
		 PautaDTO pautaDTO= new PautaDTO();
		 
		 try {
			 conn = PersistenceManagerDAO.getConnection();
			 stmt = conn.prepareStatement(SELECT_PAUTA_PELO_ID);
			 stmt.setInt(1, idPauta);
			 
			 rs = stmt.executeQuery(); 
			 
			 while (rs.next()){
				 pautaDTO.setIdPauta(rs.getInt("id_pauta"));
				 pautaDTO.setNmPauta(rs.getString("nm_pauta"));
		    }		 
			 
		 }catch (Exception e) {				
				throw new BDException(e.getMessage());
		  } finally {
				PersistenceManagerDAO.closeResultSet(rs);
				PersistenceManagerDAO.closeStatement(stmt);
				PersistenceManagerDAO.closeConnection(conn);
		 }	
		 
		return pautaDTO;
	}	

}
