package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraWill implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraWill";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("will") ? "s" : "n";
	}

}
