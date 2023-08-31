package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.TipoProduto;

/**
 * Classe que representa a abstração dos produtos que podem ser vendidos pela loja.
 * @author thiago leite
 */
public abstract class Produto {

	private TipoProduto tipoProduto;
    /**
     * Código de identiticação do produto.
     */
    private String codigo;
    
    private String nome;
    /**
     * Valor unitário do produto.
     */
    private double preco;

    /**
     * Quantidade comprada do produto.
     */
    private int quantidade;

    public Produto() {}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  

    /**
     * Calcula o preço do frete para os produtos comprados. Este cálculo pode
     * variar de acordo com o produto
     * @return valor do frete para o determinado produto
     */
    //TODO Método de cálculo de frete
    public double calcularFrete () {
    	
    	double frete = 0;
    	
    	
    	return frete;
    }

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
