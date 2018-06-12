package br.edu.infnet.av1_web.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="categoria_id", nullable=false, unique=true, length=11)
    private long id;
    
    @Column(name="nome", nullable=false)
    private String nome;
    
    @OneToMany(mappedBy="categoria", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Produto> produtos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}