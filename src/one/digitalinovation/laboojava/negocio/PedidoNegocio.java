package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;

import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Pedido}.
 * @author thiago leite
 */
public class PedidoNegocio {
	
	LeitoraDados leitora = new LeitoraDados();
    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os pedidos
     */
    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Optional<Cupom> cupom1) {	
    	
    	Cupom cupom = cupom1.get();
    	double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete() + produto.getPreco();            
        }
        if (cupom != null) {
            return  total * (1 - cupom.getDesconto());
        } else {
            return  total;
        }

    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     */
    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     * @param cupom Cupom de desconto a ser utilizado
     */
    public void salvar (Pedido novoPedido, Optional<Cupom> cupom) {
    	/*TODOS
    	 * 
    	 */
        //Definir padrão código
    	//Formatar código
        //Setar código no pedido
    	String codigo = "PD%04d";
        codigo = String.format(codigo, bancoDados.getPedidos().length);
        novoPedido.setCodigo(codigo);
        //Pegar data do dia corrente - colocado na inicialização do objeto
     
        //Setar cliente no pedido - ja foi
        
        //Calcular e set total
        novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(), novoPedido.getCupom()));
        
        //Adicionar no banco
        //Mensagem
        boolean pedidoRepetido = false;
        for (Pedido pedido: bancoDados.getPedidos()) {
            if (pedido == novoPedido) {
                pedidoRepetido = true;
                System.out.println("Você já possui um pedido exatamente igual. Deseja fazer este pedido mesmo assim? s/n");
                String opcao = leitora.lerDado();
                if(opcao == "s") {
                	this.bancoDados.adicionarPedido(novoPedido);
                	System.out.println("Pedido cadastrado com sucesso.");
                }
                break;
            }
        }

        if (!pedidoRepetido) {
        	this.bancoDados.adicionarPedido(novoPedido);
            System.out.println("Pedido cadastrado com sucesso.");
        }
    }

    /**
     * Exclui um pedido a partir de seu código de rastreio.
     * @param codigo Código do pedido
     */
    public void excluir(String codigo) {

        int pedidoExclusao = -1;
        for (int i = 0; i < bancoDados.getPedidos().length; i++) {

            Pedido pedido = bancoDados.getPedidos()[i];
            if (pedido.getCodigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerPedido(pedidoExclusao);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }

 
    /**
     * Lista todos os pedidos realizados.
     */
    //TODO Método de listar todos os pedidos
    
    public void listarPedidos () {
    	
    	if (bancoDados.getPedidos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Pedido pedido: bancoDados.getPedidos()) {
                System.out.println(pedido.toString());
                
                for (Produto produto: pedido.getProdutos()) {
                	System.out.println(" código prod.: " + produto.getCodigo() + " quantidade: " + produto.getQuantidade());
            }
        }
    
    }
    
    }
    
    public Optional<Pedido> consultar(String codigo) {

        for (Pedido pedido: bancoDados.getPedidos()) {

            if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
            	
            	System.out.println(pedido.toString());
            	for (Produto produto: pedido.getProdutos()) {
                	System.out.println(" código prod.: " + produto.getCodigo() + " quantidade: " + produto.getQuantidade());
            	}
            	
                return  Optional.of(pedido);
            }
        }

        return Optional.empty();
    }
}
