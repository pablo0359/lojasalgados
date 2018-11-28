/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author sajsm
 */
public class MenuDAO extends DataSource{
    
    public void inserir(Menu m) throws Exception{
//Realizar a conexão
    this.conectar();
//inserir a variável sql
    String sql = "INSERT INTO menu (titulo,link,icone,status,) VALUES (?,?,?,?)";
//Criar o PreparedStatement para tratar o sql.
    PreparedStatement pstm = conn.prepareStatement(sql);
 //Inserir os parâmetros para cada coluna da tabela acima.
    pstm.setString(1, m.getTitulo());
    pstm.setString(2, m.getLink());
    pstm.setString(3, m.getIcone());
    pstm.setInt(4, m.getStatus());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
     }

    /*Criar o método carregarPorId para que ele liste tudo do menu
ao receber o "id" do determinado menu*/
public Menu carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de menu para obter todas as informações
do menu após encontrá-lo no laco do "Se(if)"*/
    Menu m = new Menu();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM menu WHERE id=?";
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
     m.setId(rs.getInt("id"));
     m.setTitulo(rs.getString("titulo"));
     m.setLink(rs.getString("link"));
     m.setIcone(rs.getString("icone"));
     m.setStatus(rs.getInt("status"));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do menu pelo "id" obtido. 
    ;) legal né!                        */
    return m;
    }
 
//Método para realizar a alteração do menu.
public void alterar(Menu m) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE menu SET titulo=?,link=?,icone=?,status=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, m.getTitulo());
    pstm.setString(2, m.getLink());
    pstm.setString(3, m.getIcone());
    pstm.setInt(4, m.getStatus());
    pstm.setInt(5, m.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
 
 //Criar método para excluir um menu, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM menu WHERE id=?";
/*Necessário criar uma variável para receber o sql digitado e inserir na variábel pstm.
Para isso iremos buscar o prepareStatement na conexão*/
    PreparedStatement pstm = conn.prepareStatement(sql);
//Agora iremos incluir os valores necessários na variável conforme o parâmetro desejado acima. 
    pstm.setInt(1, id);
//Executar o a variável pstm para inserir a informação no banco de dados.
    pstm.execute();
//Necessário liberar a memória para não sobrecarregar o computador.
    this.desconectar();
//Fim da exclusão
    }

//Criar um método para listar os menus
 public ArrayList<Menu> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de menu e gerar um novo objeto chamado lista, que irá receber todos os menus e incluir na variável lista.
    ArrayList<Menu> lista = new ArrayList<Menu>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM menu";
//criando a variável pstm para receber os comando e incluir no banco de dados.
    PreparedStatement pstm = conn.prepareStatement(sql);
/*Criado a variável rs de ResultSet para receber a tabela que foi 
retornada do banco ao incluir o sql que estava no pstm*/
 //pstm.executeQuery() serve para executar o comando sql e retornar uma tabela.
    ResultSet rs = pstm.executeQuery();
//Com a tabela dentro de rs iremos agora varrer a tabela com o while.
 /*Enquanto tiver um proximo registro a variável irá obter a informação
 e incluir dentro de "rs" vale ressaltar que o próximo serve exatamente
para não pegar o cabeçalho da tabela que contem as colunas do banco de dados*/
    while(rs.next()){
     Menu f = new Menu();
     f.setId(rs.getInt("id"));
     f.setTitulo(rs.getString("titulo"));
     f.setLink(rs.getString("link"));
     f.setIcone(rs.getString("icone"));
     f.setStatus(rs.getInt("status"));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(f);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os menus existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

}
