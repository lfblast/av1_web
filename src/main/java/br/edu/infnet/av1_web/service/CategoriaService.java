package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.repository.CategoriaRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class CategoriaService {
    
    private CategoriaRepository rep;
    
    public CategoriaService(EntityManager manager) {
        rep = new CategoriaRepository(manager);
    }
    
    public List<Categoria> getListaCategorias() {
        List<Categoria> categorias = rep.listar();
        return categorias;
    }
    
    public Categoria getCategoriaById(long id) {
        Categoria categoria = rep.selecionar(Categoria.class, id);
        return categoria;
    }
    
    public void excluirCategoria(Categoria categoria) throws ServiceException {
        
        ProdutoService produtoService = new ProdutoService(rep.getManager());
        List<Produto> produtos = produtoService.getListaProdutos();
        
        for(Produto produto : produtos) {
            if(produto.getCategoria().getId() == categoria.getId()) {
                throw new ServiceException("A categoria já está sendo utilizada em um produto e não pôde ser excluída.");
            }
        }
        
        rep.beginTransatcion();
        rep.excluir(categoria);
        rep.commitTransaction();
    }
    
    public void alterarCategoria(Categoria categoria) {
        rep.beginTransatcion();
        rep.alterar(categoria);
        rep.commitTransaction();
    }
    
    public void incluirCategoria(Categoria categoria) {
        rep.beginTransatcion();
        rep.incluir(categoria);
        rep.commitTransaction();
    }
}