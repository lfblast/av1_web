package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.model.Pedido;
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
    
    public void incluirCliente(Cliente cliente) {
        rep.beginTransatcion();
        rep.incluir(cliente);
        rep.commitTransaction();
    }
    
    public void excluirCliente(Cliente cliente) throws ServiceException {        
        
        PedidoService pedidoService = new PedidoService(rep.getManager());
        List<Pedido> pedidos = pedidoService.getListaPedidos();
        
        for(Pedido pedido : pedidos) {
            if(pedido.getCliente().getId() == cliente.getId()) {
                throw new ServiceException("O cliente já está sendo utilizado em um pedido e não pôde ser excluído.");
            }
        }
        
        rep.beginTransatcion();
        rep.excluir(cliente);
        rep.commitTransaction();
    }
    
    public void alterarProduto(Cliente cliente) {
        rep.beginTransatcion();
        rep.alterar(cliente);
        rep.commitTransaction();
    }
}