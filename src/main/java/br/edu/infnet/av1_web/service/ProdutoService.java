package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.repository.ProdutoRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class ProdutoService {

    private ProdutoRepository prodRep;
    
    public ProdutoService(EntityManager manager) {
        prodRep = new ProdutoRepository(manager);
    }
    
    public List<Produto> getListaProdutos() {
        List<Produto> produtos = prodRep.listar();
        return produtos;
    }
    
    public Produto getProdutoById(long id) {
        return prodRep.selecionar(Produto.class, id);
    }
}