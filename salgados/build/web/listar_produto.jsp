<%-- 
    Document   : listar_produto
    Created on : 26/11/2018, 17:52:44
    Author     : 256358
--%>

<%@page import="modelo.Classificacao"%>
<%@page import="modelo.ClassificacaoDAO"%>
<%@page import="modelo.ProdutoDAO"%>
<%@page import="modelo.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript">
            function excluir(id,nome){
                if(confirm("Tem certeza que deseja excluir o perfil: "+nome+"?")){
                    window.open("excluir_produto.do?id="+id,"_parent");
                }
            }
        </script>
        <title>Listar Produtos</title>
    </head>
    <body>
        
         <%--@include file="produto.jsp" --%>
        <h1>Lista de Produtos (<a href="form_inserir_produto.jsp"><img src="imagens/novo.png"/></a>)</h1>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>NOME</td>
                <td>SABOR</td>
                <td>PREÇO</td>
                <td>INGREDIENTES</td>
                <td>DESCRIÇÃO</td>
                <td>IMAGEM</td>
                <td>PESO EM GRAMAS</td>
                <td>STATUS</td>
                <td>CLASSIFICAÇÃO</td>
                <td>OPÇÕES</td>
            </tr>
            <%
            ArrayList<Produto> lista = new ArrayList<Produto>();
            ProdutoDAO pDAO = new ProdutoDAO();
            try{
               lista = pDAO.listar();
            }catch(Exception e){
                out.print("Erro:"+e);
            }
            
            for(Produto p:lista){
            %>
            <tr>
                <td><%=p.getId() %></td>
                <td><%=p.getNome() %></td>
                <td><%=p.getSabor() %></td>
                <td><%=p.getPreco() %></td>
                <td><%=p.getIngredientes() %></td>
                <td><%=p.getDescricao() %></td>
                <td><%=p.getImg() %></td>
                <td><%=p.getPeso() %></td>
                <td><% 
                        if(p.getStatus() == 1){
                            %> Ativo <% 
                        } else{
                        %> Desativado <%
                        }
                    %>
                </td>
                <td><% ClassificacaoDAO cdao = new ClassificacaoDAO();
                        Classificacao cc = new Classificacao();
                        cc = cdao.carregarPorId(p.getClassificacao().getId()); %>
                        <%=cc.getTipo() %></td>
                <td><a href="form_alterar_produto.jsp?id=<%=p.getId() %>"><img src="imagens/alterar.png"/></a> <a href="#" onclick="excluir(<%=p.getId() %>,'<%=p.getNome() %>')"><img src="imagens/excluir.png"/></a>  </td>
            </tr>            
            <%
            }
            %>
    </body>
</html>
