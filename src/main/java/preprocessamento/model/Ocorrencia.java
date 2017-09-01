package preprocessamento.model;

public class Ocorrencia {
	private String palavra;
	private int quantidade;
	
	public Ocorrencia(String palavra, int quantidade) {
		this.palavra = palavra;
		this.quantidade = quantidade;
	}
	
	public String getPalavra() {
		return palavra;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
}
