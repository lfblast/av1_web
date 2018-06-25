package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.service.CategoriaService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detalhes-categoria")
public class DetalhesCategoriaServlet extends HttpServlet {
        
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();
        
        long categoriaId = new Long(request.getParameter("categoria"));
        CategoriaService categoriaService = new CategoriaService(em);
        
        Categoria categoria = categoriaService.getCategoriaById(categoriaId);
        
        em.close();
        
        request.setAttribute("categoria", categoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/detalhes-categoria.jsp");
        dispatcher.forward(request, response);
    }
}