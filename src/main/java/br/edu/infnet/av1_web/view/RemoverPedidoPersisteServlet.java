package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.service.PedidoService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remover-pedido-persiste")
public class RemoverPedidoPersisteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = JpaUtil.getEntityManager();
        PedidoService pedidoService = new PedidoService(em);
        
        long pedidoId = new Long(request.getParameter("pedido"));
        Pedido pedido = pedidoService.getPedidoById(pedidoId);
        pedidoService.excluirPedido(pedido);
        
        em.close();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-pedidos");
        dispatcher.forward(request, response);
    }
}