package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class TituloPossuiPalavraResearch implements Regra {

	@Override
	public String getNome() {
		return "tituloPossuiPalavraResearch";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("!research") ? "s" : "n";
	}

}
