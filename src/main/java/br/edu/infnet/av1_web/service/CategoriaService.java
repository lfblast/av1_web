package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.repository.CategoriaRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class CategoriaService {
    
    private CategoriaRepository catRep;
    
    public CategoriaService(EntityManager manager) {
        catRep = new CategoriaRepository(manager);
    }
    
    public List<Categoria> getListaCategorias() {
        List<Categoria> categorias = catRep.listar();
        return categorias;
    }
}