<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Usuario usuario = (Usuario)request.getAttribute("usuario"); %>
	<form action="usuariocontroller" method="post">
		ID:<input type="number" name="id" value="<%=usuario.getId()%>"/></br>
		Nome:<input type="text" name="nome" value="<%=usuario.getNome()%>"/></br>
		Login:<input type="text" name="login" value="<%=usuario.getLogin()%>"/></br>
		Senha:<input type="text" name="senha" value="<%=usuario.getSenha()%>"/></br></br>
		<input type="submit" value="Salvar"/>
 	</form>
</body>
</html>