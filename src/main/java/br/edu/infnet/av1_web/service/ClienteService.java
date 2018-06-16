package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.repository.ClienteRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class ClienteService {
    
    private ClienteRepository clienteRep;
    
    public ClienteService(EntityManager manager) {
        clienteRep = new ClienteRepository(manager);
    }
    
    public List<Cliente> getListaClientes() {
        List<Cliente> clientes = clienteRep.listar();
        return clientes;
    }
    
    public Cliente getClienteById(long id) {
        Cliente cliente = clienteRep.selecionar(Cliente.class, id);
        return cliente;
    }
}