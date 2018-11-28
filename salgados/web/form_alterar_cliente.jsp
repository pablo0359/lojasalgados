<%-- 
    Document   : form_alterar_cliente
    Created on : 29/10/2018, 07:23:21
    Author     : sajsm
--%>

<%@page import="modelo.ClienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    Cliente c = new Cliente();
    try{
       int id = Integer.parseInt(request.getParameter("id"));
       ClienteDAO cDAO = new ClienteDAO();
       c = cDAO.carregarPorId(id);
       
       
       ClienteDAO pDAO = new ClienteDAO();
       clientes = pDAO.listar();
    }catch(Exception e){
        out.print("Erro:"+e);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Cliente</title>
    </head>
    <body>
        <h1>Editando Cliente</h1>
        <form action="alterar_cliente.do" method="post">
            ID:<%=c.getId() %><br/>
            <input type="hidden" value="<%=c.getId() %>" name="id"/>
            Nome:    <input type="text" name="nome" value="<%=c.getNome() %>" size="60" /><br/>
            Telefone:<input type="text" name="tel" value="<%=c.getTelefone() %>" size="60" /><br/>
            email:   <input type="text" name="email" value="<%=c.getEmail() %>" size="60"/><br/>
            CPF:     <input type="text" name="cpf" value="<%=c.getCpf() %>" size="60" /><br/>
            RG:      <input type="text" name="rg" value="<%=c.getRG() %>" size="60" /><br/>
            Senha:   <input type="password" name="senha" size="60" /><br/>
             <br/>
            <input type="submit" value="Salvar"/>
        </form>
    </body>
</html>
