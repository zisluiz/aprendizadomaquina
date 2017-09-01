package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraIntroduction implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraIntroduction";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("introduction") ? "s" : "n";
	}

}
