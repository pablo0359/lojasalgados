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
public class EnderecoDAO extends DataSource{
    
//Criar o método Inserir
public void inserir(Endereco e) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar uma sql para ser utilizada com o PreparedStatement
    String sql = "INSERT INTO endereco (logradouro,uf,cep,pais,cliente_id,cidade_id) VALUES (?,?,?,?,?,?)";
//Criar o metodo PreparedStatement que irá conectar com o banco de dados para realizar a inserção do sql.
    PreparedStatement pstm = conn.prepareStatement(sql);
//Inserir os parâmetros na variável do pstm
    pstm.setString(1, e.getLogradouro());
    pstm.setString(2, e.getUf());
    pstm.setInt(3, e.getCep());
    pstm.setString(4, e.getPais());
    pstm.setInt(5, e.getCliente().getId());
    pstm.setInt(6, e.getCidade().getId());
//Executar as informações no banco de dados
    pstm.execute();
//Desconectar do banco de dados.
    this.desconectar();
    }
 
 //Método para realizar a alteração do endereco.
public void alterar(Endereco e) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE endereco SET logradouro=?,uf=?,cep=?,pais=?,cliente_id=?,cidade_id=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, e.getLogradouro());
    pstm.setString(2, e.getUf());
    pstm.setInt(3, e.getCep());
    pstm.setString(4, e.getPais());
    pstm.setInt(5, e.getCliente().getId());
    pstm.setInt(6, e.getCidade().getId());
    pstm.setInt(8, e.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
 
 //Criar método para excluir um endereco, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM endereco WHERE id=?";
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

//Criar um método para listar os enderecos
 public ArrayList<Endereco> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de endereco e gerar um novo objeto chamado lista, que irá receber todos os enderecos e incluir na variável lista.
    ArrayList<Endereco> lista = new ArrayList<Endereco>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM endereco";
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
     Endereco e = new Endereco();
     e.setId(rs.getInt("id"));
     e.setLogradouro(rs.getString("logradouro"));
     e.setUf(rs.getString("uf"));
     e.setCep(rs.getInt("cep"));
     e.setPais(rs.getString("pais"));
     ClienteDAO clDAO = new ClienteDAO();
     e.setCliente(clDAO.carregarPorId(rs.getInt("cliente_id")));
     CidadeDAO cDAO = new CidadeDAO();
     e.setCidade(cDAO.carregarPorId(rs.getInt("cidade_id")));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(e);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os enderecos existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

/*Criar o método carregarPorId para que ele liste tudo do endereco
ao receber o "id" do determinado endereco*/
public Endereco carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de endereco para obter todas as informações
do endereco após encontrá-lo no laco do "Se(if)"*/
    Endereco e = new Endereco();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM endereco WHERE id=?";
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
     e.setId(rs.getInt("id"));
     e.setLogradouro(rs.getString("logradouro"));
     e.setUf(rs.getString("uf"));
     e.setCep(rs.getInt("cep"));
     e.setPais(rs.getString("pais"));
     ClienteDAO clDAO = new ClienteDAO();
     e.setCliente(clDAO.carregarPorId(rs.getInt("cliente_id")));
     CidadeDAO cDAO = new CidadeDAO();
     e.setCidade(cDAO.carregarPorId(rs.getInt("cidade_id")));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do endereco pelo "id" obtido. 
    ;) legal né!                        */
    return e;
    }

}
