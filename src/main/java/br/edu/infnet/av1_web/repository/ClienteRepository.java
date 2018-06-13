package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class ClienteRepository extends GenericRepository<Cliente> {

    public ClienteRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Cliente> Listar() {
        TypedQuery<Cliente> query = manager.createQuery("select p from Cliente p", Cliente.class);
        return query.getResultList();
    }    
}