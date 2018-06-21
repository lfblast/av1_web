package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.model.ProdutoPedido;
import br.edu.infnet.av1_web.repository.PedidoRepository;
import java.util.ArrayList;
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
    
    public Pedido getPedidoById(long id) {
        Pedido pedido = pedRep.selecionar(Pedido.class, id);
        return pedido;
    }
    
    public void excluirPedido(Pedido pedido) {
        
        List<ProdutoPedido> produtosPedidos = new ArrayList<>();
        for(ProdutoPedido prodPed : pedido.getProdutos()) {
            produtosPedidos.add(prodPed);
        }
        
        for(ProdutoPedido prodPed : produtosPedidos) {
            pedido.getProdutos().remove(prodPed);
        }
        
        pedRep.beginTransatcion();
        pedRep.excluir(pedido);
        pedRep.commitTransaction();
    }
}