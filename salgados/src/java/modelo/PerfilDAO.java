/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PerfilDAO extends DataSource{

//Criar o método inserir um perfil.
public void inserir(Perfil p) throws Exception{

//Realizar a conexão com o banco de dados
    this.conectar();
//Criar uma String SQL para inserir no PreparedStatement
    String sql = "INSERT INTO perfil (nome,descricao,status) VALUES (?,?,?)";
//Usar o PreparedStatement para inserir os parêmetros.
    PreparedStatement pstm = conn.prepareStatement(sql);
//Inserir os parâmetros
 //Observação: Set (insiro) o tipo do atributo (variável.get(Pego) o atributo)
    pstm.setString(1, p.getNome());
    pstm.setString(2, p.getDescricao());
    pstm.setInt(3, p.getStatus());
/*Depois de atribuido os valores para cada campo na variável 
basta executar o comando de inserção das informações no banco de dados*/
    pstm.execute();
//Necessário desconectar do banco para liberar a memória que está sendo utilizada.
 //Obs: this significa neste método. ;)
    this.desconectar();
    }

public void excluir(int id) throws Exception{
//Realizar a conexão
    this.conectar();
//Criar a variável para obter o comando sql.
    String sql = "DELETE FROM perfil WHERE id=?";
//Comando para preparar receber a variável e incluir os parâmetros.
    PreparedStatement pstm = conn.prepareStatement(sql);
//Setar os parâmetros
    pstm.setInt(1, id);
//Executar o camando da variável para inserir no banco de dados o sql com os parâmetros.
    pstm.execute();
//Comando para desconectar do banco de dados
    this.desconectar();
    }

public ArrayList<Perfil> listar() throws Exception{
//Conectar com o banco de dados para buscar a lista de perfis
    this.conectar();
//Instanciar a lista para retornar com a Classe ArrayList<>
    ArrayList<Perfil> lista = new ArrayList<Perfil>();
//Após instanciado o arraylist deverá ser criado o sql conforme abaixo.
    String sql = "SELECT * FROM perfil";
//variável para trabalhar com os parâmetro que serão inseridos na variável.
    PreparedStatement pstm = conn.prepareStatement(sql);
/*Como precisamos de uma lista o retorno sempre será uma tabela
dessa forma precisamos obter essa tabela com ResultSet*/
    ResultSet rs = pstm.executeQuery();
//Com a tabela em mãos iremos fazer um laço para para obter as colunas
    while(rs.next()){
//Iremos instanciar o Perfil para pegar as informações abaixo.
      Perfil p = new Perfil();
//Estamos pegando os campos e inserindo na variável p.
      p.setId(rs.getInt("id"));
      p.setNome(rs.getString("nome"));
      p.setStatus(rs.getInt("status"));
      p.setDescricao(rs.getString("descricao"));
    }       
//Após montar a lista iremos desconectar do banco.
    this.desconectar();
//Como uma lista retorna um objeto, ele foi nomeado de lista
    return lista;
  }

    /*Criar o método carregarPorId para que ele liste tudo do perfil
ao receber o "id" do determinado perfil*/
public Perfil carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de perfil para obter todas as informações
do perfil após encontrá-lo no laco do "Se(if)"*/
    Perfil p = new Perfil();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM perfil WHERE id=?";
//criando a variável pstm para receber os comando e incluir no banco de dados.
    PreparedStatement pstm = conn.prepareStatement(sql);
//incluir na variável pstm o parâmetro solicitado no sql acima.
    pstm.setInt(1, id);
/*Criado a variável rs de ResultSet para receber a tabela que foi 
retornada do banco ao incluir o sql que estava no pstm*/
 //pstm.executeQuery() serve para executar o comando sql e retornar uma tabela.
    ResultSet rs = pstm.executeQuery();
//Com a tabela dentro de rs iremos agora varrer a tabela com o while.
 /*Se possui um registro a variável irá obter a informação
 e incluir dentro de "rs" vale ressaltar que o próximo serve exatamente
para não pegar o cabeçalho da tabela que contem as colunas do banco de dados*/
    if(rs.next()) 
    {
     p.setId(rs.getInt("id"));
      p.setNome(rs.getString("nome"));
      p.setStatus(rs.getInt("status"));
      p.setDescricao(rs.getString("descricao"));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do perfil pelo "id" obtido. 
    ;) legal né!                        */
    return p;
    }

public void alterar(Perfil p) throws Exception{
//conectar com o banco de dados
    this.conectar();
//Criar a variáel sql para receber o comando da de alteração
    String sql = "UPDATE perfil SET nome=?,descricao=?,status=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, p.getNome());
    pstm.setString(2, p.getDescricao());
    pstm.setInt(3, p.getStatus());
    pstm.setInt(4, p.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
}
