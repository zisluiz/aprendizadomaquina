package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraEmail implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraEmail";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("e-mail") || pagina.getOcorrenciasDePalavras().containsKey("email") ? "s" : "n";
	}

}
