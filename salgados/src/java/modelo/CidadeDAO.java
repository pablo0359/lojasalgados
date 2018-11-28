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
public class CidadeDAO extends DataSource{

//Criar o método inserir
public void inserir(Cidade c) throws Exception{
//Conectar com o banco de dados
    this.conectar();
//Criar a variável que irá receber a string para inserir no banco de dados.
    String sql = "INSERT INTO cidade (cidade,taxa) VALUES (?,?)";
//Usar a Classe PreparedStatement para trabalhar com a variável sql.
    PreparedStatement pstm = conn.prepareStatement(sql);
//incluir os parâmetros na variável pstm
    pstm.setString(1, c.getCidade());
    pstm.setDouble(2, c.getTaxa());
    //pstm.setInt(3, c.getEndereco().getId());
//Executar as informações da variável pstm
    pstm.execute();
//Desconectar do banco de dados para liberar memória.
    this.desconectar();
    }

public void alterar(Cidade c) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE cidade SET cidade=?,taxa=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, c.getCidade());
    pstm.setDouble(2, c.getTaxa());
    pstm.setInt(3, c.getId());
    //pstm.setInt(4, c.getEndereco().getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
 
 //Criar método para excluir um cidade, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM cidade WHERE id=?";
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

//Criar um método para listar os cidades
 public ArrayList<Cidade> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de cidade e gerar um novo objeto chamado lista, que irá receber todos os cidades e incluir na variável lista.
    ArrayList<Cidade> lista = new ArrayList<Cidade>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM cidade";
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
     Cidade c = new Cidade();
     c.setId(rs.getInt("id"));
     c.setCidade(rs.getString("cidade"));
     c.setTaxa(rs.getFloat("taxa"));
     //EnderecoDAO eDAO = new EnderecoDAO();
     //c.setEndereco(eDAO.carregarPorId(rs.getInt("id")));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(c);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os cidades existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

/*Criar o método carregarPorId para que ele liste tudo do cidade
ao receber o "id" do determinado cidade*/
public Cidade carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de cidade para obter todas as informações
do cidade após encontrá-lo no laco do "Se(if)"*/
    Cidade c = new Cidade();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM cidade WHERE id=?";
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
     c.setCidade(rs.getString("cidade"));
     c.setTaxa(rs.getFloat("taxa"));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do cidade pelo "id" obtido. 
    ;) legal né!                        */
    return c;
    }

}
