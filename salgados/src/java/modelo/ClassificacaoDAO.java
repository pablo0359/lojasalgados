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
public class ClassificacaoDAO extends DataSource{
    
//Método inserir
public void inserir(Classificacao c) throws Exception{
//Criar a conexão com o banco de dados.
    this.conectar();
//Criar a sql
    String sql = "INSERT INTO classificacao(tipo,status) VALUES(?,?)";
//Criar o PreparedStatement para obter a variável sql acima.
    PreparedStatement pstm = conn.prepareStatement(sql);
//Inserir os parâmetros para a variável pstm inserir na string sql
    pstm.setString(1, c.getTipo());
    pstm.setInt(2, c.getStatus());
//executar o pstm para inserir no banco de dados as informações.
    pstm.execute();
//Realizar a desconexão do banco de dados.
    this.desconectar();
    }

 //Método para realizar a alteração do classificacao.
public void alterar(Classificacao c) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE classificacao SET tipo=?,status=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, c.getTipo());
    pstm.setInt(2, c.getStatus());
    pstm.setInt(3, c.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
 
 //Criar método para excluir um classificacao, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM classificacao WHERE id=?";
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

//Criar um método para listar os classificacaos
 public ArrayList<Classificacao> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de classificacao e gerar um novo objeto chamado lista, que irá receber todos os classificacaos e incluir na variável lista.
    ArrayList<Classificacao> lista = new ArrayList<Classificacao>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM classificacao";
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
     Classificacao c = new Classificacao();
     c.setId(rs.getInt("id"));
     c.setTipo(rs.getString("tipo"));
     c.setStatus(rs.getInt("status"));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(c);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os classificacaos existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

/*Criar o método carregarPorId para que ele liste tudo do classificacao
ao receber o "id" do determinado classificacao*/
public Classificacao carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de classificacao para obter todas as informações
do classificacao após encontrá-lo no laco do "Se(if)"*/
    Classificacao c = new Classificacao();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM classificacao WHERE id=?";
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
     c.setId(rs.getInt("id"));
     c.setTipo(rs.getString("tipo"));
     c.setStatus(rs.getInt("status"));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do classificacao pelo "id" obtido. 
    ;) legal né!                        */
    return c;
    }

}
