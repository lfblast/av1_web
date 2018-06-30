package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Endereco;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


public class EnderecoForm {
    
    private String rua;
    private String numero;
    private String referencia;
    
    public static EnderecoForm fromRequest(HttpServletRequest request) throws ServiceException {
        
        EnderecoFormValidacao.valida(request);
        
        EnderecoForm form = new EnderecoForm();        
        form.setRua(request.getParameter("rua"));
        form.setNumero(request.getParameter("numero"));        
        form.setReferencia(request.getParameter("referencia"));
        
        return form;
    }
    
    public Endereco toEndereco(EntityManager em) {
        
        Endereco endereco = new Endereco();        
        endereco.setRua(this.getRua());
        endereco.setNumero(new Integer(this.getNumero()));
        endereco.setReferencia(this.getReferencia());
        
        return endereco;
    }
    
    public Endereco toEnderecoAlterado(Endereco enderecoJaCadastrado, Endereco enderecoForm) {
        
        enderecoJaCadastrado.setRua(enderecoForm.getRua());
        enderecoJaCadastrado.setNumero(enderecoForm.getNumero());
        enderecoJaCadastrado.setReferencia(enderecoForm.getReferencia());
        
        return enderecoJaCadastrado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}