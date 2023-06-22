// Objeto de acesso a dados (Data Acess Object - DAO)


package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contatos;


public class ContatoDAO{
	
	//Aqui vai ocorrer toda a regra de negócio
	
	public void save(Contatos contato) {
		
		String sql = "INSERT INTO Contatos (nome,idade,telefone,email,dataCadastro) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			//Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getTelefone());
			pstm.setString(4, contato.getEmail());
			pstm.setDate(5, java.sql.Date.valueOf(contato.getDataCadastro()));
			
			//Executar a query
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void update(Contatos contato) {
		
		String sql = "update contatos set nome = ?, idade = ?, telefone = ?, email = ?, dataCadastro = ? "+
		"Where codigo = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a classe para executar a query
			pstm = conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getTelefone());
			pstm.setString(4, contato.getEmail());
			pstm.setDate(5, java.sql.Date.valueOf(contato.getDataCadastro()));
			
			//Qual o codigo id do registro que deseja atualiazar?
			pstm.setInt(6, contato.getCodigo());
			
			//Executar a query
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			    try {
			        if (pstm != null) {
			            pstm.close();
			        }

			        if (conn != null) {
			            conn.close();
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
	}


	public void deleteByCodigo(int codigo) {
		
		String sql = "delete from contatos where codigo = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, codigo);
			
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
		
	
	
	public List<Contatos> getContatos() {
		
		String sql = "select * from contatos";
		
		List<Contatos> contatos	= new ArrayList<Contatos>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***Select***
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contatos contato = new Contatos();
				
				//Recuperar o id
				contato.setCodigo(rset.getInt("codigo"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar o telefone
				contato.setTelefone(rset.getString("telefone"));
				//Recuperar o email
				contato.setEmail(rset.getString("email"));
				//recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("datacadastro").toLocalDate());
				
				
				contatos.add(contato);
			}}
			catch (Exception e) {
				e.printStackTrace();
			}finally {
				try { 
					if(rset!=null) {
						rset.close();
					}
				
					if(pstm!=null) {
						pstm.close();
					}
				
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			
			}
			
			return contatos;
		}
	
	
	
}	






