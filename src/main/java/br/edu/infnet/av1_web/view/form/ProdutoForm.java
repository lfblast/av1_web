package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
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