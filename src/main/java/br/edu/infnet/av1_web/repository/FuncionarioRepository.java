package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class FuncionarioRepository extends GenericRepository<Funcionario> {

    public FuncionarioRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Funcionario> Listar() {
        TypedQuery<Funcionario> query = manager.createQuery("select p from Funcionario p", Funcionario.class);
        return query.getResultList();
    }
}