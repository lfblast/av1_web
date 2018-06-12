package br.edu.infnet.av1_web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produto_pedido")
public class ProdutoPedido {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="produto_pedido_id", nullable=false, unique=true, length=11)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="produto_id", nullable=false)
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name="pedido_id", nullable=false)
    private Pedido pedido;
    
    @Column(name="quantidade", nullable=false)
    private int quantidade;
    
    @Column(name="obs")
    private String obs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}