package br.edu.infnet.av1_web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pedido_id", nullable=false, unique=true, length=11)
    private long id;
    
    @OneToMany(mappedBy="pedido", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<ProdutoPedido> produtos;
    
    @ManyToOne
    @JoinColumn(name="endereco_id", nullable=false)
    private Endereco endereco;
    
    @Column(name="entrega", nullable=false)
    private boolean entrega;
    
    @Column(name="desconto")
    private BigDecimal desconto;
    
    @Column(name="taxa_entrega")
    private BigDecimal taxaEntrega;
    
    @Column(name="valor", nullable=false)
    private BigDecimal valor;
    
    @Column(name="troco")
    private BigDecimal troco;
    
    @Column(name="data", nullable=false)
    private LocalDate data;
    
    @Column(name="hora", nullable=false)
    private LocalTime hora;
    
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoPedido> produtos) {
        this.produtos = produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(BigDecimal taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}