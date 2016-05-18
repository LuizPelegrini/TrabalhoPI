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
}
