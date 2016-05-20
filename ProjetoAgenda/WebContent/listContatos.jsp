<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import=	"com.example.model.ContatoDao,
    		 com.example.model.ContatoBean,
    		 java.util.*,
    		 java.text.SimpleDateFormat" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Contatos</title>
	</head>
	<body>
		<table border="2px">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Endereco</th>
				<th>Data Nascimento</th>
			</tr>
		</thead>
		<tbody>
		<%
			ContatoDao dao = new ContatoDao();
			List<ContatoBean> contatos = dao.listContatos();
			
			for (ContatoBean contato : contatos){
		%>
			<tr>
				<td><%=contato.getNome()%></td>
				<td><%=contato.getEmail()%></td>
				<td><%=contato.getEndereco()%></td>
				<%
				Date date = new Date(contato.getDataNascimento().getTimeInMillis());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				%>
				<td><%=dateFormat.format(date)%></td>
			</tr>
		<%
			} 
		%>
		</tbody>
		</table>
	</body>
</html>

<%-- a diretiva <%@ page %> serve para configurarmos a pagina --%>
<%-- a diretiva <%= expressao %> serve para printar "expressao" na tela --%>