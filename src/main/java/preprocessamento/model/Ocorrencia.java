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
		boolean o1TemCerq = getPalavra().charAt(0) == '#';
		boolean o1TemPorc = getPalavra().charAt(0) == '!';
		boolean o2TemCerq = o.getPalavra().charAt(0) == '#';
		boolean o2TemPorc = o.getPalavra().charAt(0) == '!';
		
		if ((o1TemCerq || o1TemPorc) && (!o2TemCerq && !o2TemPorc))
			return -1;
		else if ((o2TemCerq || o2TemPorc) && (!o1TemCerq && !o1TemPorc))
			return 1;
		else if (o1TemCerq && o2TemPorc)
			return -1;
		else if (o2TemCerq && o1TemPorc)
			return 1;
		else {
			if (getQuantidade() > o.getQuantidade())
				return -1;
			else if (getQuantidade() < o.getQuantidade())
				return 1;
			else
				return 0;
		}
		
//		if (getPalavra().charAt(0) == '#' && o.getPalavra().charAt(0) != '#')
//			return -1;
//		else if (getPalavra().charAt(0) != '#' && o.getPalavra().charAt(0) == '#')
//			return 1;
//		else if (getQuantidade() > o.getQuantidade())
//			return -1;
//		else if (getQuantidade() < o.getQuantidade())
//			return 1;		
//		else
//			return 0;	
	}

}
