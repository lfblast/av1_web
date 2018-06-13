package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.ProdutoPedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class ProdutoPedidoRepository extends GenericRepository<ProdutoPedido> {

    public ProdutoPedidoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<ProdutoPedido> Listar() {
        TypedQuery<ProdutoPedido> query = manager.createQuery("select p from ProdutoPedido p", ProdutoPedido.class);
        return query.getResultList();
    }
}