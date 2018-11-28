package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import modelo.ClienteDAO;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Cliente;

public final class form_005falterar_005fcliente_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    Cliente c = new Cliente();
    try{
       int id = Integer.parseInt(request.getParameter("id"));
       ClienteDAO cDAO = new ClienteDAO();
       c = cDAO.carregarPorId(id);
       
       ClienteDAO pDAO = new ClienteDAO();
       clientes = pDAO.listar();
    }catch(Exception e){
        out.print("Erro:"+e);
    }

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Alterar Cliente</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Editando Cliente</h1>\n");
      out.write("        <form action=\"alterar_cliente.do\" method=\"post\">\n");
      out.write("            ID:");
      out.print(c.getId() );
      out.write("<br/>\n");
      out.write("            Nome:    <input type=\"text\" name=\"nome\" size=\"60\" required/><br/>\n");
      out.write("            Telefone:<input type=\"text\" name=\"tel\" size=\"60\" required/><br/>\n");
      out.write("            Endereço:<input type=\"text\" name=\"end\" size=\"60\" required/><br/>\n");
      out.write("            email:   <input type=\"text\" name=\"email\" size=\"60\"/><br/>\n");
      out.write("            Status:  <input type=\"text\" name=\"status\" size=\"60\"/><br/>\n");
      out.write("            CPF:     <input type=\"text\" name=\"cpf\" size=\"60\" required/><br/>\n");
      out.write("            Senha:   <input type=\"password\" name=\"senha\" size=\"60\" required/><br/>\n");
      out.write("             <br/>\n");
      out.write("            <input type=\"submit\" value=\"Salvar\"/>\n");
      out.write("        </form>\n");
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
