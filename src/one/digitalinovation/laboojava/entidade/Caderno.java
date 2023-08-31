package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Tipo;
import one.digitalinovation.laboojava.entidade.constantes.TipoProduto;

public class Caderno extends Produto {

	 /**
     * Nome do caderno.
     */
    private String nome;

    /**
     * Tipo do caderno.
     */
    private Tipo tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
   

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + tipo.getFator());
    }

    @Override
    public String toString() {
        return "Livro{" +
                "nome='" + getNome() + '\'' +
                ", tipo=" + tipo + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }
}
