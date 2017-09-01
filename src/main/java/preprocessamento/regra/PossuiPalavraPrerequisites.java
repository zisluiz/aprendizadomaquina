package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraPrerequisites implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraPrerequisites";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("prerequisites") ? "s" : "n";
	}

}
