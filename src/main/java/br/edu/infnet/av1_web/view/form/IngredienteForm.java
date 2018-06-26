package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Ingrediente;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


public class IngredienteForm {
    
    private String nome;
    
    public static IngredienteForm fromRequest(HttpServletRequest request) throws ServiceException {
        
        IngredienteFormValidacao.valida(request);
        
        IngredienteForm form = new IngredienteForm();        
        form.setNome(request.getParameter("nome"));
        
        return form;
    }
    
    public Ingrediente toIngrediente(EntityManager em) {
        
        Ingrediente ingrediente = new Ingrediente();        
        ingrediente.setNome(this.getNome());
        
        return ingrediente;
    }
    
    public Ingrediente toIngredienteAlterado(Ingrediente ingredienteJaCadastrada, Ingrediente ingredienteForm) {
        
        ingredienteJaCadastrada.setNome(ingredienteForm.getNome());
        
        return ingredienteJaCadastrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}