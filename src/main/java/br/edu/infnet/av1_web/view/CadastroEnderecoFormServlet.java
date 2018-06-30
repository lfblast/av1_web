package br.edu.infnet.av1_web.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastro-endereco-form")
public class CadastroEnderecoFormServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setAttribute("cliente", request.getParameter("cliente"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/cadastro-endereco-form.jsp");
        dispatcher.forward(request, response);
    }
}