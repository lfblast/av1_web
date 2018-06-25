package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.repository.ProdutoRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class ProdutoService {

    private ProdutoRepository rep;
    
    public ProdutoService(EntityManager manager) {
        rep = new ProdutoRepository(manager);
    }
    
    public List<Produto> getListaProdutos() {
        List<Produto> produtos = rep.listar();
        return produtos;
    }
    
    public Produto getProdutoById(long id) {
        return rep.selecionar(Produto.class, id);
    }
}