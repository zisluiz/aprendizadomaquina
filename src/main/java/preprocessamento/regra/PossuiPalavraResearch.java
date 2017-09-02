package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraResearch implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraResearch";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("research") ? "s" : "n";
	}

}
