package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class TituloPossuiPalavraGroup implements Regra {

	@Override
	public String getNome() {
		return "tituloPossuiPalavraGroup";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("!group") ? "s" : "n";
	}

}
