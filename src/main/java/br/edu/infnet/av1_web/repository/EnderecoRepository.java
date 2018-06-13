package br.edu.infnet.av1_web.repository;

import br.edu.infnet.av1_web.model.Endereco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class EnderecoRepository extends GenericRepository<Endereco> {

    public EnderecoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Endereco> Listar() {
        TypedQuery<Endereco> query = manager.createQuery("select p from Endereco p", Endereco.class);
        return query.getResultList();
    }
}