package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class CategoriaRepository extends GenericRepository<Categoria> {    

    public CategoriaRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Categoria> listar() {
        TypedQuery<Categoria> query = manager.createQuery("select p from Categoria p", Categoria.class);
        return query.getResultList();
    }
}