package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class ProdutoRepository extends GenericRepository<Produto> {

    public ProdutoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Produto> Listar() {
        TypedQuery<Produto> query = manager.createQuery("select p from Produto p", Produto.class);
        return query.getResultList();
    }
}