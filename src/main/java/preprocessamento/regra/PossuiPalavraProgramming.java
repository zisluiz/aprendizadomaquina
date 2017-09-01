package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraProgramming implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraProgramming";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("programming") ? "s" : "n";
	}

}
