package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.service.ClienteService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remover-cliente-persiste")
public class RemoverClientePersisteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JpaUtil.getEntityManager();
        ClienteService clienteService = new ClienteService(em);

        long clienteId = new Long(request.getParameter("cliente"));
        Cliente cliente = clienteService.getClienteById(clienteId);
        try {
            clienteService.excluirCliente(cliente);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-clientes");
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