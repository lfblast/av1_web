package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.repository.ClienteRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class ClienteService {
    
    private ClienteRepository rep;
    
    public ClienteService(EntityManager manager) {
        rep = new ClienteRepository(manager);
    }
    
    public List<Cliente> getListaClientes() {
        List<Cliente> clientes = rep.listar();
        return clientes;
    }
    
    public Cliente getClienteById(long id) {
        Cliente cliente = rep.selecionar(Cliente.class, id);
        return cliente;
    }
}