package br.edu.infnet.av1_web.view.form;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    private String status;
    private String[] produtos;
    private Map<Long, Integer> quantidadesProdutos = new HashMap<>();
    private Map<Long, String> obsProdutos = new HashMap<>();
    
    public static PedidoForm fromRequest(HttpServletRequest request) throws ServiceException {
        
        PedidoFormValidacao.valida(request);
        
        PedidoForm form = new PedidoForm();
        
        form.setCliente(request.getParameter("cliente"));
        form.setEndereco(request.getParameter("endereco"));
        form.setDesconto(request.getParameter("desconto"));
        form.setTroco(request.getParameter("troco"));
        form.setEntrega(request.getParameter("entrega"));
        form.setTaxaEntrega(request.getParameter("taxaEntrega"));
        form.setProdutos(request.getParameterValues("produtos"));
        form.setStatus(request.getParameter("status"));
        
        for(String prod : form.getProdutos()) {
            form.getQuantidadesProdutos().put(new Long(prod), new Integer(request.getParameter("quantidade"+prod)));
        }
        
        for(String prod : form.getProdutos()) {
            form.getObsProdutos().put(new Long(prod), request.getParameter("obs"+prod));
        }
        
        return form;
    }
    
    public Pedido toPedido(EntityManager em) {
        
        Pedido pedido = new Pedido();
        Cliente cli = new Cliente();
        ProdutoPedido produtoPedido;
        Produto produto;
        List<ProdutoPedido> produtosPedidos = new ArrayList<>();
        Endereco end = new Endereco();
        ProdutoService prodService = new ProdutoService(em);
        
        cli.setId(new Long(this.getCliente()));
        pedido.setCliente(cli);
        pedido.setData(LocalDate.now());
        pedido.setHora(LocalTime.now());
        pedido.setStatus(StatusPedido.fromInt(new Integer(this.getStatus())));
        end.setId(new Long(this.getEndereco()));
        pedido.setEndereco(end);
        if(this.getEntrega() != null) {
            pedido.setEntrega(true);
        }
        else {
            pedido.setEntrega(false);
        }
        
        try {
            if(this.getDesconto().length() > 0) {
                pedido.setDesconto(new BigDecimal(DECIMAL_FORMAT.parse(this.getDesconto()).doubleValue()));
            }
            else {
                pedido.setDesconto(new BigDecimal(0));
            }
            if(this.getTroco().length() > 0) {
                pedido.setTroco(new BigDecimal(DECIMAL_FORMAT.parse(this.getTroco()).doubleValue()));
            }
            else {
                pedido.setTroco(new BigDecimal(0));
            }
            if(this.getTaxaEntrega().length() > 0) {
                pedido.setTaxaEntrega(new BigDecimal(DECIMAL_FORMAT.parse(this.getTaxaEntrega()).doubleValue()));
            }
            else {
                pedido.setTaxaEntrega(new BigDecimal(0));
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(PedidoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        double preco = 0.00;
        for(String prod : this.produtos) {
            
            produtoPedido = new ProdutoPedido();
            produto = prodService.getProdutoById(new Long(prod));
            produtoPedido.setObs(obsProdutos.get(produto.getId()));
            produtoPedido.setProduto(produto);
            produtoPedido.setQuantidade(quantidadesProdutos.get(produto.getId()));
            preco += (produto.getPreco().doubleValue() * produtoPedido.getQuantidade());
            produtoPedido.setPedido(pedido);
            produtosPedidos.add(produtoPedido);
        }        
        preco = preco - pedido.getDesconto().longValue();
        pedido.setValor(new BigDecimal(preco));        
        pedido.setProdutos(produtosPedidos);
        
        return pedido;
    }
    
    public Pedido toPedidoAlterado(Pedido pedidoJaCadastrado, Pedido pedidoForm) {
        
        pedidoJaCadastrado.setDesconto(pedidoForm.getDesconto());
        pedidoJaCadastrado.setEndereco(pedidoForm.getEndereco());
        pedidoJaCadastrado.setEntrega(pedidoForm.isEntrega());
//        pedidoJaCadastrado.setProdutos(pedidoForm.getProdutos());
        pedidoJaCadastrado.setStatus(pedidoForm.getStatus());
        pedidoJaCadastrado.setTaxaEntrega(pedidoForm.getTaxaEntrega());
        pedidoJaCadastrado.setTroco(pedidoForm.getTroco());
        pedidoJaCadastrado.setValor(pedidoForm.getValor());
        
        List<ProdutoPedido> produtosPedidos = new ArrayList<>();
        for(ProdutoPedido prodPed : pedidoJaCadastrado.getProdutos()) {
            produtosPedidos.add(prodPed);
        }
        
        for(ProdutoPedido prodPed : produtosPedidos) {
            pedidoJaCadastrado.getProdutos().remove(prodPed);
        }
        
        for(ProdutoPedido prodPed : pedidoForm.getProdutos()) {
            prodPed.setPedido(pedidoJaCadastrado);
            pedidoJaCadastrado.getProdutos().add(prodPed);
        }
        
        return pedidoJaCadastrado;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<Long, Integer> getQuantidadesProdutos() {
        return quantidadesProdutos;
    }

    public void setQuantidadesProdutos(Map<Long, Integer> quantidadesProdutos) {
        this.quantidadesProdutos = quantidadesProdutos;
    }

    public Map<Long, String> getObsProdutos() {
        return obsProdutos;
    }

    public void setObsProdutos(Map<Long, String> obsProdutos) {
        this.obsProdutos = obsProdutos;
    }
}