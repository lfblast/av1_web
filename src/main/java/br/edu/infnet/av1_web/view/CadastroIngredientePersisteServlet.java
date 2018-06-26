package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.service.IngredienteService;
import br.edu.infnet.av1_web.util.JpaUtil;
import br.edu.infnet.av1_web.view.form.IngredienteForm;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastro-ingrediente-persiste")
public class CadastroIngredientePersisteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();        
        IngredienteService service = new IngredienteService(em);
        IngredienteForm form = null;
        try {
            form = IngredienteForm.fromRequest(request);
        } 
        catch (ServiceException ex) {
            request.setAttribute("mensagem", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/erro.jsp");
            dispatcher.forward(request, response);
        }
        
        service.incluirIngrediente(form.toIngrediente(em));
        
        em.close();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-ingredientes");
        dispatcher.forward(request, response);
    }
}