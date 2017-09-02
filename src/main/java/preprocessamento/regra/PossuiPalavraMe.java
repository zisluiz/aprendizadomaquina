package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraMe implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraMe";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("me") ? "s" : "n";
	}

}
