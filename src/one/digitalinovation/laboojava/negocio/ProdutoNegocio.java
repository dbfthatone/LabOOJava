package one.digitalinovation.laboojava.negocio;



import one.digitalinovation.laboojava.basedados.Banco;

import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.constantes.TipoProduto;

import java.util.Optional;
import java.util.Scanner;

import one.digitalinovation.laboojava.utilidade.LeitoraDados;

/**
 * Classe para manipular a entidade {@link Produto}.
 * @author thiago leite
 */
public class ProdutoNegocio {
	
	LeitoraDados leitora = new LeitoraDados();
    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os produtos
     */
    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Salva um novo produto(livro ou caderno) na loja.
     * @param novoProduto Livro ou caderno que pode ser vendido
     */
    public void salvar(Produto novoProduto) {

        String codigo = "PR%04d";
        codigo = String.format(codigo, bancoDados.getProdutos().length);
        novoProduto.setCodigo(codigo);

        boolean produtoRepetido = false;
        for (Produto produto: bancoDados.getProdutos()) {
            if (produto.getCodigo() == novoProduto.getCodigo()) {
                produtoRepetido = true;
                System.out.println("Produto já cadastrado.");
                break;
            }
        }

        if (!produtoRepetido) {
            this.bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    /**
     * Exclui um produto pelo código de cadastro.
     * @param codigo Código de cadastro do produto
     */
    public void excluir(String codigoParaRemover) {
    	
    	for (Produto produto : bancoDados.getProdutos()) {
    		if(produto.getCodigo().equals(codigoParaRemover)) {
    			bancoDados.removerProduto(produto);
    			if (produto.getCodigo().equals(codigoParaRemover)) {
    				System.out.println("Produto removido");
    			}
    			else {
    				System.out.println("Falha ao remover produto");
    			}			
    		}
    	}
    }

    /**
     * Obtem um produto a partir de seu código de cadastro. 
     * @param codigo Código de cadastro do produto
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Produto> consultar(String codigo) {

        for (Produto produto: bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return  Optional.of(produto);
            }
        }

        return Optional.empty();
    }
    
    /**
     * Obtem um produto a partir de seu nome e tipo (deve funcionar para qualquer tipo de produto).
     * @param nome
     * @return Optional 
     */
    public Optional<Produto> consultarPorNome(String nome, String tipoProd) {

    	

    	for (Produto produto: bancoDados.getProdutos()) {
    		//filtro por tipo de produto e nome
            if (produto.getTipoProduto().toString().equalsIgnoreCase(tipoProd) && produto.getNome().equalsIgnoreCase(nome)) {
                
            	produto.toString();
            	return  Optional.of(produto);
            }
            else {
            	System.out.println("não encontrado!");
            }
            
        }
    	
        return Optional.empty();
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto: bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }
}
