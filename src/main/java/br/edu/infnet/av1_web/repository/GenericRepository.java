package br.edu.infnet.av1_web.repository;

import java.util.List;
import javax.persistence.EntityManager;

public abstract class GenericRepository<T> {
    
    protected EntityManager manager;

    public GenericRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void beginTransatcion() {
        manager.getTransaction().begin();
    }
    
    public void commitTransaction() {
        manager.getTransaction().commit();
    }
    
    public void closeEntityManager() {
        manager.close();
    }
    
    public void incluir(T t) {
        manager.persist(t);
    }
    
    public void alterar(T t) {
        manager.merge(t);        
    }
    
    public void excluir(T t) {
        manager.remove(t);
    }
    
    public T selecionar(Class<T> clazz, long id) {
        return manager.find(clazz, id);
    }
    
    public abstract List<T> Listar();
}