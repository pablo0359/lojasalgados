/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author sajsm
 */
public class VendaDAO extends DataSource{
    
//Criar o método Inserir
public void inserir(Venda v) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar uma sql para ser utilizada com o PreparedStatement
    String sql = "INSERT INTO venda (data,dataentrega,total,status,chave,datacancelamento,cliente_id,funcionario_id) VALUES (now(),?,?,?,?,?,?,?)";
//Criar o metodo PreparedStatement que irá conectar com o banco de dados para realizar a inserção do sql.
    PreparedStatement pstm = conn.prepareStatement(sql);
//Inserir os parâmetros na variável do pstm
 //Inserido a (Date) para converter o date sql para o date do java.util.
    pstm.setDate(1, (Date) v.getData());
    pstm.setDate(2, (Date) v.getDataentrega());
    pstm.setDouble(3, v.getTotal());
    pstm.setInt(4, v.getStatus());
    pstm.setLong(5, v.getChave());
    pstm.setDate(6, (Date) v.getDatacancelamento());
    pstm.setInt(7, v.getCliente().getId());
    pstm.setInt(8, v.getFuncionario().getId());
//Executar as informações no banco de dados
    pstm.execute();
//Desconectar do banco de dados.
    this.desconectar();
    }
 
 //Criar método para excluir um venda, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM venda WHERE id=?";
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

//Criar um método para listar os vendas
 public ArrayList<Venda> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de venda e gerar um novo objeto chamado lista, que irá receber todos os vendas e incluir na variável lista.
    ArrayList<Venda> lista = new ArrayList<Venda>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM venda";
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
     Venda v = new Venda();
     v.setId(rs.getInt("id"));
     v.setData(rs.getDate("data"));
     v.setDataentrega(rs.getDate("dataentrega"));
     v.setTotal(rs.getDouble("total"));
     v.setStatus(rs.getInt("status"));
     v.setChave(rs.getLong("chave"));
     v.setDatacancelamento(rs.getDate("datacancelamento"));
     ClienteDAO clDAO = new ClienteDAO();
     v.setCliente(clDAO.carregarPorId(rs.getInt("cliente_id")));
     FuncionarioDAO cDAO = new FuncionarioDAO();
     v.setFuncionario(cDAO.carregarPorId(rs.getInt("funcionario_id")));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(v);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os vendas existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

//Criando o metodo para verificar o login se existe.
public boolean loginExiste(int id, String login) throws Exception{
//Necessário conectar com o banco para consultar o login dos usuários
    this.conectar();
//Criar a variável sql para prepará-la
    String sql = "SELECT * FROM venda WHERE cpf=?";
//incluindo na variável pstm o sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluindo o parâmetro da sql acima.
    pstm.setString(1, login);
//Obtendo a tabela pelo pstm e incluindo dentro da variável rs
    ResultSet rs = pstm.executeQuery();
//Criando um "IF" para verificar se o login existe
    if(rs.next()){
/*        {
       return true;
    }
*/
     if(rs.getInt("id") == id){
        return false;
    } else {
        return true;
        }
    }
//Desconectando do banco de dadoa
    this.desconectar();
/*Como eu quero que somente se for "falso" que o login deverá 
ser realizado, então deverá retornar "false"*/
    return false;
    }

/*Criar o método carregarPorId para que ele liste tudo do venda
ao receber o "id" do determinado venda*/
public Venda carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de venda para obter todas as informações
do venda após encontrá-lo no laco do "Se(if)"*/
    Venda v = new Venda();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM venda WHERE id=?";
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
     v.setId(rs.getInt("id"));
     v.setData(rs.getDate("data"));
     v.setDataentrega(rs.getDate("dataentrega"));
     v.setTotal(rs.getDouble("total"));
     v.setStatus(rs.getInt("status"));
     v.setChave(rs.getLong("chave"));
     v.setDatacancelamento(rs.getDate("datacancelamento"));
     ClienteDAO clDAO = new ClienteDAO();
     v.setCliente(clDAO.carregarPorId(rs.getInt("cliente_id")));
     FuncionarioDAO cDAO = new FuncionarioDAO();
     v.setFuncionario(cDAO.carregarPorId(rs.getInt("funcionario_id")));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do venda pelo "id" obtido. 
    ;) legal né!                        */
    return v;
    }

}
