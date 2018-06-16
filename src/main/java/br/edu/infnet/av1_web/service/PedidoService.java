package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.repository.PedidoRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class PedidoService {
    
    private PedidoRepository pedRep;
    
    public PedidoService(EntityManager manager) {
        pedRep = new PedidoRepository(manager);
    }
    
    public void incluirPedido(Pedido pedido) {
        pedRep.beginTransatcion();
        pedRep.incluir(pedido);
        pedRep.commitTransaction();
    }
    
    public List<Pedido> getListaPedidos() {
        List<Pedido> pedidos = pedRep.listar();
        return pedidos;
    }
}