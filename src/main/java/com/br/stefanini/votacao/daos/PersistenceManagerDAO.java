package com.br.stefanini.votacao.daos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import com.br.stefanini.votacao.util.PropertiesReader;

/**
 * Classe Abstrata que define a implementa��o base para o design pattern Data Access Object.
 *  
 * @FIXME OBS: M�todos da Hierarquia antiga foram migrados diretamente para essa classe. � necess�rio um REFACTORY.
 * 
 *@author Kamy Ponce
 */
public abstract class PersistenceManagerDAO {	
	
	private static final String DATA_SOURCE = "arqParam.properties";	
	private Connection conexao;	
	public static final String SCHEMA = "OPI.";
	
	/**
	   * Obtem uma conex�o com o datasource default.
	   * 
	   * @return <code>Connection</code> conex�o com o datasource.
	 * @throws Exception 
	   */
	  public static Connection getConnection() throws Exception {
		  PropertiesReader gp = PropertiesReader.getInstance();
		    Connection con = null;
		    
		    try {
		    	
		    	String url = gp.getProperty(DATA_SOURCE, "url");
		    	String user = gp.getProperty(DATA_SOURCE, "user");
		    	String pass = gp.getProperty(DATA_SOURCE, "pass");
		    	//pass = new Criptografia(Criptografia.PASS_FRASE).desencriptar(pass);
		    	//pass = new Criptografia().decrypt(pass, "sP81h6rB1wr2qoieqwrl5zW3");
		    	
		    	Class.forName("com.mysql.jdbc.Driver");		 	    
		 	    con = (Connection) DriverManager.getConnection(url, user, pass);
		 	   return con;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		
	  }
	  
	  /**
	   * Libera um recurso do tipo <code>ResultSet</code>.
	   * 
	   * @param rs
	   *          <code>ResultSet</code> a ser liberado.
	   */
	  public static void closeResultSet(final ResultSet rs) {
	    if (rs != null) {

	      try {
	        rs.close();
	      } catch (SQLException sqlex) {
	      }
	    }
	  }
	  
	  /**
	   * Libera um recurso do tipo <code>Statement</code>.
	   * 
	   * @param stmt
	   *          <code>Statement</code> a ser liberado.
	   */
	  public static void closeStatement(final Statement stmt) {
	    if (stmt != null) {

	      try {
	        stmt.close();
	      } catch (SQLException sqlex) {
	      }
	    }
	  }
	  
	  /**
	   * Libera um recurso do tipo <code>Connection</code>.
	   * 
	   * @param conn
	   *          <code>Connection</code> a ser liberado.
	   */
	  public static void closeConnection(final Connection conn) {
	    if (conn != null) {

	      try {
	        conn.close();
	      } catch (SQLException sqlex) {
	      }
	    }
	  }


	

	/**
	 * 
	 * Atribui o valor do tipo String ao PreparedStatement
	 * 
	 * @param posicao
	 *            A posi��o a ser atribuida no PreparedStatement
	 * @param valor
	 *            O valor a ser atribu�do
	 * @param ps
	 *            O PreparedStatement que receber� o valor
	 * @throws SQLException
	 *             Caso ocorra um problema na execu��o do m�todo
	 */
	
	protected void setString(int posicao, String valor, PreparedStatement ps) throws SQLException {

		if (valor != null && !"".equals(valor.trim())) {
			ps.setString(posicao, valor);
		} else {
			ps.setNull(posicao, Types.VARCHAR);
		}
	}


	/**
	 * 
	 * Retorna o primeiro item de uma lista ou null se a lista estiver vazia
	 * 
	 * @param lista A lista
	 * @return
	 */
	
	protected Object getPrimeiroItem(@SuppressWarnings("rawtypes") List lista) {
		if (lista != null && !lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	/**
	 * 
	 * Adiciona a cl�usula WITH ao SQL
	 * 
	 * @param sql
	 *            O sql que receber� a cl�usula
	 * @return O SQL com a cl�usula
	 */
	
	public String selectWithUR(final String sql) {
		return sql + " WITH UR";
	}	

	
	/**
	 * Retorna o valor real do atributo da tabela, ou seja, se o valor do campo inteiro for null o atributo ser� null
	 * @param rs
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public Integer getInteger(ResultSet rs, String field) throws SQLException {
		int value = rs.getInt(field);
		return rs.wasNull() ? null : new Integer(value);
	}
	
	
	/**
	 * 
	 * @param rs
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public BigInteger getBigInteger(ResultSet rs, String field) throws SQLException {
		Object value = rs.getObject(field);
		return rs.wasNull() ? null : new BigInteger(value.toString());
	}

	/**
	 * 
	 * @param rs
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public Long getLong(ResultSet rs, String field) throws SQLException {
		long value = rs.getLong(field);
		return rs.wasNull() ? null : new Long(value);
	}
	
	public Long getLong(BigDecimal value) {
		return value != null ? new Long(value.longValue()) : null;
	}
	
	public BigDecimal getBigDecimal(ResultSet rs, String field) throws SQLException {
		BigDecimal value = rs.getBigDecimal(field);
		return rs.wasNull() ? null : value;
	}
	

}
