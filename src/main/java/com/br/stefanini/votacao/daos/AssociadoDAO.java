package com.br.stefanini.votacao.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.br.stefanini.votacao.dtos.AssociadoDTO;
import com.br.stefanini.votacao.exception.BDException;

public class AssociadoDAO extends PersistenceManagerDAO implements IAssociadoDAO{
	
	private static AssociadoDAO instance;
	
	public static AssociadoDAO getInstance() {
		if (instance == null) {
			instance = new AssociadoDAO();
		}
		return instance;
	}
	
	 public AssociadoDTO selectAssociado(String cpfAssoc) throws BDException {
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 AssociadoDTO associadoDTO= new AssociadoDTO();
				 
		 try {
			 conn = PersistenceManagerDAO.getConnection();
			 stmt = conn.prepareStatement(SELECT_ASSOCIADO);
			 stmt.setString(1, cpfAssoc);
			 rs = stmt.executeQuery(); 
			 while (rs.next()){
				 associadoDTO.setIdAssociado(rs.getInt("id_associado"));
				 associadoDTO.setCpf(rs.getString("dc_cpf"));
				 associadoDTO.setNome(rs.getString("nm_associado"));				
		    }		 
			 
		 }catch (Exception e) {				
				throw new BDException(e.getMessage());
		  } finally {
				PersistenceManagerDAO.closeResultSet(rs);
				PersistenceManagerDAO.closeStatement(stmt);
				PersistenceManagerDAO.closeConnection(conn);
		 }	 
		 
		return associadoDTO;
		 
	 }

	@Override
	public AssociadoDTO selectAssociadoId(Integer idAssoc) throws BDException {
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 AssociadoDTO associadoDTO= new AssociadoDTO();
		 try {
			 conn = PersistenceManagerDAO.getConnection();
			 stmt = conn.prepareStatement(SELECT_ASSOCIADO_ID);
			 stmt.setInt(1, idAssoc);
			 rs = stmt.executeQuery(); 
			 while (rs.next()){
				 associadoDTO.setIdAssociado(rs.getInt("id_associado"));
				 associadoDTO.setCpf(rs.getString("dc_cpf"));
				 associadoDTO.setNome(rs.getString("nm_associado"));				
		    }		 
			 
		 }catch (Exception e) {				
				throw new BDException(e.getMessage());
		  } finally {
				PersistenceManagerDAO.closeResultSet(rs);
				PersistenceManagerDAO.closeStatement(stmt);
				PersistenceManagerDAO.closeConnection(conn);
		 }	 
		 
		return associadoDTO;
	}
	 
	 

}
