package com.br.stefanini.votacao.daos;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;




/**
 * Classe Abstrata que define a implementa��o base para o design pattern Data Access Object.
 *  
 * @FIXME OBS: M�todos da Hierarquia antiga foram migrados diretamente para essa classe. � necess�rio um REFACTORY.
 * 
 *@author Kamy Ponce
 */
public abstract class AbstractManterDAO {
	
	private Connection conexao;
	
	public static final String SCHEMA = "OPI.";

	
	
	/**
	 * Obt�m a conex�o com o banco de dados.
	 * 
	 * @return A Conex�o com o banco de dados
	 * @throws SQLException Caso ocorra algum problema ao obter a conex�o
	 */
	public Connection getConnection() throws SQLException {
		return conexao;
	}

	/**
	 * Atribui a conex�o com o banco de dados.
	 * 
	 * @param conn A conex�o que ser� atribu�da
	 * @throws SQLException Caso ocorra algum problema ao atribuir a conex�o
	 */
	public void setConnection(Connection conexao) throws SQLException {
		this.conexao = conexao;
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
