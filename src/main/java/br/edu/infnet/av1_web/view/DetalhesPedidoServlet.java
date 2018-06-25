package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.PedidoService;
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

@WebServlet("/detalhes-pedido")
public class DetalhesPedidoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();
        
        long pedidoId = new Long(request.getParameter("pedido"));
        PedidoService pedidoService = new PedidoService(em);
        ProdutoService produtoService = new ProdutoService(em);
        
        Pedido pedido = pedidoService.getPedidoById(pedidoId);
        List<Produto> produtos = produtoService.getListaProdutos();
        
        em.close();
        
        request.setAttribute("pedido", pedido);
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/detalhes-pedido.jsp");
        dispatcher.forward(request, response);
    }
}