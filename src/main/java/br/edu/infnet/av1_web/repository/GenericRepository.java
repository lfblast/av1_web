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
    
    public abstract void incluir(T t);
    public abstract void alterar(T t);    
    public abstract void excluir(T t);
    public abstract T selecionar(Long id);
    public abstract List<T> Listar();
}