package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Ingrediente;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.model.ProdutoPedido;
import br.edu.infnet.av1_web.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;


public class ProdutoService {

    private ProdutoRepository rep;
    
    public ProdutoService(EntityManager manager) {
        rep = new ProdutoRepository(manager);
    }
    
    public void incluirProduto(Produto produto) {
        rep.beginTransatcion();
        rep.incluir(produto);
        rep.commitTransaction();
    }
    
    public List<Produto> getListaProdutos() {
        List<Produto> produtos = rep.listar();
        return produtos;
    }
    
    public Produto getProdutoById(long id) {
        return rep.selecionar(Produto.class, id);
    }
    
    public void excluirProduto(Produto produto) throws ServiceException {
        
        ProdutoPedidoService prodPedService = new ProdutoPedidoService(rep.getManager());
        List<ProdutoPedido> produtosPedidos = prodPedService.getListaProdutosPedidos();
        
        for(ProdutoPedido prodPed : produtosPedidos) {
            if(prodPed.getProduto().getId() == produto.getId()) {
                throw new ServiceException("O produto já está sendo utilizado em um pedido e não pôde ser excluído.");
            }
        }
        
        List<Ingrediente> ingredientes = new ArrayList<>();
        for(Ingrediente ingr : produto.getIngredientes()) {
            ingredientes.add(ingr);
        }
        
        for(Ingrediente ingr : ingredientes) {
            produto.getIngredientes().remove(ingr);
        }
        
        rep.beginTransatcion();
        rep.excluir(produto);
        rep.commitTransaction();
    }
    
    public void alterarProduto(Produto produto) {
        rep.beginTransatcion();
        rep.alterar(produto);
        rep.commitTransaction();
    }
}