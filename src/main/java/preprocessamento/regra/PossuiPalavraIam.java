package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraIam implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraIam";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("i'm") || pagina.getOcorrenciasDePalavras().containsKey("i am") ? "s" : "n";
	}

}
