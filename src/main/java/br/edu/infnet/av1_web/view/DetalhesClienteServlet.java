package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.model.Endereco;
import br.edu.infnet.av1_web.service.ClienteService;
import br.edu.infnet.av1_web.util.JpaUtil;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detalhes-cliente")
public class DetalhesClienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JpaUtil.getEntityManager();

        long clienteId = new Long(request.getParameter("cliente"));
        ClienteService clienteService = new ClienteService(em);
        List<Endereco> enderecos = new ArrayList<>();

        Cliente cliente = clienteService.getClienteById(clienteId);
        
        em.getTransaction().begin();
        if(cliente.getEnderecos() != null || cliente.getEnderecos().size() > 0) {
            enderecos = cliente.getEnderecos();
        }

        em.close();

        request.setAttribute("cliente", cliente);
        request.setAttribute("enderecos", enderecos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/detalhes-cliente.jsp");
        dispatcher.forward(request, response);
    }
}
