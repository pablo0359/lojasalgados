/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataSource {
    
//Criando uma variável "conn" para realizar a conexão.
    Connection conn;
    
//Criar o método Conectar para realizar a conexão.
public void conectar () throws Exception{
    String usuario, senha, url;
    usuario = "root";
    senha = "";
    url = "jdbc:mysql://localhost/salgados";
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(url,usuario,senha);
        }

//Cria o método para realizar a desconecção do banco de dados.
public void desconectar() throws Exception{
    if(!conn.isClosed()){
        conn.close();
        }
    }
}
