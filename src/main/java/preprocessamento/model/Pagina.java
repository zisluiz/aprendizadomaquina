package preprocessamento.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pagina {
	private String nomeArquivo;
	private int tamanhoArquivo;
	private Map<String, Integer> ocorrenciasDePalavras = new HashMap<>();
	private List<Ocorrencia> ocorrencias;
	
	public Pagina(String nomeArquivo, int tamanhoArquivo) {
		this.nomeArquivo = nomeArquivo;
		this.tamanhoArquivo = tamanhoArquivo;
	}

	public void addOcorrencia(String word) {
		Integer ocorrencias = ocorrenciasDePalavras.get(word);
		
		if (ocorrencias == null)
			ocorrencias = 1;
		else
			ocorrencias++;
			
		ocorrenciasDePalavras.put(word, ocorrencias);	
	}
	
	public void putOcorrencia(String word, Integer quantidade) {
		ocorrenciasDePalavras.put(word, quantidade);	
	}
	
	public List<Ocorrencia> getOcorrencias() {
		if (ocorrencias == null) {
			ocorrencias = new ArrayList<>();
			Set<String> palavras = ocorrenciasDePalavras.keySet();
			for (String palavra : palavras)
				ocorrencias.add(new Ocorrencia(palavra, ocorrenciasDePalavras.get(palavra)));
			
			Collections.sort(ocorrencias);
		}

		return ocorrencias;
	}	

	public Map<String, Integer> getOcorrenciasDePalavras() {
		return ocorrenciasDePalavras;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public int getTamanhoArquivo() {
		return tamanhoArquivo;
	}
}
