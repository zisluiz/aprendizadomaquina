package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraWe implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraWe";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("we") ? "s" : "n";
	}

}
