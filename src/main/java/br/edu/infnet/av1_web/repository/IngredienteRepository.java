package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Ingrediente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class IngredienteRepository extends GenericRepository<Ingrediente> {

    public IngredienteRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Ingrediente> Listar() {
        TypedQuery<Ingrediente> query = manager.createQuery("select p from Ingrediente p", Ingrediente.class);
        return query.getResultList();
    }
}