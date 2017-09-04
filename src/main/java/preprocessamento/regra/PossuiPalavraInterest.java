package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraInterest implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraInterestOuInterests";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("interest") || pagina.getOcorrenciasDePalavras().containsKey("interests") ? "s" : "n";
	}

}
