<%-- 
    Document   : form_listar_funcionario
    Created on : 29/10/2018, 05:21:37
    Author     : sajsm
--%>

<%@page import="modelo.FuncionarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- Scrpt para confirmar a exclusão do funcionario --%>
        <script type="text/javascript">
            function excluir(id,nome){
                if(confirm("Tem certeza que deseja excluir o Funcionario "+nome+"?")){
                    window.open("excluir_funcionario.do?id="+id,"_parent");
                }
            }
        </script>
        <title>Lista de Funcionarios</title>
    </head>
    <body>
        <h1>Lista de Funcionarios (<a href="form_inserir_funcionario.jsp">Adicionar Funcionario</a>)</h1>
        <table border="1">
            <tr>
                <td>Nome</td>
                <td>Telefone</td>
                <td>CPF</td>
                <td>RG</td>
                <td>Email</td>
                <td>OPÇÕES</td>
            </tr>
            <%
            ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
            FuncionarioDAO cDAO = new FuncionarioDAO();
            try{
               lista = cDAO.listar();
            }catch(Exception e){
                out.print("Erro:"+e);
            }
            
            for(Funcionario c:lista){
            %>
            <tr>
                <td><%=c.getNome()%></td>
                <td><%=c.getTelefone()%></td>
                <td><%=c.getCpf() %></td>
                <td><%=c.getRg() %></td>
                <td><%=c.getEmail() %></td>
                <td><a href="form_alterar_funcionario.jsp?id=<%=c.getId() %>">Alterar</a> <a href="#" onclick="excluir(<%=c.getId() %>,'<%=c.getNome() %>')">Excluir</a> </td>
            </tr> 
            <%
            }
            %>
        </table>
    </body>
</html>
