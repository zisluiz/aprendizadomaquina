package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraHomework implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraHomework";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("homework") ? "s" : "n";
	}

}
