package preprocessamento.model;

import java.util.HashMap;
import java.util.Map;

public class Tag {
	private Map<String, Integer> ocorrenciasDePalavras = new HashMap<>();
	
	public Map<String, Integer> getOcorrenciasDePalavras() {
		return ocorrenciasDePalavras;
	}
	
}
