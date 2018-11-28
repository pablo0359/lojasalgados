/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 *
 * @author sajsm
 */
public class InserirCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//request.setCharacterEncoding("UTF-8") seve para converter os caracteres para português Brasil
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InserirCliente</title>");                        
            out.println("</head>");
            out.println("<body>");

            
     
            try {
//Inserimos uma na classe String uma variável para recever da requisição o parâmetro "nome" por exemplo.                
                String nome = request.getParameter("nome");
                String telefone = request.getParameter("tel");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                
                String rg = request.getParameter("rg");
/*Como o fomulário só trás String(caracteres) será necessário realizar a conversão
Integer (classe de números inteiros) e depois solicitar da requisição que pegue o parâmetro desejado.*/
                String cpf = request.getParameter("cpf");
                          
//Iremos criar um objeto do tipo cliente para receber as variáveis obtidas das requisições acima.
                Cliente c = new Cliente();
               
                c.setNome(nome);
                c.setTelefone(telefone);
                c.setEmail(email);
                c.setSenha(senha);
                c.setCpf(cpf);
                c.setRG(rg);
                ClienteDAO cDAO = new ClienteDAO();
                cDAO.inserir(c);
                 response.sendRedirect("listar_cliente.jsp");
                
            } catch (Exception e) {
                out.printf("Erro:"+e);
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
