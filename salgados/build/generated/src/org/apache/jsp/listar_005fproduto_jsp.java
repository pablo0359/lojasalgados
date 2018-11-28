package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import modelo.Classificacao;
import modelo.ClassificacaoDAO;
import modelo.ProdutoDAO;
import modelo.Produto;
import java.util.ArrayList;

public final class listar_005fproduto_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function excluir(id,nome){\n");
      out.write("                if(confirm(\"Tem certeza que deseja excluir o perfil: \"+nome+\"?\")){\n");
      out.write("                    window.open(\"excluir_produto.do?id=\"+id,\"_parent\");\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        <title>Listar Produtos</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("         ");
      out.write("\n");
      out.write("        <h1>Lista de Perfis (<a href=\"form_inserir_produto.jsp\"><img src=\"imagens/novo.png\"/></a>)</h1>\n");
      out.write("        <table border=\"1\">\n");
      out.write("            <tr>\n");
      out.write("                <td>ID</td>\n");
      out.write("                <td>NOME</td>\n");
      out.write("                <td>SABOR</td>\n");
      out.write("                <td>PREÇO</td>\n");
      out.write("                <td>INGREDIENTES</td>\n");
      out.write("                <td>DESCRIÇÃO</td>\n");
      out.write("                <td>IMAGEM</td>\n");
      out.write("                <td>PESO EM GRAMAS</td>\n");
      out.write("                <td>STATUS</td>\n");
      out.write("                <td>CLASSIFICAÇÃO</td>\n");
      out.write("                <td>OPÇÕES</td>\n");
      out.write("            </tr>\n");
      out.write("            ");

            ArrayList<Produto> lista = new ArrayList<Produto>();
            ProdutoDAO pDAO = new ProdutoDAO();
            try{
               lista = pDAO.listar();
            }catch(Exception e){
                out.print("Erro:"+e);
            }
            
            for(Produto p:lista){
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(p.getId() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getNome() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getSabor() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getPreco() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getIngredientes() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getDescricao() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getImg() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getPeso() );
      out.write("</td>\n");
      out.write("                <td>");
 
                        if(p.getStatus() == 1){
                            
      out.write(" Ativo ");
 
                        } else{
                        
      out.write(" Desativado ");

                        }
                    
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
 ClassificacaoDAO cdao = new ClassificacaoDAO();
                        Classificacao cc = new Classificacao();
                        cc = cdao.carregarPorId(p.getClassificacao().getId()); 
      out.write("\n");
      out.write("                        ");
      out.print(cc.getTipo() );
      out.write("</td>\n");
      out.write("                <td><a href=\"form_alterar_produto.jsp?id=");
      out.print(p.getId() );
      out.write("\"><img src=\"imagens/alterar.png\"/></a> <a href=\"#\" onclick=\"excluir(");
      out.print(p.getId() );
      out.write(',');
      out.write('\'');
      out.print(p.getNome() );
      out.write("')\"><img src=\"imagens/excluir.png\"/></a>  </td>\n");
      out.write("            </tr>            \n");
      out.write("            ");

            }
            
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
