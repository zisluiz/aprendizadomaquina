package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class TituloPossuiPalavraUniversity implements Regra {

	@Override
	public String getNome() {
		return "tituloPossuiPalavraUniversity";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("!university") ? "s" : "n";
	}

}
