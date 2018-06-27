package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.ProdutoPedido;
import br.edu.infnet.av1_web.repository.ProdutoPedidoRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class ProdutoPedidoService {

    private ProdutoPedidoRepository rep;
    
    public ProdutoPedidoService(EntityManager manager) {
        rep = new ProdutoPedidoRepository(manager);
    }
    
    public List<ProdutoPedido> getListaProdutosPedidos() {
        List<ProdutoPedido> produtosPedidos = rep.listar();
        return produtosPedidos;
    }
}