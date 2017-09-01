package preprocessamento.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

public class Categoria {
	private CategoriaEnum tipoCategoria;
	private Map<String, Integer> ocorrenciasDePalavras = new HashMap<>();
	private List<Universidade> universidades = new ArrayList<>();
	private List<Ocorrencia> ocorrencias;

	public Categoria(CategoriaEnum tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public Categoria(CategoriaEnum tipoCategoria, Map<String, Integer> ocorrenciasDePalavras) {
		this.tipoCategoria = tipoCategoria;
		this.ocorrenciasDePalavras = ocorrenciasDePalavras;
	}

	public void addOcorrencia(String word) {
		Integer ocorrencias = ocorrenciasDePalavras.get(word);

		if (ocorrencias == null)
			ocorrencias = 1;
		else
			ocorrencias++;

		ocorrenciasDePalavras.put(word, ocorrencias);
	}

	public List<Ocorrencia> getOcorrencias() {
		if (ocorrencias == null) {
			ocorrencias = new ArrayList<>();
			Set<String> palavras = ocorrenciasDePalavras.keySet();
			for (String palavra : palavras)
				ocorrencias.add(new Ocorrencia(palavra, ocorrenciasDePalavras.get(palavra)));

			BeanComparator fieldComparator = new BeanComparator("quantidade", new ReverseComparator(new ComparableComparator()));
			Collections.sort(ocorrencias, fieldComparator);
		}

		return ocorrencias;
	}
	
	public List<Universidade> getUniversidades() {
		return universidades;
	}

	public void addUniversidade(Universidade universidade) {
		universidades.add(universidade);
	}
	
	public Map<String, Integer> getOcorrenciasDePalavras() {
		return ocorrenciasDePalavras;
	}
	
	public CategoriaEnum getTipoCategoria() {
		return tipoCategoria;
	}
}