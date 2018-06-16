package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.ClienteService;
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

@WebServlet("/cadastro-pedido-form")
public class CadastroPedidoFormServlet extends HttpServlet {
     
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        long clienteId = new Long(request.getParameter("clienteId"));
        
        EntityManager em = JpaUtil.getEntityManager();
        
        ClienteService clienteService = new ClienteService(em);
        Cliente cliente = clienteService.getClienteById(clienteId);
        
        ProdutoService produtoService = new ProdutoService(em);
        List<Produto> produtos = produtoService.getListaProdutos();
        
        request.setAttribute("cliente", cliente);
        request.setAttribute("enderecos", cliente.getEnderecos());
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/cadastro-pedido-form.jsp");
        dispatcher.forward(request, response);
    }
}