package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Endereco;
import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.repository.EnderecoRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class EnderecoService {
    
    private EnderecoRepository rep;
    
    public EnderecoService(EntityManager manager) {
        rep = new EnderecoRepository(manager);
    }
    
    public void incluirEndereco(Endereco endereco) {
        rep.beginTransatcion();
        rep.incluir(endereco);
        rep.commitTransaction();
    }
    
    public List<Endereco> getListaEnderecos() {
        List<Endereco> enderecos = rep.listar();
        return enderecos;
    }
    
    public Endereco getEnderecoById(long id) {
        return rep.selecionar(Endereco.class, id);
    }
    
    public void excluirEndereco(Endereco endereco) throws ServiceException {
        
        rep.beginTransatcion();
        
        PedidoService pedidoService = new PedidoService(rep.getManager());
        List<Pedido> pedidos = pedidoService.getListaPedidos();
        
        for(Pedido pedido : pedidos) {
            if(pedido.getEndereco().getId() == endereco.getId()) {
                throw new ServiceException("O endereço já está sendo utilizado em um pedido e não pôde ser excluído.");
            }
        }
        
        
        rep.excluir(endereco);
        rep.commitTransaction();
    }
    
    public void alterarEndereco(Endereco endereco) {
        rep.beginTransatcion();
        rep.alterar(endereco);
        rep.commitTransaction();
    }
}