package com.example.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.ContatoBean;
import com.example.model.ContatoDao;


@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dateString = request.getParameter("data");
		
		try{
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			ContatoBean contato = new ContatoBean();
			contato.setNome(nome);
			contato.setEmail(email);
			contato.setEndereco(endereco);
			contato.setDataNascimento(calendar);
			
			ContatoDao contatoDao = new ContatoDao();
			contatoDao.adicionaContato(contato);
			
			out.println("Contato inserido com sucesso!");
		}catch(ParseException e){
			//TODO Thrown by parse()
			throw new ServletException(e);
		}catch(SQLException e){
			//TODO Thrown by adicionaContato()
			throw new ServletException(e);
		}
	}

}
