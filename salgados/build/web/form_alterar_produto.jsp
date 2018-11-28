<%-- 
    Document   : Form_alterar_produto
    Created on : 26/11/2018, 17:12:54
    Author     : 256358
--%>

<%@page import="modelo.ClassificacaoDAO"%>
<%@page import="modelo.Classificacao"%>
<%@page import="modelo.ProdutoDAO"%>
<%@page import="modelo.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    Produto p = new Produto();
    try{
       int id = Integer.parseInt(request.getParameter("id"));
       ProdutoDAO pDAO = new ProdutoDAO();
       p = pDAO.carregarPorId(id);
       
       
    }catch(Exception e){
        out.print("Erro:"+e);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editando Produtos</h1>
        <form action="alterar_produto.do" method="post">
            ID:<%=p.getId() %><br/>
            <input type="hidden" value="<%=p.getId() %>" name="id"/>
            Nome:    <input value="<%=p.getNome() %>" type="text" name="nome"  size="60" required/><br/>
            Sabor:<input value="<%=p.getSabor() %>" type="text" name="sabor"  size="60" required/><br/>
            Preco:<input value="<%=p.getPreco() %>" type="text" name="preco" size="60"  required/>Ops.: separador de centavos é um ponto<br/>
           Ingredientes:   <input value="<%=p.getIngredientes() %>" type="text" name="ingredientes" size="120" required/><br/>
            Descricao:  <input value="<%=p.getDescricao() %>" type="text" name="descricao" size="120"/><br/>
            Img:     <input value="<%=p.getImg() %>" type="text" name="img" size="60" required/><br/>
            Peso:   <input value="<%=p.getPeso() %>" type="text" name="peso" size="60" required/>Ops.: Em gramas<br/>
            Status: <select name="status"  >
                <option value="1" <% if(p.getStatus() == 1){ %> selected   <% } %> >Ativo</option>
                    <option value="2"  <% if(p.getStatus() != 1){ %> selected   <% } %> >Desativo</option>
                    </select> <br/>
            Classificação: <select name="classificacao"  >
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
                <option value="<%=c.getId() %>" <% if(p.getClassificacao().getId() == c.getId()){ %> selected   <% } %>><%=c.getTipo() %></option>
              <% } %>
              </select><br/>
            <input type="submit" value="Salvar"/>
        </form>
        
        
        
    </body>
</html>
