package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Cliente;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


public class ClienteForm {
    
    private String nome;
    private String telefone;
    
    public static ClienteForm fromRequest(HttpServletRequest request) throws ServiceException {
        
        ClienteFormValidacao.valida(request);
        
        ClienteForm form = new ClienteForm();        
        form.setNome(request.getParameter("nome"));
        form.setTelefone(request.getParameter("telefone"));
        
        return form;
    }
    
    public Cliente toCliente(EntityManager em) {
        
        Cliente cliente = new Cliente();        
        cliente.setNome(this.getNome());
        cliente.setTelefone(new Integer(this.getTelefone()));
        
        return cliente;
    }
    
    public Cliente toClienteAlterado(Cliente clienteJaCadastrado, Cliente clienteForm) {
        
        clienteJaCadastrado.setNome(clienteForm.getNome());
        clienteJaCadastrado.setTelefone(clienteForm.getTelefone());
        
        return clienteJaCadastrado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }    
}