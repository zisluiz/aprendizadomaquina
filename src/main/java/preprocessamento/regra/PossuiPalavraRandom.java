package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraRandom implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraRandomOuRandomized";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("random") || pagina.getOcorrenciasDePalavras().containsKey("randomized") ? "s" : "n";
	}

	@Override
	public boolean ative() {
		return false;
	}
}
