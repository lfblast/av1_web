package br.edu.infnet.av1_web.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa {
    
    @OneToMany(mappedBy="cliente", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Endereco> enderecos;
    
    @OneToMany(mappedBy="cliente", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Pedido> pedidos;
    
    @Column(name="telefone")
    private int telefone;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}