package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.ProdutoService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lista-produtos")
public class ListaProdutosServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JpaUtil.getEntityManager();
        
        ProdutoService produtoService = new ProdutoService(em);
        
        List<Produto> produtos = produtoService.getListaProdutos();
        
        em.close();
        
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/lista-produtos.jsp");
        dispatcher.forward(request, response);
    }
}