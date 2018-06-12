package br.edu.infnet.av1_web.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="produto_id", nullable=false, unique=true, length=11)
    private long id;
    
    @Column(name="codigo", nullable=false)
    private int codigo;
    
    @Column(name="nome", nullable=false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name="categoria_id", nullable=false)
    private Categoria categoria;
    
    @Column(name="preco", nullable=false)
    private BigDecimal preco;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "produto_ingrediente", 
        joinColumns = { @JoinColumn(name = "produto_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "ingrediente_id") }
    )
    private List<Ingrediente> ingredientes;
    
    @OneToMany(mappedBy="produto", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<ProdutoPedido> produtosPedidos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<ProdutoPedido> getProdutosPedidos() {
        return produtosPedidos;
    }

    public void setProdutosPedidos(List<ProdutoPedido> produtosPedidos) {
        this.produtosPedidos = produtosPedidos;
    }
}