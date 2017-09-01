package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraCurrently implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraCurrently";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("currently") || pagina.getOcorrenciasDePalavras().containsKey("i am") ? "s" : "n";
	}

}
