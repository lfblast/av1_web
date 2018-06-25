package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Categoria;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


public class CategoriaForm {
    
    private String nome;
    
    public static CategoriaForm fromRequest(HttpServletRequest request) throws ServiceException {
        
        CategoriaFormValidacao.valida(request);
        
        CategoriaForm form = new CategoriaForm();        
        form.setNome(request.getParameter("nome"));
        
        return form;
    }
    
    public Categoria toCategoria(EntityManager em) {
        
        Categoria categoria = new Categoria();        
        categoria.setNome(this.getNome());
        
        return categoria;
    }
    
    public Categoria toCategoriaAlterado(Categoria categoriaJaCadastrada, Categoria categoriaForm) {
        
        categoriaJaCadastrada.setNome(categoriaForm.getNome());
        
        return categoriaJaCadastrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}