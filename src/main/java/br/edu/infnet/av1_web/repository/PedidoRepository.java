package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class PedidoRepository extends GenericRepository<Pedido> {

    public PedidoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Pedido> Listar() {
        TypedQuery<Pedido> query = manager.createQuery("select p from Pedido p", Pedido.class);
        return query.getResultList();
    }
}