package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class PessoaRepository extends GenericRepository<Pessoa> {

    public PessoaRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Pessoa> Listar() {
        TypedQuery<Pessoa> query = manager.createQuery("select p from Pessoa p", Pessoa.class);
        return query.getResultList();
    }
}