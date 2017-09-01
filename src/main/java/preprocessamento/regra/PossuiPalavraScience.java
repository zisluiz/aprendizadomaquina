package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraScience implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraScience";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("science") ? "s" : "n";
	}

}
