package br.edu.infnet.av1_web.run;

import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.repository.CategoriaRepository;
import br.edu.infnet.av1_web.util.JpaUtil;

public class InsereRegistros {
    
    public static void main(String[] args) {
        Categoria categoria = new Categoria();
        categoria.setNome("Nome Teste");
        
        CategoriaRepository catRep = new CategoriaRepository(JpaUtil.getEntityManager());
        
        catRep.beginTransatcion();
        catRep.incluir(categoria);
        catRep.commitTransaction();        
        
        //Fecha EntityManager e EntityManagerFactory para finalizar a aplicação.
        catRep.closeEntityManager();
        JpaUtil.closeEntityManagerFactory();
    }
}
