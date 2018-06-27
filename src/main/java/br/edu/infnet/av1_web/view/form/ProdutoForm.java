package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.model.Ingrediente;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.service.IngredienteService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


public class ProdutoForm {
    
    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(
                new Locale("pt", "BR"));
        DECIMAL_FORMAT.applyPattern("#0.00");
    }
    
    private String codigo;
    private String nome;
    private String categoria;
    private String[] ingredientes;    
    private String preco;
    
    public static ProdutoForm fromRequest(HttpServletRequest request) throws ServiceException {
        
        ProdutoFormValidacao.valida(request);
        
        ProdutoForm form = new ProdutoForm();
        
        form.setCodigo(request.getParameter("codigo"));
        form.setNome(request.getParameter("nome"));
        form.setCategoria(request.getParameter("categoria"));
        form.setIngredientes(request.getParameterValues("ingredientes"));
        form.setPreco(request.getParameter("preco"));
        
        return form;
    }
    
    public Produto toProduto(EntityManager em) {
        
        Produto produto = new Produto();
        Categoria cat = new Categoria();
        List<Ingrediente> ingrs = new ArrayList<>();
        
        produto.setCodigo(new Integer(this.getCodigo()));
        produto.setNome(this.getNome());        
        
        cat.setId(new Long(this.getCategoria()));
        produto.setCategoria(cat);
        
        try {
            produto.setPreco(new BigDecimal(DECIMAL_FORMAT.parse(this.getPreco()).doubleValue()));
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Ingrediente ingrediente = null;
        IngredienteService ingrService = new IngredienteService(em);
        for(String ing : this.getIngredientes()) {
            ingrediente = ingrService.getIngredienteById(new Long(ing));
            ingrs.add(ingrediente);
        }
        produto.setIngredientes(ingrs);
        
        return produto;
    }
    
    public Produto toProdutoAlterado(Produto produtoJaCadastrado, Produto produtoForm) {
        
        produtoJaCadastrado.setCodigo(produtoForm.getCodigo());
        produtoJaCadastrado.setNome(produtoForm.getNome());
        produtoJaCadastrado.setPreco(produtoForm.getPreco());
        produtoJaCadastrado.setCategoria(produtoForm.getCategoria());
        
        List<Ingrediente> ingrs = new ArrayList<>();
        for(Ingrediente ingrJaCadastrado : produtoJaCadastrado.getIngredientes()) {
            ingrs.add(ingrJaCadastrado);
        }
        
        for(Ingrediente ingrJaCadastrado : ingrs) {
            produtoJaCadastrado.getIngredientes().remove(ingrJaCadastrado);
        }
        
        for(Ingrediente ingrASerCadastrado : produtoForm.getIngredientes()) {
            produtoJaCadastrado.getIngredientes().add(ingrASerCadastrado);            
        }
        
        produtoJaCadastrado.setIngredientes(produtoForm.getIngredientes());
            
        return produtoJaCadastrado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}