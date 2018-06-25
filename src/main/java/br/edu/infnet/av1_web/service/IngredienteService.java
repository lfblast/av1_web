package br.edu.infnet.av1_web.service;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Ingrediente;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.repository.IngredienteRepository;
import java.util.List;
import javax.persistence.EntityManager;

public class IngredienteService {

    private IngredienteRepository rep;

    public IngredienteService(EntityManager manager) {
        rep = new IngredienteRepository(manager);
    }

    public List<Ingrediente> getListaIngredientes() {
        List<Ingrediente> ingredientes = rep.listar();
        return ingredientes;
    }

    public Ingrediente getIngredienteById(long id) {
        Ingrediente ingrediente = rep.selecionar(Ingrediente.class, id);
        return ingrediente;
    }

    public void excluirIngrediente(Ingrediente ingrediente) throws ServiceException {

        ProdutoService produtoService = new ProdutoService(rep.getManager());
        List<Produto> produtos = produtoService.getListaProdutos();

        for (Produto produto : produtos) {
            for (Ingrediente ingr : produto.getIngredientes()) {
                if (ingr.getId() == ingrediente.getId()) {
                    throw new ServiceException("O ingrediente já está sendo utilizado em um produto e não pôde ser excluído.");
                }
            }
        }

        rep.beginTransatcion();
        rep.excluir(ingrediente);
        rep.commitTransaction();
    }

    public void alterarIngrediente(Ingrediente ingrediente) {
        rep.beginTransatcion();
        rep.alterar(ingrediente);
        rep.commitTransaction();
    }

    public void incluirIngrediente(Ingrediente ingrediente) {
        rep.beginTransatcion();
        rep.incluir(ingrediente);
        rep.commitTransaction();
    }
}
