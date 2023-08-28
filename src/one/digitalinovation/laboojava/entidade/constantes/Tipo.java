package one.digitalinovation.laboojava.entidade.constantes;

public enum Tipo {

	 	M2(2),

	    M5(5),

	    M10(10);

	    private double fator;

	    /**
	     * Construtor
	     * @param fator Valor por tipo que influencia no cálculo do frete.
	     */
	    Tipo(double fator) {
	        this.fator = fator / 100;
	    }

	    public double getFator() {
	        return fator;
	    }
}
