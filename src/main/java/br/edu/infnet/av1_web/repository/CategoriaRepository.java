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
    public void incluir(Categoria categoria) {        
        manager.persist(categoria);        
    }
    
    @Override
    public void alterar(Categoria categoria) {        
        manager.merge(categoria);        
    }
    
    @Override
    public void excluir(Categoria categoria) {        
        manager.remove(categoria);        
    }

    @Override
    public Categoria selecionar(Long id) {
        return manager.find(Categoria.class, id);
    }

    @Override
    public List<Categoria> Listar() {
        TypedQuery<Categoria> query = manager.createQuery("select p from Produto p", Categoria.class);
        return query.getResultList();
    }
}