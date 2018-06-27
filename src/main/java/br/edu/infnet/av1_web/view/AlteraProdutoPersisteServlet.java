package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.ProdutoService;
import br.edu.infnet.av1_web.util.JpaUtil;
import br.edu.infnet.av1_web.view.form.ProdutoForm;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/alterar-produto-persiste")
public class AlteraProdutoPersisteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JpaUtil.getEntityManager();
        ProdutoService service = new ProdutoService(em);
        ProdutoForm form = null;
        try {
            form = ProdutoForm.fromRequest(request);

            Produto produto = form.toProduto(em);
            Produto produtoCadastrado = service.getProdutoById(new Long(request.getParameter("id")));

            service.alterarProduto(form.toProdutoAlterado(produtoCadastrado, produto));

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