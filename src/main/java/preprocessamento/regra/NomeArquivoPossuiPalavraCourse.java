package preprocessamento.regra;

import org.apache.commons.lang3.StringUtils;

import preprocessamento.model.Pagina;

public class NomeArquivoPossuiPalavraCourse implements Regra {

	@Override
	public String getNome() {
		return "nomeArquivoPossuiPalavraCourse";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return StringUtils.containsIgnoreCase(pagina.getNomeArquivo(), "course") ? "s" : "n";
	}

}
