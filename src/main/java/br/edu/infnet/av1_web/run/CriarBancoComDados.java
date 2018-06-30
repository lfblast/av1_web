package br.edu.infnet.av1_web.run;

import br.edu.infnet.av1_web.model.Categoria;
import br.edu.infnet.av1_web.model.Cliente;
import br.edu.infnet.av1_web.model.Endereco;
import br.edu.infnet.av1_web.model.Ingrediente;
import br.edu.infnet.av1_web.model.Pedido;
import br.edu.infnet.av1_web.model.Produto;
import br.edu.infnet.av1_web.model.ProdutoPedido;
import br.edu.infnet.av1_web.model.StatusPedido;
import br.edu.infnet.av1_web.repository.CategoriaRepository;
import br.edu.infnet.av1_web.repository.ClienteRepository;
import br.edu.infnet.av1_web.repository.EnderecoRepository;
import br.edu.infnet.av1_web.repository.IngredienteRepository;
import br.edu.infnet.av1_web.repository.PedidoRepository;
import br.edu.infnet.av1_web.repository.ProdutoRepository;
import br.edu.infnet.av1_web.util.JpaUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class CriarBancoComDados {
    
    private static final EntityManager MANAGER = JpaUtil.getEntityManager();
    
    public static void main(String[] args) {
        
        insereCategorias();
        insereIngredientes();
        insereProdutos();
        insereClientes();
        insereEnderecos();
        
        JpaUtil.closeEntityManagerFactory();
    }
    
    private static void insereCategorias() {
        
        CategoriaRepository catRep = new CategoriaRepository(MANAGER);
        
        List<Categoria> categorias = new ArrayList<>();
        Categoria categoria = new Categoria();
        categoria.setNome("PIZZAS");
        categorias.add(categoria);
        
        categoria = new Categoria();
        categoria.setNome("PIZZAS DOCES");
        categorias.add(categoria);
        
        categoria = new Categoria();
        categoria.setNome("CALZONES");
        categorias.add(categoria);        
        
        for(Categoria cat : categorias) {
            catRep.beginTransatcion();
            catRep.incluir(cat);
            catRep.commitTransaction();
        }
    }
    
    private static void insereIngredientes() {
        
        IngredienteRepository ingRep = new IngredienteRepository(MANAGER);
        
        List<Ingrediente> ingredientes = new ArrayList<>();
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Mussarela");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Azeitona");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Catupiry");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Tomate");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Salame");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Peito de Peru");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Champignon");
        ingredientes.add(ingrediente);
        
        ingrediente = new Ingrediente();
        ingrediente.setNome("Chocolate");
        ingredientes.add(ingrediente);
        
        for(Ingrediente ingr : ingredientes) {
            ingRep.beginTransatcion();
            ingRep.incluir(ingr);
            ingRep.commitTransaction();
        }
    }
    
    private static Categoria selecionaCategoria(long id) {
        
        CategoriaRepository catRep = new CategoriaRepository(MANAGER);
        return catRep.selecionar(Categoria.class, id);
    }
    
    private static Ingrediente selecionaIngrediente(long id) {
        
        IngredienteRepository ingRep = new IngredienteRepository(MANAGER);
        return ingRep.selecionar(Ingrediente.class, id);
    }
    
    private static void insereProdutos() {
        
        ProdutoRepository prodRep = new ProdutoRepository(MANAGER);        
        List<Produto> produtos = new ArrayList<>();
        
        Produto produto = new Produto();
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(selecionaIngrediente(1));
        ingredientes.add(selecionaIngrediente(2));
        ingredientes.add(selecionaIngrediente(4));
        produto.setCodigo(1234);
        produto.setNome("Pizza de Mussarela");
        produto.setCategoria(selecionaCategoria(1));
        produto.setPreco(new BigDecimal(15.50));
        produto.setIngredientes(ingredientes);
        produtos.add(produto);
        
        produto = new Produto();
        ingredientes = new ArrayList<>();
        ingredientes.add(selecionaIngrediente(3));
        ingredientes.add(selecionaIngrediente(2));        
        produto.setCodigo(4321);
        produto.setNome("Pizza de Catupiry");
        produto.setCategoria(selecionaCategoria(1));
        produto.setPreco(new BigDecimal(13.50));
        produto.setIngredientes(ingredientes);
        produtos.add(produto);
        
        produto = new Produto();
        ingredientes = new ArrayList<>();
        ingredientes.add(selecionaIngrediente(8));                
        produto.setCodigo(5678);
        produto.setNome("Pizza de Chocolate");
        produto.setCategoria(selecionaCategoria(2));
        produto.setPreco(new BigDecimal(10.50));
        produto.setIngredientes(ingredientes);
        produtos.add(produto);
        
        produto = new Produto();
        ingredientes = new ArrayList<>();
        ingredientes.add(selecionaIngrediente(5));
        ingredientes.add(selecionaIngrediente(6));
        ingredientes.add(selecionaIngrediente(7));
        produto.setCodigo(8765);
        produto.setNome("Calzone Misto");
        produto.setCategoria(selecionaCategoria(3));
        produto.setPreco(new BigDecimal(20.99));
        produto.setIngredientes(ingredientes);
        produtos.add(produto);
        
        for(Produto prod : produtos) {
            prodRep.beginTransatcion();
            prodRep.incluir(prod);
            prodRep.commitTransaction();
        }
    }
    
    private static void insereClientes() {
        
        ClienteRepository cliRep = new ClienteRepository(MANAGER);
        List<Cliente> clientes = new ArrayList<>();
        
        Cliente cliente = new Cliente();
        cliente.setNome("Fulano da Silva");
        cliente.setTelefone(2345678);
        clientes.add(cliente);
        
        cliente = new Cliente();
        cliente.setNome("Cicrano Pereira");
        cliente.setTelefone(91234567);        
        clientes.add(cliente);
        
        for(Cliente cli : clientes) {
            cliRep.beginTransatcion();
            cliRep.incluir(cli);
            cliRep.commitTransaction();
        }
    }
    
    private static Cliente selecionaCliente(long id) {
        
        ClienteRepository cliRep = new ClienteRepository(MANAGER);
        return cliRep.selecionar(Cliente.class, id);
    }
    
    private static void insereEnderecos() {
        
        EnderecoRepository endRep = new EnderecoRepository(MANAGER);
        List<Endereco> enderecos = new ArrayList<>();
        
        Endereco endereco = new Endereco();
        endereco.setRua("Rua do Endereço - Vila Isabel");
        endereco.setNumero(10);
        endereco.setReferencia("Em frente a tal lugar");
        endereco.setCliente(selecionaCliente(1));
        enderecos.add(endereco);
        
        endereco = new Endereco();
        endereco.setRua("Rua Outro Lugar - Tijuca");
        endereco.setNumero(687);
        endereco.setReferencia("Sem referência");
        endereco.setCliente(selecionaCliente(1));
        enderecos.add(endereco);
        
        endereco = new Endereco();
        endereco.setRua("Rua Muito Longe - Jacarepaguá");
        endereco.setNumero(950);
        endereco.setReferencia("Ali, lá em baixo à direita");
        endereco.setCliente(selecionaCliente(2));
        enderecos.add(endereco);
        
        for(Endereco end : enderecos) {
            endRep.beginTransatcion();
            endRep.incluir(end);
            endRep.commitTransaction();
        }
    }
    
    private static Produto selecionaProduto(long id) {
        
        ProdutoRepository prodRep = new ProdutoRepository(MANAGER);
        return prodRep.selecionar(Produto.class, id);
    }
    
    private static Pedido selecionaPedido(long id) {
        
        PedidoRepository pedidoRep = new PedidoRepository(MANAGER);
        return pedidoRep.selecionar(Pedido.class, id);
    }
    
    private static void inserePedido() {
        PedidoRepository pedRep = new PedidoRepository(MANAGER);
        
        Pedido pedido = new Pedido();
        ProdutoPedido prodPed = new ProdutoPedido();
        List<ProdutoPedido> produtos = new ArrayList<>();
        
        prodPed.setObs("Teste");
        prodPed.setProduto(selecionaProduto(1));
        prodPed.setQuantidade(1);
        prodPed.setPedido(pedido);
        
        produtos.add(prodPed);
        
        pedido.setCliente(selecionaCliente(1));
        pedido.setData(LocalDate.now());
        pedido.setDesconto(new BigDecimal(5));
        System.out.println("---------------------------------------------------------- CLIENTE: " + pedido.getCliente().getId());
        List<Endereco> enderecos = pedido.getCliente().getEnderecos();
        System.out.println("---------------------------------------------------------- TAMANHO: " + enderecos.size());
        pedido.setEndereco(enderecos.get(1));
        pedido.setEntrega(true);
        pedido.setHora(LocalTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO_CONFIRM_PAGAMENTO);
        pedido.setTaxaEntrega(new BigDecimal(5));
        pedido.setTroco(new BigDecimal(5));
        pedido.setValor(new BigDecimal(5));
        pedido.setProdutos(produtos);
        
        pedRep.beginTransatcion();
        pedRep.incluir(pedido);
        pedRep.commitTransaction();
    }
    
    private static void removerPedido() {
        
        PedidoRepository pedRep = new PedidoRepository(MANAGER);
        Pedido pedido = selecionaPedido(1);
                
        pedRep.beginTransatcion();
        pedRep.excluir(pedido);
        pedRep.commitTransaction();
    }
}