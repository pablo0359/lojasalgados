<%-- 
    Document   : form_inserir_cliente
    Created on : 29/10/2018, 06:17:35
    Author     : sajsm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Cliente</title>
    </head>
    <body>
        <h1>Novo Cliente</h1>
        <form action="inserir_cliente.do" method="post">
      
            
            Nome:    <input type="text" name="nome"  size="60" /><br/>
            Telefone:<input type="text" name="tel"  size="60" /><br/>
            email:   <input type="text" name="email" size="60"/><br/>
            CPF:     <input type="text" name="cpf" size="60" /><br/>
            RG:      <input type="text" name="rg" size="60" /><br/>
            Senha:   <input type="password" name="senha" size="60" /><br/>
             <br/>
            <input type="submit" value="Salvar"/>
        </form>
    </body>
</html>
