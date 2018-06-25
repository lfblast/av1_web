package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.model.ProdutoPedido;
import br.edu.infnet.av1_web.repository.PedidoRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;


public class PedidoService {
    
    private PedidoRepository rep;
    
    public PedidoService(EntityManager manager) {
        rep = new PedidoRepository(manager);
    }
    
    public void incluirPedido(Pedido pedido) {
        rep.beginTransatcion();
        rep.incluir(pedido);
        rep.commitTransaction();
    }
    
    public List<Pedido> getListaPedidos() {
        List<Pedido> pedidos = rep.listar();
        return pedidos;
    }
    
    public Pedido getPedidoById(long id) {
        Pedido pedido = rep.selecionar(Pedido.class, id);
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
        
        rep.beginTransatcion();
        rep.excluir(pedido);
        rep.commitTransaction();
    }
    
    public void alterarPedido(Pedido pedido) {
        rep.beginTransatcion();
        rep.alterar(pedido);
        rep.commitTransaction();
    }
}