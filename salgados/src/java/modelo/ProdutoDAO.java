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
public class ProdutoDAO extends DataSource{

//método Inserir Produto -- Empurrando o tratamento do erro com o throws
 public void inserir (Produto p) throws Exception{
//neste método peque a conexão com o banco de dados.
     this.conectar();
//Criado uma variável "sql" para guardar o comando de inserção do produto conforme abaixo.
     String sql = "INSERT INTO produto (nome,sabor,preco,ingredientes,descricao,img,peso,status,classificacao_id) VALUES (?,?,?,?,?,?,?,?,?)";
//o atributo pstm criado para o PreparedStatement irá conectar e preparar para inserir o código no banco de dados.
     PreparedStatement pstm = conn.prepareStatement(sql);
//Iremos inserir cada atributo nas colunas do banco de dados acima com o pstm.
    pstm.setString(1, p.getNome());
    pstm.setString(2, p.getSabor());
    pstm.setDouble(3, p.getPreco());
    pstm.setString(4, p.getIngredientes());
    pstm.setString(5, p.getDescricao());
    pstm.setString(6, p.getImg());
    pstm.setInt(7, p.getPeso());
    pstm.setInt(8, p.getStatus());
    pstm.setInt(9, p.getClassificacao().getId());
//Executar o pstm com as infromações das variáveis que foram inseridas no pstm
    pstm.execute();
/*Agora iremos fechar a conexão do banco de dados para liberar espaço na memoria,
quando for necessário iremos realizar nova abertura da conexão*/
this.desconectar();
    }

 //Criar método para excluir um produto, caso ocorra erros iremos tratar mais tarde com o Exception.
 public void excluir (int id) throws Exception{
//Necessario abrir um conexão com o banco de dados.
    this.conectar(); //this significa esta classe, ou seja conecte nessa classe dentro do método que ela se encontra.
//Criar um variável para receber o comando sql.
    String sql = "DELETE FROM produto WHERE id=?";
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

//Criar um método para listar os produtos
public ArrayList<Produto> listar() throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
//Criar um vetor ArrayList de produto e gerar um novo objeto chamado lista, que irá receber todos os produtos e incluir na variável lista.
    ArrayList<Produto> lista = new ArrayList<Produto>();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM produto";
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
     Produto p = new Produto();
     p.setId(rs.getInt("id"));
     p.setNome(rs.getString("nome"));
     p.setSabor(rs.getString("sabor"));
     p.setPreco(rs.getDouble("preco"));
     p.setIngredientes(rs.getString("ingredientes"));
     p.setDescricao(rs.getString("descricao"));
     p.setImg(rs.getString("img"));
     p.setPeso(rs.getInt("peso"));
     p.setStatus(rs.getInt("status"));
      ClassificacaoDAO cDAO = new ClassificacaoDAO();
     p.setClassificacao(cDAO.carregarPorId(rs.getInt("classificacao_id")));
//Mesma coisa acima será feita com o Objeto cDAO que foi (instanciado)criado.
     lista.add(p);
        }
    this.desconectar();
 return lista;
    }

//Método para realizar a alteração do cliente.
public void alterar(Produto p) throws Exception{
//Realizar a conexão.
    this.conectar();
//Criar a variável que irá possuir a string do sql.
    String sql = "UPDATE produto SET nome=?,sabor=?,preco=?,ingredientes=?,descricao=?,img=?,peso=?,status=?,classificacao_id=? WHERE id=?";
//variável que irá incluir as variáveis no sql
    PreparedStatement pstm = conn.prepareStatement(sql);
//Incluir as variáveis dentro do pstm para depois executá-las.
    pstm.setString(1, p.getNome());
    pstm.setString(2, p.getSabor());
    pstm.setDouble(3, p.getPreco());
    pstm.setString(4, p.getIngredientes());
    pstm.setString(5, p.getDescricao());
    pstm.setString(6, p.getImg());
    pstm.setInt(7, p.getPeso());
    pstm.setInt(8, p.getStatus());
    pstm.setInt(9, p.getClassificacao().getId());
    pstm.setInt(10, p.getId());
//Executar o string com as variáveis dentro do pstm
    pstm.execute();
//Desconetar do banco de dados para liberar a memória.
    this.desconectar();
    }

/*Criar o método carregarPorId para que ele liste tudo do produto
ao receber o "id" do determinado produto*/
public Produto carregarPorId(int id) throws Exception{
//Realizar a conexão com o banco de dados.
    this.conectar();
/*Criar um objeto de produto para obter todas as informações
do produto após encontrá-lo no laco do "Se(if)"*/
    Produto p = new Produto();
//Criar a variável que irá receber os comando de sql.
    String sql = "SELECT * FROM produto WHERE id=?";
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
     p.setSabor(rs.getString("sabor"));
     p.setPreco(rs.getDouble("preco"));
     p.setIngredientes(rs.getString("ingredientes"));
     p.setDescricao(rs.getString("descricao"));
     p.setImg(rs.getString("img"));
     p.setPeso(rs.getInt("peso"));
     p.setStatus(rs.getInt("status"));
      ClassificacaoDAO cDAO = new ClassificacaoDAO();
     p.setClassificacao(cDAO.carregarPorId(rs.getInt("classificacao_id")));
        }
//Desconectar do banco de dados para não usar memória desnecessáriamente.
    this.desconectar();
/*Abaixo se tiver passado pelo laço terá toda a informação
necessária do produto pelo "id" obtido. 
    ;) legal né!                        */
    return p;
    }
}
