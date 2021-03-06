package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.model.Ingrediente;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.CategoriaService;
import br.edu.infnet.av1_web.service.IngredienteService;
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

@WebServlet("/detalhes-produto")
public class DetalhesProdutoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();
        
        long produtoId = new Long(request.getParameter("produto"));
        ProdutoService produtoService = new ProdutoService(em);
        Produto produto = produtoService.getProdutoById(produtoId);
                
        CategoriaService categoriaService = new CategoriaService(em);
        List<Categoria> categorias = categoriaService.getListaCategorias();
        
        IngredienteService ingredienteService = new IngredienteService(em);
        List<Ingrediente> ingredientes = ingredienteService.getListaIngredientes();
        
        em.close();
        
        request.setAttribute("produto", produto);
        request.setAttribute("ingredientes", ingredientes);
        request.setAttribute("categorias", categorias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/detalhes-produto.jsp");
        dispatcher.forward(request, response);
    }
}