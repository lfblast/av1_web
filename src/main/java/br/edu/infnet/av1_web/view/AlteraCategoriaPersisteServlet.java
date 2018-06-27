package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.service.CategoriaService;
import br.edu.infnet.av1_web.util.JpaUtil;
import br.edu.infnet.av1_web.view.form.CategoriaForm;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/alterar-categoria-persiste")
public class AlteraCategoriaPersisteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JpaUtil.getEntityManager();
        CategoriaService service = new CategoriaService(em);
        CategoriaForm form = null;

        try {
            form = CategoriaForm.fromRequest(request);

            Categoria categoria = form.toCategoria(em);
            Categoria categoriaCadastrada = service.getCategoriaById(new Long(request.getParameter("id")));

            service.alterarCategoria(form.toCategoriaAlterado(categoriaCadastrada, categoria));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-categorias");
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
