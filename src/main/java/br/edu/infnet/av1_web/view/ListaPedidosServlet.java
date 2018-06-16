package br.edu.infnet.av1_web.view;

import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.service.PedidoService;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lista-pedidos")
public class ListaPedidosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PedidoService pedidoService = new PedidoService(JpaUtil.getEntityManager());
        
        List<Pedido> pedidos = pedidoService.getListaPedidos();
        
        request.setAttribute("pedidos", pedidos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/lista-pedidos.jsp");
        dispatcher.forward(request, response);
    }
}
