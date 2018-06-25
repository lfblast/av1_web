package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Ingrediente;
import br.edu.infnet.av1_web.service.IngredienteService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detalhes-ingrediente")
public class DetalhesIngredienteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();
        
        long ingredienteId = new Long(request.getParameter("ingrediente"));
        IngredienteService ingredienteService = new IngredienteService(em);
        
        Ingrediente ingrediente = ingredienteService.getIngredienteById(ingredienteId);
        
        em.close();
        
        request.setAttribute("ingrediente", ingrediente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/detalhes-ingrediente.jsp");
        dispatcher.forward(request, response);
    }
}