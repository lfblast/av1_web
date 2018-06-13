package br.edu.infnet.av1_web.run;

import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.repository.CategoriaRepository;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.util.List;

//Criar banco av1_web e executar este arquivo para a geração de tabelas e inserção dos registros necessários para a continuidade do trabalho.
public class CriaTabelasInsereRegistros {
    
    public static void main(String[] args) {
        
        CategoriaRepository catRep = new CategoriaRepository(JpaUtil.getEntityManager());
        Categoria categoria = catRep.selecionar(Categoria.class, 1);
        categoria.setNome("Nome Modificado de Novo");
        
        catRep.beginTransatcion();
        catRep.alterar(categoria);
        catRep.commitTransaction();
        
        List<Categoria> categorias = catRep.Listar();
        for(Categoria cat : categorias) {
            System.out.println(cat.getId() + " - " + cat.getNome());
        }
        
        //Fecha EntityManager e EntityManagerFactory para finalizar a aplicação.
        catRep.closeEntityManager();
        JpaUtil.closeEntityManagerFactory();
    }
}
