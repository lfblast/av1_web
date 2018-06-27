package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.ProdutoService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remover-produto-persiste")
public class RemoverProdutoPersisteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();
        ProdutoService produtoService = new ProdutoService(em);
        
        long produtoId = new Long(request.getParameter("produto"));
        Produto produto = produtoService.getProdutoById(produtoId);
        try {
            produtoService.excluirProduto(produto);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-produtos");
            dispatcher.forward(request, response);
        } catch (ServiceException ex) {
            request.setAttribute("mensagem", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            em.close();
        }
    }
}