package br.edu.infnet.av1_web.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.model.Endereco;
import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.model.ProdutoPedido;
import br.edu.infnet.av1_web.model.StatusPedido;
import br.edu.infnet.av1_web.service.ProdutoService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


public class PedidoForm {
    
    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(
                new Locale("pt", "BR"));
        DECIMAL_FORMAT.applyPattern("#0.00");
    }
    
    private String cliente;
    private String endereco;
    private String desconto;
    private String troco;
    private String entrega;
    private String taxaEntrega;
    private String[] produtos;
    
    public static PedidoForm fromRequest(HttpServletRequest request) {
        
        PedidoForm form = new PedidoForm();
        
        form.setCliente(request.getParameter("cliente"));
        form.setEndereco(request.getParameter("endereco"));
        form.setDesconto(request.getParameter("desconto"));
        form.setTroco(request.getParameter("troco"));
        form.setEntrega(request.getParameter("entrega"));
        form.setTaxaEntrega(request.getParameter("taxaEntrega"));
        form.setProdutos(request.getParameterValues("produtos"));
        
        return form;
    }
    
    private void valida() throws ServiceException {
        
    } 
    
    public Pedido toPedido(EntityManager em) {
        
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        ProdutoPedido produtoPedido;
        Produto produto;
        List<ProdutoPedido> produtos = new ArrayList<ProdutoPedido>();
        Endereco end = new Endereco();
        ProdutoService prodService = new ProdutoService(em);
        
        cliente.setId(new Long(this.getCliente()));
        pedido.setCliente(cliente);
        pedido.setData(LocalDate.now());
        pedido.setHora(LocalTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO_CONFIRM_PAGAMENTO);
        end.setId(new Long(this.getEndereco()));
        pedido.setEndereco(end);
        if(this.getEntrega() != null) {
            pedido.setEntrega(true);
        }
        else {
            pedido.setEntrega(false);
        }
        
        try {
            pedido.setDesconto(new BigDecimal(DECIMAL_FORMAT.parse(this.getDesconto()).doubleValue()));
            pedido.setTroco(new BigDecimal(DECIMAL_FORMAT.parse(this.getTroco()).doubleValue()));
            pedido.setTaxaEntrega(new BigDecimal(DECIMAL_FORMAT.parse(this.getTaxaEntrega()).doubleValue()));
        } catch (ParseException ex) {
            Logger.getLogger(PedidoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        double preco = 0.00;
        for(String prod : this.produtos) {
            
            produtoPedido = new ProdutoPedido();
            produto = prodService.getProdutoById(new Long(prod));
            produtoPedido.setObs("Obs Padrão");
            produtoPedido.setProduto(produto);
            produtoPedido.setQuantidade(1);
            preco += produto.getPreco().doubleValue();
            produtoPedido.setPedido(pedido);
            produtos.add(produtoPedido);
        }
        pedido.setValor(new BigDecimal(preco));        
        pedido.setProdutos(produtos);
        
        return pedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(String taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public String[] getProdutos() {
        return produtos;
    }

    public void setProdutos(String[] produtos) {
        this.produtos = produtos;
    }
}