/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

//Classe ClienteDAO extendendo a conexão com o banco de dados (DataSource).

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO extends DataSource{

//método Inserir Cliente -- Empurrando o tratamento do erro com o throws
 public void inserir (Cliente c) throws Exception{
//neste método peque a conexão com o banco de dados.
     this.conectar();
//Criado uma variável "sql" para guardar o comando de inserção do cliente conforme abaixo.
     String sql = "INSERT INTO cliente (nome,telefone,senha,email,cpf,rg) VALUES (?,?,?,?,?,?)";
//o atributo pstm criado para o PreparedStatement irá conectar e preparar para inserir o código no banco de dados.
     PreparedStatement pstm = conn.prepareStatement(sql);
//Iremos inserir cada atributo nas colunas do banco de dados acima com o pstm.
    pstm.setString(1, c.getNome());
    pstm.setString(2, c.getTelefone());
    pstm.setString(3, c.getSenha());
    pstm.setString(4, c.getEmail());
    pstm.setString(5, c.getCpf());
    pstm.setString(6, c.getRG());
//Executar o pstm com as infromações das variáveis que foram inseridas no pstm
    pstm.execute();
/*Agora iremos fechar a conexão do banco de dados para liberar espaço na memoria,
quando for necessário iremos realizar nova abertura da conexão*/
this.desconectar();
    }
 
 //Método para realizar a alteração do cliente.
public void alterar(Cliente c) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE cliente SET nome=?,telefone=?,senha=?,email=?,cpf=?,rg=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, c.getNome());
    pstm.setString(2, c.getTelefone());
    pstm.setString(3, c.getSenha());
    pstm.setString(4, c.getEmail());
    pstm.setString(5, c.getCpf());
    pstm.setString(6, c.getRG());
    pstm.setInt(7, c.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
 
 //Criar método para excluir um cliente, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM cliente WHERE id=?";
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

//Criar um método para listar os clientes
 public ArrayList<Cliente> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de cliente e gerar um novo objeto chamado lista, que irá receber todos os clientes e incluir na variável lista.
    ArrayList<Cliente> lista = new ArrayList<Cliente>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM cliente";
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
     Cliente c = new Cliente();
     c.setId(rs.getInt("id"));
     c.setNome(rs.getString("nome"));
     c.setTelefone(rs.getString("telefone"));
     c.setSenha(rs.getString("senha"));
     c.setEmail(rs.getString("email"));
     c.setCpf(rs.getString("cpf"));
     c.setRG(rs.getString("rg"));
     //EnderecoDAO eDAO = new EnderecoDAO();
     //c.setEndereco(eDAO.carregarPorId(rs.getInt("endereco_id")));
     //VendaDAO vDAO = new VendaDAO();
     //c.setVenda(vDAO.carregarPorId(rs.getInt("")));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(c);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os clientes existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

//Criando o metodo para verificar o login se existe.
public boolean loginExiste(int id, String login) throws Exception{
//Necessário conectar com o banco para consultar o login dos usuários
    this.conectar();
//Criar a variável sql para prepará-la
    String sql = "SELECT * FROM cliente WHERE cpf=?";
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
 
 //Criar o método logar
public Cliente logar(String login, String senha) throws Exception{
//conectar com o banco de dado
    this.conectar();
//Criando um objeto cliente para obter o login e a senha
    Cliente c = new Cliente();
//Criar a veriável sql para inserir a string no banco de dados
    String sql = "SELECT * FROM cliente WHERE login=?";
//Criar a variável pstm para obter o parâmetro para preenchimento
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir o pstm para inserir o parâmetro dentro da string login
    pstm.setString(1, login);
//Criar a variável rs para obter o tabela 
    ResultSet rs = pstm.executeQuery();
//Iremos realizar um se para identificar se há registro na tabela
    if(rs.next()){ //Se tiver um proximo registro passe para o passo seguinte.
//Se a senha for igual a senha então obtenha as informações da tabela
     if(senha.equals(rs.getString("senha"))){
//se tudo for verdadeiro, iremos então pegar as informações da tabela
      c.setId(rs.getInt("id"));
     c.setNome(rs.getString("nome"));
     c.setTelefone(rs.getString("telefone"));
     c.setSenha(rs.getString("senha"));
     c.setEmail(rs.getString("email"));
     c.setCpf(rs.getString("cpf"));
     c.setRG(rs.getString("rg"));
     }
    }
//Realizar a desconexão do banco de dados
    this.desconectar();
//Retornar um objeto "c" com todas as informações dos cliente para conferir o login e senha.
    return c;
    }

/*Criar o método carregarPorId para que ele liste tudo do cliente
ao receber o "id" do determinado cliente*/
public Cliente carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de cliente para obter todas as informações
do cliente após encontrá-lo no laco do "Se(if)"*/
    Cliente c = new Cliente();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM cliente WHERE id=?";
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
     c.setNome(rs.getString("nome"));
     c.setTelefone(rs.getString("telefone"));
     c.setSenha(rs.getString("senha"));
     c.setEmail(rs.getString("email"));
     c.setCpf(rs.getString("cpf"));
     c.setRG(rs.getString("rg"));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do cliente pelo "id" obtido. 
    ;) legal né!                        */
    return c;
    }

}
