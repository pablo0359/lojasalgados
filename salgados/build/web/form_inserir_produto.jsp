<%-- 
    Document   : form_inserir_produto
    Created on : 26/11/2018, 17:39:09
    Author     : 256358
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ClassificacaoDAO"%>
<%@page import="modelo.Classificacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir produto</title>
    </head>
    <body>
        <%--@include file="produto.jsp" --%>
        <h1>Novo Produto</h1>
        <form action="inserir_produto.do" method="post">
            
            Nome:    <input type="text" name="nome"  size="60" required/><br/>
            Sabor:<input type="text" name="sabor"  size="60" required/><br/>
            Preco:<input type="text" name="preco" size="60"  required/>Ops.: separador de centavos é um ponto<br/>
           Ingredientes:   <input type="text" name="ingredientes" size="120" required/><br/>
            Descricao:  <input type="text" name="descricao" size="120"/><br/>
            Img:     <input type="text" name="img" size="60" required/><br/>
            Peso:   <input type="text" name="peso" size="60" required/>Ops.: Em gramas<br/>
            Status: <select name="status"  required>
                    <option value="1">Ativo</option>
                    <option value="2">Desativo</option>
                    </select> <br/>
            Classificação: <select name="classificacao"  required>
            <%
            ArrayList<Classificacao> lista = new ArrayList<Classificacao>();
            ClassificacaoDAO cDAO = new ClassificacaoDAO();
            try{
               lista = cDAO.listar();
            }catch(Exception e){
                out.print("Erro:"+e);
            }
            for(Classificacao c:lista){
                %>
                <option value="<%=c.getId() %>"><%=c.getTipo() %></option>
              <% } %>
              </select>
            <input type="submit" value="Salvar"/>
        </form>
    </body>
</html>
