package one.digitalinovation.laboojava.utilidade;

import one.digitalinovation.laboojava.basedados.Banco;

import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.constantes.Genero;
import one.digitalinovation.laboojava.entidade.constantes.Tipo;
import one.digitalinovation.laboojava.entidade.constantes.TipoProduto;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe utilitária para auxiliar na leitura de entradas de dados via teclado.
 * @author thiago leite
 */
public final class LeitoraDados {

	/**
	 * Classe do Java para manipular entradas via teclado.
	 */
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	/**
	 * Ler um dado específico
	 * @return Dado lido
	 */
	public static String lerDado() {
		
		String texto = scanner.nextLine();
		
		return texto;
	}

	/**
	 * Ler os dados do livro a ser cadastrado.
	 * @return Um livro a partir dos dados de entrada
	 */
	public static Livro lerLivro() {

		System.out.println("Cadastrando livro...");
		Livro livro = new Livro();

		livro.setTipoProduto(TipoProduto.LIVRO);
//tipo de produto
		/*while(livro.getTipoProduto()==null){
			System.out.println("\nDigite o tipo de produto: LIVRO, CADERNO");
			String tipoProd = lerDado();
			try {livro.setTipoProduto(TipoProduto.valueOf(tipoProd.toUpperCase()));}
			catch (IllegalArgumentException e) {
				System.out.print("Esse valor não corresponde a nenhum tipo de produto disponível no momento");
			}
		}*/
		
		System.out.println("Digite o nome");
		String nome = lerDado();
		livro.setNome(nome);
//tratamento do input para gênero, de acordo com valores do enum Genero
		while(livro.getGenero()==null) {
			System.out.println("Digite o gênero: DRAMA, SUSPENSE, ROMANCE");
			String genero = lerDado();
			try { livro.setGenero(Genero.valueOf(genero.toUpperCase())); }
			catch(IllegalArgumentException e){
				System.out.print("Esse valor não corresponde a nenhum gênero de livro disponível");
			}
		}
	
		System.out.println("Digite o preço(padrão 0.0)");
		String preco = lerDado();
		livro.setPreco(Double.parseDouble(preco));

		return livro;
	}
	
	/**
	 * Ler os dados do caderno a ser cadastrado.
	 * @return Um caderno a partir dos dados de entrada
	 */
	//TODO Método para ler o caderno
	public static Caderno lerCaderno() {

		System.out.println("Cadastrando caderno...");
		Caderno caderno = new Caderno();
		
		caderno.setTipoProduto(TipoProduto.CADERNO);
		//tipo de produto
		/*while(caderno.getTipoProduto()==null){
			System.out.println("\nDigite o tipo de produto: LIVRO, CADERNO");
			String tipoProd = lerDado();
			try {caderno.setTipoProduto(TipoProduto.valueOf(tipoProd.toUpperCase()));}
			catch (IllegalArgumentException e) {
				System.out.print("Esse valor não corresponde a nenhum tipo de produto disponível no momento");
			}
		}*/

		System.out.println("Digite o nome");
		String nome = lerDado();
		caderno.setNome(nome);
		
		//tratamento do input de tipo, de acordo com os valores do enum Tipo
		while(caderno.getTipo()==null){
			System.out.println("\nDigite o tipo: M2, M5, M10");
			String tipo = lerDado();
			try {caderno.setTipo(Tipo.valueOf(tipo.toUpperCase()));}
			catch (IllegalArgumentException e) {
				System.out.print("Esse valor não corresponde a nenhum tipo de caderno disponível");
			}
		}
		
		System.out.println("Digite o preço(padrão 0.0)");
		String preco = lerDado();
		caderno.setPreco(Double.parseDouble(preco));

		return caderno;
	}

	/**
	 * Ler os dados do pedido e retorna um objeto a partir destes.
	 * @return Um pedido a partir dos dados de entrada
	 */
	public static Pedido lerPedido(Banco banco, Cliente cliente) {
		
		//instância para utilizar método consultar desta classe
		ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

		System.out.println("Cadastrando pedido...");
		//inputs para o construtor
		
		//detalhe: produtoParametro como sendo 1 item da lista na classe Pedido
		String opcao = "s";
		Pedido pedido = new Pedido(cliente);
		do {
			System.out.println("Digite o código do produto(livro/Caderno)");
			String codigo = lerDado();

			Optional<Produto> resultado = produtoNegocio.consultar(codigo);
			if (resultado.isPresent()) {

				Produto produto = resultado.get();

				System.out.println("Digite a quantidade");
				String quantidade = lerDado();
				produto.setQuantidade(Integer.parseInt(quantidade));

				pedido.getProdutos().add(produto);
				System.out.println(produto.toString());
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um produto? s/n");
			opcao = lerDado();
		} while("s".equals(opcao));
		
		//ler cupom
		Optional<Cupom> cupom = lerCupom(banco);
		
		if(cupom.isPresent()) {
			pedido.setCupom(cupom);
		}
		
		return pedido;
	}

	/**
	 * Ler os dados do cupom e retorna um objeto a partir destes.
	 * @return O cupom a partir dos dados de entrada
	 */
	public static Optional<Cupom> lerCupom(Banco banco) {

		System.out.println("Caso queira utilizar algum cupom escolha entre: CUPOM2, CUPOM5, CUPOM7. Se não desejar, deixe em branco.");

		String desconto = lerDado();

		for (Cupom cupom: banco.getCupons()) {
			if (cupom.getCodigo().equalsIgnoreCase(desconto)) {
				return Optional.of(cupom);
			}
		}

		return Optional.empty();
	}

}
