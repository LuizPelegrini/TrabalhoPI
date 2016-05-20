package com.example.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDao {
	Connection connection=null;
	
	public ContatoDao(){
		connection = new ConnectionFactory().getConnection();
	}
	
	public void adicionaContato(ContatoBean contato) throws SQLException{
		//Query
		String sql =	"insert into contatos"+
						"(nome, email, endereco, dataNascimento)"+
						"values(?,?,?,?)";
		// Statement setup
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, contato.getNome());
		stmt.setString(2, contato.getEmail());
		stmt.setString(3, contato.getEmail());
		Date date = new Date(contato.getDataNascimento().getTimeInMillis());
		stmt.setDate(4, date);
		
		stmt.execute();
		
		System.out.println("Gravado!");
		
		// Closing statement
		stmt.close();
	}
	
	public List<ContatoBean> listContatos() throws SQLException{
		List<ContatoBean> contatos = new ArrayList<ContatoBean>();
		
		String sql =	"select * from contatos";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			ContatoBean contato = new ContatoBean();
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(rs.getDate("dataNascimento"));
			
			contato.setDataNascimento(calendar);
			
			contatos.add(contato);
		}
		
		rs.close();
		stmt.close();
		
		return contatos;
	}
}
