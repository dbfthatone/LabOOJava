package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Classe que representa a entidade pedido, que é a compra dos produtos por um cliente.
 * @author Diêgo de Barros
 */
public class Pedido {
    //TODO Preencher esta classe
	
    //codigo
    //cliente
    //produtos
    //total
	
	private String codigo;
	
	private Cliente cliente;
	
	private List<Produto> produtos;
	
	private Optional<Cupom> cupom;
	
	//getter  setter OP CUPOM -------------
	public Optional<Cupom> getCupom() {
		return cupom;
	}
	
	public void setCupom(Optional<Cupom> cupom) {
		this.cupom = cupom;
	}

	private double total;
	
	private String dataFormatada;
	//formatação para o campo dataFormatada, campo de data para gravar a data do pedido
		LocalDate dataAtual = LocalDate.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	//construtor: codigo, cliente e ao menos UM produto
	public Pedido(Cliente cliente) {
			
		//this.codigo 
		this.cliente = cliente;
		this.produtos = new ArrayList<>();
		//gravando data do pedido
		this.dataFormatada = dataAtual.format(formatador);
		
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

//	public List<Cupom> getCupons() {
//		return cupons;
//	}
//
//	public void setCupons(List<Cupom> cupons) {
//		this.cupons = cupons;
//	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
	
	
////TODO método para calcular o total
//	private double calcularTotal () {
//		
//		double total = 0;
//		
//		for (Produto produto: getProdutos()) {
//			total = + produto.getPreco() + produto.calcularFrete();
//			
//			
//		}
//		System.out.println("total: " + total);
//		return total;
//	}
	
	}