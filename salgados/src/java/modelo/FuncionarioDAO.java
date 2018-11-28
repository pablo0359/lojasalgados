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
public class FuncionarioDAO extends DataSource{
    
public void inserir(Funcionario f) throws Exception{
//Realizar a conexão
    this.conectar();
//inserir a variável sql
    String sql = "INSERT INTO funcionario (nome,login,status,senha,cpf,cargo,rg,email,telefone,endereco,perfil_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
//Criar o PreparedStatement para tratar o sql.
    PreparedStatement pstm = conn.prepareStatement(sql);
 //Inserir os parâmetros para cada coluna da tabela acima.
    pstm.setString(1, f.getNome());
    pstm.setString(2, f.getLogin());
    pstm.setInt(3, f.getStatus());
    pstm.setString(4, f.getSenha());
    pstm.setString(5, f.getCpf());
    pstm.setString(6, f.getCargo());
    pstm.setString(7, f.getRg());
    pstm.setString(8, f.getEmail());
    pstm.setString(9, f.getTelefone());
    pstm.setString(10, f.getEndereco());
    pstm.setInt(11, f.getPerfil().getId());
    
    }

    /*Criar o método carregarPorId para que ele liste tudo do funcionario
ao receber o "id" do determinado funcionario*/
public Funcionario carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de funcionario para obter todas as informações
do funcionario após encontrá-lo no laco do "Se(if)"*/
    Funcionario f = new Funcionario();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM funcionario WHERE id=?";
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
     f.setId(rs.getInt("id"));
     f.setNome(rs.getString("nome"));
     f.setLogin(rs.getString("login"));
     f.setStatus(rs.getInt("status"));
     f.setSenha(rs.getString("senha"));
     f.setCpf(rs.getString("cpf"));
     f.setCargo(rs.getString("cargo"));
     f.setRg(rs.getString("rg"));
     f.setEmail(rs.getString("email"));
     f.setTelefone(rs.getString("telefone"));
     f.setEndereco(rs.getString("endereco"));
     PerfilDAO pDAO = new PerfilDAO();
     f.setPerfil(pDAO.carregarPorId(rs.getInt("perfil_id")));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do funcionario pelo "id" obtido. 
    ;) legal né!                        */
    return f;
    }

//Método para realizar a alteração do funcionario.
public void alterar(Funcionario f) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE funcionario SET nome=?,login=?,status=?,senha=?,cpf=?,cargo=?,rg=?,email=?,telefone=?,endereco=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, f.getNome());
    pstm.setString(2, f.getLogin());
    pstm.setInt(3, f.getStatus());
    pstm.setString(4, f.getSenha());
    pstm.setString(5, f.getCpf());
    pstm.setString(6, f.getCargo());
    pstm.setString(7, f.getRg());
    pstm.setString(8, f.getEmail());
    pstm.setString(9, f.getTelefone());
    pstm.setString(10, f.getEndereco());
    pstm.setInt(11, f.getPerfil().getId());
    pstm.setInt(8, f.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }
 
 //Criar método para excluir um funcionario, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM funcionario WHERE id=?";
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

//Criar um método para listar os funcionarios
 public ArrayList<Funcionario> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de funcionario e gerar um novo objeto chamado lista, que irá receber todos os funcionarios e incluir na variável lista.
    ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM funcionario";
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
     Funcionario f = new Funcionario();
     f.setId(rs.getInt("id"));
     f.setNome(rs.getString("nome"));
     f.setLogin(rs.getString("login"));
     f.setStatus(rs.getInt("status"));
     f.setSenha(rs.getString("senha"));
     f.setCpf(rs.getString("cpf"));
     f.setCargo(rs.getString("cargo"));
     f.setRg(rs.getString("rg"));
     f.setEmail(rs.getString("email"));
     f.setTelefone(rs.getString("telefone"));
     f.setEndereco(rs.getString("endereco"));
     PerfilDAO pDAO = new PerfilDAO();
     f.setPerfil(pDAO.carregarPorId(rs.getInt("perfil_id")));
/*Ao terminar de inserir a informação da tabela no laço de repetição
iremos adicionar na lista conforme abaixo:*/
     lista.add(f);
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Retorna a lista com os funcionarios existentes no banco de dados, caso contrário
retorna uma lista vazia.*/
 return lista;
    }

//Criando o metodo para verificar o login se existe.
public boolean loginExiste(int id, String login) throws Exception{
//Necessário conectar com o banco para consultar o login dos usuários
    this.conectar();
//Criar a variável sql para prepará-la
    String sql = "SELECT * FROM funcionario WHERE cpf=?";
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
public Funcionario logar(String login, String senha) throws Exception{
//conectar com o banco de dado
    this.conectar();
//Criando um objeto funcionario para obter o login e a senha
    Funcionario f = new Funcionario();
//Criar a veriável sql para inserir a string no banco de dados
    String sql = "SELECT * FROM funcionario WHERE login=?";
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
     f.setId(rs.getInt("id"));
     f.setNome(rs.getString("nome"));
     f.setLogin(rs.getString("login"));
     f.setStatus(rs.getInt("status"));
     f.setSenha(rs.getString("senha"));
     f.setCpf(rs.getString("cpf"));
     f.setCargo(rs.getString("cargo"));
     f.setRg(rs.getString("rg"));
     f.setEmail(rs.getString("email"));
     f.setTelefone(rs.getString("telefone"));
     f.setEndereco(rs.getString("endereco"));
     PerfilDAO pDAO = new PerfilDAO();
     f.setPerfil(pDAO.carregarPorId(rs.getInt("perfil_id")));
     }
    }
//Realizar a desconexão do banco de dados
    this.desconectar();
//Retornar um objeto "c" com todas as informações dos funcionario para conferir o login e senha.
    return f;
    }

}
