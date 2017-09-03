package preprocessamento.model;

public class Ocorrencia implements Comparable<Ocorrencia> {
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

	@Override
	public int compareTo(Ocorrencia o) {
		if (getPalavra().charAt(0) == '#' && o.getPalavra().charAt(0) != '#')
			return -1;
		else if (getPalavra().charAt(0) != '#' && o.getPalavra().charAt(0) == '#')
			return 1;
		else if (getQuantidade() > o.getQuantidade())
			return -1;
		else if (getQuantidade() < o.getQuantidade())
			return 1;		
		else
			return 0;	
	}

}
